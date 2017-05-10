package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "checklist")
@Table(name = "checklist")
public class Checklist extends CommonEntity {

    @Column(name = "name")
    String name;

    @OneToMany
    List<ChecklistItem> checklistItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChecklistItem> getChecklistItems() {
        return checklistItems;
    }

    public void setChecklistItems(List<ChecklistItem> checklistItems) {
        this.checklistItems = checklistItems;
    }
}
