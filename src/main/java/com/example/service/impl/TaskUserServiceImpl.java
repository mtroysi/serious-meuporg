package com.example.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TaskUserDTO;
import com.example.dto.TaskWithPeriodDTO;
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
    Transformers transformers;

	
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
	 * @param taskUser
	 * @return liste de TaskUserDTO
	 */
	private List<TaskUserDTO> buildTaskWithPeriodicity(List<TaskUser> taskUser){		
		return taskUser.stream().map((TaskUser tu) -> {
			TaskUserDTO taskUserDTO = (TaskUserDTO)transformers.convertEntityToDto(tu, TaskUserDTO.class);
			Periodicity period = tu.getTask().getPeriodicity();
			TaskWithPeriodDTO  taskWithPeriod = taskUserDTO.getTask();
			taskWithPeriod.setBoardId(tu.getTask().getBoard().getId());
			taskWithPeriod.setDateEnd(tu.getTask().getDateEnd());
			/* Si pas de periode alors la tache est classique */
			if( period == null ){
				taskWithPeriod.setDateBeginTask(taskWithPeriod.getDateBegin());
				taskWithPeriod.setDateEndTask(taskWithPeriod.getDateEnd());
			}else{
				//On récupere la premiere date de la périodicité
				Calendar dateBeginPeriodicity = new GregorianCalendar();
				dateBeginPeriodicity.setTime(period.getDateBegin());
				
				Calendar dateBegin = new GregorianCalendar();
				dateBegin.setTime(new Date());
				
				Calendar dateEnd = new GregorianCalendar();
				dateEnd.setTime(taskWithPeriod.getDateEnd());

				Calendar dateUpdatePeriodicity = new GregorianCalendar();
				if( period.getPeriodicityDateUpdate() != null){
					dateUpdatePeriodicity.setTime(period.getPeriodicityDateUpdate());
				}
				
				
				Boolean test = Boolean.FALSE;
				do {
					if(dateBegin.before(dateBeginPeriodicity) && (period.getPeriodicityDateUpdate() == null || (period.getPeriodicityDateUpdate() != null && dateUpdatePeriodicity.before(dateBeginPeriodicity)))){
						test = Boolean.TRUE;
					}
					else{
						this.addPeriodicityDate(dateBeginPeriodicity, period, 1);
					}
					
				} while ( dateBeginPeriodicity.before(dateEnd) && !test);
				
				// SI la dateBeginPeriodicity est avant la date de fin alors en prend la date de la premiere occurence (pour la date de fin)
				// et la date de -1 occurrence pour la date de debut
				if(dateBeginPeriodicity.before(dateEnd)){
					taskWithPeriod.setDateEndTask((Date) dateBeginPeriodicity.getTime().clone());
					this.addPeriodicityDate(dateBeginPeriodicity, period, -1);
					taskWithPeriod.setDateBeginTask(dateBeginPeriodicity.getTime());
					taskUserDTO.setTask(taskWithPeriod);
				}else{
					// SI la dateBeginPeriodicity est avant la date de fin alors en prend la date de l'occurence -1 (pour la date de fin)
					// et la date de -2 occurrence pour la date de debut
					this.addPeriodicityDate(dateBeginPeriodicity, period, -1);
					taskWithPeriod.setDateEndTask((Date) dateBeginPeriodicity.getTime().clone());
					this.addPeriodicityDate(dateBeginPeriodicity, period, -1);
					taskWithPeriod.setDateBeginTask(dateBeginPeriodicity.getTime());
					taskUserDTO.setTask(taskWithPeriod);
				}
				
				// Test sur les valeurs de la tasks, on va remettre le statut a TODO pour chaque nouvelle occurence de la periodicity.
				
			}
			
			
			
			return taskUserDTO;
		})
		.filter((TaskUserDTO tud) -> tud != null).collect(Collectors.toList());
	}
	
	/**
	 * Ajoute une période à une date par rapport a sa pérodicité
	 * @param date
	 * @param periodicity
	 * @param negativeFrequence
	 */
	private void addPeriodicityDate(Calendar date, Periodicity periodicity, int negativeFrequence){
		
		switch(periodicity.getType()){
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
}
