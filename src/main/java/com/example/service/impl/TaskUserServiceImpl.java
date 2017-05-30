package com.example.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

import com.example.ConstanteGameMaster;
import com.example.dto.ColonneKanbanDTO;
import com.example.dto.PeriodicityDTO;
import com.example.enumeration.PeriodicityEnum;
import com.example.enumeration.PriorityEnum;
import com.example.model.ColonneKanban;
import com.example.model.Task;
import com.example.repository.ColonneKanbanRepository;
import com.example.repository.PeriodicityRepository;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TaskUserDTO;
import com.example.dto.TaskWithPeriodDTO;
import com.example.enumeration.StatusEnum;
import com.example.exception.GameMasterException;
import com.example.model.Periodicity;
import com.example.model.TaskUser;
import com.example.repository.TaskUserRepository;
import com.example.service.TaskUserService;
import com.example.transformers.Transformers;


@Service
public class TaskUserServiceImpl implements TaskUserService {

    @Autowired
    private TaskUserRepository taskUserRepository;

    @Autowired
    private PeriodicityRepository periodicityRepository;

    @Autowired
    private ColonneKanbanRepository colonneKanbanRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Transformers transformers;

    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);


    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserService#getTaskUserByUserIdAndBoardId(java.lang.Long, java.lang.Long)
     */
    @Override
    public List<TaskUserDTO> getTaskUserByUserIdAndBoardId(Long userId, Long boardId) {
        return this.buildTaskWithPeriodicity(taskUserRepository.findAllByUserIdAndTaskBoardId(userId, boardId));
    }


    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserService#getTaskUserByUserId(java.lang.Long)
     */
    @Override
    public List<TaskUserDTO> getTaskUserByUserId(Long userId) {
        return this.buildTaskWithPeriodicity(taskUserRepository.findAllByUserId(userId));
    }


    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserService#getTaskUserByBoardId(java.lang.Long)
     */
    @Override
    public List<TaskUserDTO> getTaskUserByBoardId(Long boardId) {
        return this.buildTaskWithPeriodicity(taskUserRepository.findAllByTaskBoardId(boardId));
    }


    /**
     * Méthode qui va constuire une liste de tache avec la gestion de la périodicité
     *
     * @param taskUser
     * @return liste de TaskUserDTO
     */
    private List<TaskUserDTO> buildTaskWithPeriodicity(List<TaskUser> taskUser) {
        return taskUser.stream().map((TaskUser tu) -> {
            TaskUserDTO taskUserDTO = (TaskUserDTO) transformers.convertEntityToDto(tu, TaskUserDTO.class);
            Periodicity period = tu.getTask().getPeriodicity();
            TaskWithPeriodDTO taskWithPeriod = taskUserDTO.getTask();
            taskWithPeriod.setBoardId(tu.getTask().getBoard().getId());
            taskWithPeriod.setDateEnd(tu.getTask().getDateEnd());
            /* Si pas de periode alors la tache est classique */
            if (period == null) {
                taskWithPeriod.setDateBeginTask(taskWithPeriod.getDateBegin());
                taskWithPeriod.setDateEndTask(taskWithPeriod.getDateEnd());
            } else {
                //On récupere la premiere date de la périodicité
                Calendar dateBeginPeriodicity = new GregorianCalendar();
                dateBeginPeriodicity.setTime(period.getDateBegin());

                Calendar dateBegin = new GregorianCalendar();
                dateBegin.setTime(new Date());

                Calendar dateEnd = new GregorianCalendar();
                dateEnd.setTime(taskWithPeriod.getDateEnd());

                Calendar dateUpdatePeriodicity = new GregorianCalendar();
                if (period.getPeriodicityDateUpdate() != null) {
                    dateUpdatePeriodicity.setTime(period.getPeriodicityDateUpdate());
                }


                Boolean test = Boolean.FALSE;
                do {
                    if (dateBegin.before(dateBeginPeriodicity) && (period.getPeriodicityDateUpdate() == null || (period.getPeriodicityDateUpdate() != null && dateUpdatePeriodicity.before(dateBeginPeriodicity)))) {
                        test = Boolean.TRUE;
                    } else {
                        this.addPeriodicityDate(dateBeginPeriodicity, period, 1);
                    }

                } while (dateBeginPeriodicity.before(dateEnd) && !test);

                // SI la dateBeginPeriodicity est avant la date de fin alors en prend la date de la premiere occurence (pour la date de fin)
                // et la date de -1 occurrence pour la date de debut
                if (dateBeginPeriodicity.before(dateEnd)) {
                    taskWithPeriod.setDateEndTask((Date) dateBeginPeriodicity.getTime().clone());
                    this.addPeriodicityDate(dateBeginPeriodicity, period, -1);
                    taskWithPeriod.setDateBeginTask(dateBeginPeriodicity.getTime());
                    taskUserDTO.setTask(taskWithPeriod);
                } else {
                    // SI la dateBeginPeriodicity est avant la date de fin alors en prend la date de l'occurence -1 (pour la date de fin)
                    // et la date de -2 occurrence pour la date de debut
                    this.addPeriodicityDate(dateBeginPeriodicity, period, -1);
                    taskWithPeriod.setDateEndTask((Date) dateBeginPeriodicity.getTime().clone());
                    this.addPeriodicityDate(dateBeginPeriodicity, period, -1);
                    taskWithPeriod.setDateBeginTask(dateBeginPeriodicity.getTime());
                    taskUserDTO.setTask(taskWithPeriod);
                }

		// Test sur les valeurs de la tasks, on va remettre le statut a TODO pour chaque nouvelle occurence de la periodicity.
		if( taskWithPeriod.getDateBeginTask().after(dateUpdatePeriodicity.getTime())){
			tu.setStatus(StatusEnum.TODO);
			tu.getTask().getPeriodicity().setPeriodicityDateUpdate(new Date());
			taskUserRepository.save(tu);
		}
            }
            return taskUserDTO;
        })
                .filter((TaskUserDTO tud) -> tud != null).collect(Collectors.toList());
    }

    /**
     * Ajoute une période à une date par rapport a sa pérodicité
     *
     * @param date
     * @param periodicity
     * @param negativeFrequence
     */
    private void addPeriodicityDate(Calendar date, Periodicity periodicity, int negativeFrequence) {

        switch (periodicity.getType()) {
            case DAILY:
                date.add(Calendar.DAY_OF_YEAR, periodicity.getFrequency() * negativeFrequence);
                break;
            case MONTHLY:
                date.add(Calendar.MONTH, periodicity.getFrequency() * negativeFrequence);
                break;
            case YEARLY:
                date.add(Calendar.YEAR, periodicity.getFrequency() * negativeFrequence);
                break;
        }
    }


    /*
     * (non-Javadoc)
     * @see com.example.service.TaskService#updateTask(java.lang.Long, java.util.Map)
     */
    @Override
    public TaskUserDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        TaskUser taskUser = taskUserRepository.findOne(id);

        Map<String, Object> taskValues = (Map<String, Object>) values.get("task");

        Task task = taskUser.getTask();

        task.setCreator(userRepository.findOne(new Long((Integer) taskValues.get("creator"))));

        task.setPriority(PriorityEnum.valueOf((String) taskValues.get("priority")));
        taskValues.remove("priority");
        taskValues.remove("taskComments");
        taskValues.remove("tags");
        taskValues.remove("creator");

        Periodicity periodicity = null;
        if (task.getPeriodicity() != null) {
            periodicity = task.getPeriodicity();
        }

        boolean deletePeriodicity = false;
        if (!(Boolean) taskValues.get("isPeriodicity") && task.getPeriodicity() != null) {
            deletePeriodicity = true;
        }


        if ((Boolean) taskValues.get("isPeriodicity")) {
            if (periodicity == null)
                periodicity = new Periodicity();

            Map<String, Object> periodicityValues = (Map<String, Object>) taskValues.get("periodicity");

            periodicityValues.remove("periodicityChain");
            periodicityValues.remove("periodicityDateUpdate");

            periodicity.setType(PeriodicityEnum.valueOf((String) periodicityValues.get("type")));
            periodicityValues.remove("type");
            BeanUtils.populate(periodicity, periodicityValues);

            periodicity = periodicityRepository.save(periodicity);
            task.setPeriodicity(periodicity);
        } else {
            task.setPeriodicity(null);
        }
        taskValues.remove("periodicity");

        Board board = boardRepository.findOne(new Long((Integer) taskValues.get("boardId")));
        task.setBoard(board);
        taskValues.remove("boardId");

        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
        BeanUtils.populate(task, taskValues);

        task.setDateCreation(new Date());

        taskUser.setTask(task);

        Map<String, Object> colonneValues = (Map<String, Object>) taskValues.get("colonneKanban");
        ColonneKanban colonneKanban = colonneKanbanRepository.findOne(new Long((Integer) colonneValues.get("id")));
        taskUser.setColonneKanban(colonneKanban);

        taskUser = taskUserRepository.save(taskUser);

        if (deletePeriodicity) {
            periodicityRepository.delete(periodicity.getId());
        }

        List<TaskUserDTO> listDTO = buildTaskWithPeriodicity(Collections.singletonList(taskUser));

        return listDTO.get(0);
    }

    @Override
    public TaskUserDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        TaskUser taskUser = new TaskUser();
        Task task = new Task();

        task = taskRepository.save(task);

        taskUser.setTask(task);
        taskUser = taskUserRepository.save(taskUser);

        return updateTask(taskUser.getId(), values);
    }

    @Override
    public void deleteTask(Long id) {
        taskUserRepository.delete(id);
    }

	@Override
	public TaskUserDTO updateColumnTask(Long idtaskUser, Long idColumn) {
		TaskUser tu = taskUserRepository.findOne(idtaskUser);
		ColonneKanban col = null;
		if( idColumn != null){
			col = colonneKanbanRepository.findOne(idColumn);
		}
		
		if( tu != null){
			tu.setColonneKanban(col);
			List<TaskUserDTO> listDTO = buildTaskWithPeriodicity(Collections.singletonList(taskUserRepository.save(tu)));
	        return listDTO.get(0);
		}else{
			throw new GameMasterException(ConstanteGameMaster.ELEMENT_NO_FOUND);
		}
	}

	@Override
	public TaskUserDTO updateColumnTask(Long idtaskUser, Long idColumn) {
		TaskUser tu = taskUserRepository.findOne(idtaskUser);
		ColonneKanban col = null;
		if( idColumn != null){
			col = colonneKanbanRepository.findOne(idColumn);
		}
		
		if( tu != null){
			tu.setColonneKanban(col);
			List<TaskUserDTO> listDTO = buildTaskWithPeriodicity(Collections.singletonList(taskUserRepository.save(tu)));
	        return listDTO.get(0);
		}else{
			throw new GameMasterException(ConstanteGameMaster.ELEMENT_NO_FOUND);
		}
	}
}
