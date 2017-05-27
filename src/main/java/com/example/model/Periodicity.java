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

	/**
	 * Type de fréquence journalier / mensuel / annuel
	 */
	@Column(name = "type")
	private PeriodicityEnum type;

	/**
	 * Fréquence
	 */
	@Column(name = "frequency")
	private Integer frequency = 1;

	/**
	 * Date de début
	 */
	@Column(name = "date_begin")
	private Date dateBegin;
	
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
