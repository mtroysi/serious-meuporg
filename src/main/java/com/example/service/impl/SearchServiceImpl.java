package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.ResultDto;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.service.SearchService;
import com.example.transformers.Transformers;

/**
 * Created by Morgane TROYSI on 30/05/17.
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    private Transformers transformers;

    /**
     * @see com.example.service.SearchService#searchUsersAndTasks(String)
     */
    @Override
    public List<ResultDto> searchUsersAndTasks(String query) {
        List<User> users = userRepository.findByFirstNameContainingOrLastNameContaining(query);
        List<ResultDto> result = new ArrayList<>();
        for (User user : users) {
            ResultDto resultDto = transformers.transformUserToResultDto(user);
            result.add(resultDto);
        }

        List<Task> tasks = taskRepository.findByTitleContaining(query);
        for (Task task : tasks) {
            ResultDto resultDto = transformers.transformTaskToResultDto(task);
            result.add(resultDto);
        }

        return result;
    }
}
