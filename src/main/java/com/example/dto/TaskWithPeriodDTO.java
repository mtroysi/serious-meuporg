package com.example.dto;

import java.util.Date;

import com.example.enumeration.PriorityEnum;
import com.example.enumeration.StatusEnum;


public class TaskWithPeriodDTO extends TaskDTO {
	
    private StatusEnum status;
    private Double durationReel;
    private Date dateBegin;
    private Date dateEnd;
    private Date dateBeginTask; /* Valeur reel de la tache (gestion de la périodicity) */
    private Date dateEndTask; /* Valeur reel de la tache (gestion de la périodicity) */
    private Boolean isPeriodicity = false;
    private PeriodicityDTO periodicity;
        
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Double getDurationReel() {
		return durationReel;
	}

	public void setDurationReel(Double durationReel) {
		this.durationReel = durationReel;
	}

	public Date getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateBeginTask() {
		return dateBeginTask;
	}

	public void setDateBeginTask(Date dateBeginTask) {
		this.dateBeginTask = dateBeginTask;
	}

	public Date getDateEndTask() {
		return dateEndTask;
	}

	public void setDateEndTask(Date dateEndTask) {
		this.dateEndTask = dateEndTask;
	}

	public Boolean getIsPeriodicity() {
		return isPeriodicity;
	}

	public void setIsPeriodicity(Boolean isPeriodicity) {
		this.isPeriodicity = isPeriodicity;
	}
	
}
