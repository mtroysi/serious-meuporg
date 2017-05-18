package com.example.service.impl;

import com.example.dto.CommentDTO;
import com.example.model.Comment;
import com.example.model.Task;
import com.example.repository.CommentRepository;
import com.example.repository.TaskRepository;
import com.example.service.CommentService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by Florentin NOËL on 17/05/17.
 */
@Service

public class CommentServiceImpl implements CommentService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private Transformers transformers;

    @Override
    public CommentDTO addCommentToTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Comment comment = new Comment();
        BeanUtils.populate(comment,values);
        CommentDTO commentDTO = (CommentDTO) transformers.convertEntityToDto(commentRepository.save(comment), CommentDTO.class);

        Task task = taskRepository.findOne(id);
        List<Comment> commentList = task.getTaskComments();
        commentList.add(comment);
        task.setTaskComments(commentList);

        taskRepository.save(task);

        return commentDTO;
    }
}
