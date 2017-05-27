package com.example.model;

import javax.persistence.*;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "checklist_item")
@Table(name = "checklist_item")
public class ChecklistItem extends CommonEntity {

    /**
     * Nom de la checkbox
     */
    @Column(name = "name")
    private String name;

    /**
     * Valeur de la checkbox (true / false)
     */
    @Column(name = "value")
    private Boolean value;

    /**
     * Checklist
     */
    @ManyToOne
    @JoinColumn(name = "checklist_id")
    private Checklist checklist;

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

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }
}
