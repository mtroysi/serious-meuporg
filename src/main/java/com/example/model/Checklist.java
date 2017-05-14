package com.example.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgane TROYSI on 10/05/17.
 */

@Entity(name = "checklist")
@Table(name = "checklist")
public class Checklist extends CommonEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "checklist", cascade = CascadeType.ALL)
    private List<ChecklistItem> checklistItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
