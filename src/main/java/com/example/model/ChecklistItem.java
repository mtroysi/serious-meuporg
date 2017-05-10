package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "checklist_item")
@Table(name = "checklist_item")
public class ChecklistItem extends CommonEntity {

    @Column(name = "name")
    String name;

    @Column(name = "value")
    Boolean value;
}
