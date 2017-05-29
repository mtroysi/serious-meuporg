package com.example.dto;

import java.util.Date;

import com.example.enumeration.PeriodicityEnum;


public class PeriodicityDTO {

	private PeriodicityEnum type;
	private Integer frequency;
	private Date dateBegin;
	private String periodicityChain;
    private Date periodicityDateUpdate;
    
	public PeriodicityEnum getType() {
		return type;
	}
	public void setType(PeriodicityEnum type) {
		this.type = type;
	}
	public Integer getFrequency() {
		return frequency;
	}
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public String getPeriodicityChain() {
		return periodicityChain;
	}
	public void setPeriodicityChain(String periodicityChain) {
		this.periodicityChain = periodicityChain;
	}
	public Date getPeriodicityDateUpdate() {
		return periodicityDateUpdate;
	}
	public void setPeriodicityDateUpdate(Date periodicityDateUpdate) {
		this.periodicityDateUpdate = periodicityDateUpdate;
	}
}
