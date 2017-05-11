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
    private String name;

    @Column(name = "value")
    private Boolean value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
