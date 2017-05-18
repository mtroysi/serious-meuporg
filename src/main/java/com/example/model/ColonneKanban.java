package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.enumeration.PriorityEnum;
import com.example.enumeration.StatusEnum;

/**
 * Created by Adrien CASELLES on 11/05/17.
 */

@Entity(name = "colonnekanban")
@Table(name = "colonnekanban")
public class ColonneKanban extends CommonEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "color")
    private String color;

    @Column(name = "status", nullable = true)
    private StatusEnum status;
    
    @ManyToOne
    @JoinColumn(name = "board", nullable = true)
    private Board board;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
