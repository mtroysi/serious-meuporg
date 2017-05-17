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
import com.example.enumeration.PeriodicityEnum;
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
    
    
	@Override
	public List<TaskUserDTO> getTaskUserByUserIdAndBoardId(Long userId, Long boardId) {
		return this.buildTaskWithPeriodicity(taskUserRepository.findAllByUserIdAndTaskBoardId(userId, boardId));
	}

	@Override
	public List<TaskUserDTO> getTaskUserByBoardId(Long boardId) {
		return this.buildTaskWithPeriodicity(taskUserRepository.findByTaskBoardId(boardId));
	}
	
	
	private List<TaskUserDTO> buildTaskWithPeriodicity(List<TaskUser> taskUser){		
		return taskUser.stream().map((TaskUser tu) -> {
			TaskUserDTO taskUserDTO = (TaskUserDTO)transformers.convertEntityToDto(tu, TaskUserDTO.class);
			Periodicity period = tu.getTask().getPeriodicity();
			TaskWithPeriodDTO  taskWithPeriod = taskUserDTO.getTask();
			taskWithPeriod.setDateEnd(tu.getDateEnd());
			/* Si pas de periode alors la tache est classique */
			if( period == null ){
				taskWithPeriod.setDateBeginTask(taskWithPeriod.getDateBegin());
				taskWithPeriod.setDateEndTask(taskWithPeriod.getDateEnd());
			}else{
				//On récupere la premiere date de la périodicité
				Calendar dateBeginPeriodicity = new GregorianCalendar();
				dateBeginPeriodicity.setTime(period.getDateBegin());
				
				Calendar dateBegin = new GregorianCalendar();
				dateBegin.setTime(period.getPeriodicityDateUpdate() != null ? period.getPeriodicityDateUpdate() : taskWithPeriod.getDateBegin());
				
				Calendar dateEnd = new GregorianCalendar();
				dateEnd.setTime(taskWithPeriod.getDateEnd());

				Calendar dateUpdatePeriodicity = new GregorianCalendar();
				if( period.getPeriodicityDateUpdate() != null){
					dateUpdatePeriodicity.setTime(period.getPeriodicityDateUpdate());
				}
				
				
				Boolean test = Boolean.FALSE;
				System.out.println("---->>>>>>>>>>>>>>>>>>>>>>>>>>>>>><" + taskWithPeriod.getId());
				do {
					/*System.out.println("--------------");
					System.out.println("dateBegin -" + dateBegin.getTime().toString());
					System.out.println("dateBeginPeriodicity -" + dateBeginPeriodicity.getTime().toString());
					System.out.println("getDateEnd -" + dateEnd.getTime().toString());
					System.out.println("if -" + dateBegin.after(dateBeginPeriodicity));*/
					
					if(dateBegin.before(dateBeginPeriodicity) && (period.getPeriodicityDateUpdate() == null || (period.getPeriodicityDateUpdate() != null && dateUpdatePeriodicity.before(dateBeginPeriodicity)))){
						test = Boolean.TRUE;
					}
					else{
						this.addPeriodicityDate(dateBeginPeriodicity, period);
					}
					/*System.out.println("dateBegin -" + dateBegin.getTime().toString());
					System.out.println("while 1 -" + dateBegin.before(dateBeginPeriodicity));
					System.out.println("while 2 -" + !test);
					System.out.println("------------");*/
					
				} while ( dateBeginPeriodicity.before(dateEnd) && !test);
				
				if(dateBeginPeriodicity.before(dateEnd)){
					taskWithPeriod.setDateBeginTask(dateBeginPeriodicity.getTime());
					taskWithPeriod.setDateEndTask(taskWithPeriod.getDateEnd());
					taskUserDTO.setTask(taskWithPeriod);
				}else{
					taskUserDTO = null;
				}
				
			}
			

			System.out.println("DEBUT  -" + taskWithPeriod.getTitle());
			System.out.println("DEBUT TASK -" + taskWithPeriod.getDateBeginTask().toString());
			System.out.println("DEBUT  -" + taskWithPeriod.getDateBegin().toString());
			System.out.println("FIN TASK -" + taskWithPeriod.getDateEnd().toString());
			System.out.println("**************************************" + taskWithPeriod.getId());
				
			return taskUserDTO;
		})
		.filter((TaskUserDTO tud) -> tud != null).collect(Collectors.toList());
	}
	
	
	private void addPeriodicityDate(Calendar date, Periodicity periodicity){
		
		switch(periodicity.getType()){
		case DAILY:
			date.add(Calendar.DAY_OF_YEAR, periodicity.getFrequency());
			break;
		case MONTHLY:
			date.add(Calendar.MONTH, periodicity.getFrequency());
			break;		
		case YEARLY:
			date.add(Calendar.YEAR, periodicity.getFrequency());
			break;		
		}	
	}
   
}
