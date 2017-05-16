package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.example.enumeration.PeriodicityEnum;

/**
 * Created by Adrien CASELLES on 11/05/17.
 */
@Entity(name = "periodicity")
@Table(name = "periodicity")
public class Periodicity extends CommonEntity {

	@Column(name = "type")
	private PeriodicityEnum type;

	@Column(name = "frequency")
	private Integer frequency = 1;

	/* used for monthly treatment or yearly */
	@Column(name = "day_number")
	private Integer dayNumber;
	
	/* used for yearly treatment */
	@Column(name = "month_number")
	private Integer monthNumber;
	
	@Column(name = "periodicity_chain")
	private String periodicityChain;

    @Column(name = "periodicity_date_update")
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

	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Integer getMonthNumber() {
		return monthNumber;
	}

	public void setMonthNumber(Integer monthNumber) {
		this.monthNumber = monthNumber;
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
