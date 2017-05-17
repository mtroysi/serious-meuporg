package com.example.dto;

import java.util.Date;

import javax.persistence.Column;

import com.example.enumeration.PeriodicityEnum;


public class PeriodicityDTO {

	private PeriodicityEnum type;
	private Integer frequency;
	private Date dateBegin;
	private String periodicityChain;
    private Date periodicityDateUpdate;

}
