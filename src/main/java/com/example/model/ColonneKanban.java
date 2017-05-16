package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.enumeration.PriorityEnum;

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

    @Column(name = "priority", nullable = true)
    private PriorityEnum priority;
    
    @ManyToOne
    @JoinColumn(name = "board", nullable = true)
    private Board board;

    @Column(name = "is_default")
    private Boolean isDefault;
}
