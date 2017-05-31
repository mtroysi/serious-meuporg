package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;


/**
 * Created by Florentin NOËL on 11/05/17.
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class TaskUserDTO {

    private Long id;
    private TaskWithPeriodDTO task;
    private ArrayList<UserDTO> user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public TaskWithPeriodDTO getTask() {
        return task;
    }

    public void setTask(TaskWithPeriodDTO task) {
        this.task = task;
    }

    public ArrayList<UserDTO> getUser() {
        return user;
    }

    public void setUser(ArrayList<UserDTO> user) {
        this.user = user;
    }
}
