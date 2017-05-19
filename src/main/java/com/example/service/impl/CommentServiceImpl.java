package com.example.service.impl;

import com.example.dto.CommentDTO;
import com.example.enumeration.PriorityEnum;
import com.example.model.Comment;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
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
    private UserRepository userRepository;
    @Autowired
    private Transformers transformers;

    @Override
    public CommentDTO findComment(Long id) {
        return (CommentDTO) transformers.convertEntityToDto(commentRepository.findOne(id), CommentDTO.class);
    }

    @Override
    public CommentDTO addCommentToTask(Long id, Map<String, Object> values, Long idUser) throws InvocationTargetException, IllegalAccessException {
        Comment comment = new Comment();
        BeanUtils.populate(comment, values);

        User user = userRepository.findOne(idUser);
        comment.setCreator(user);

        Task task = taskRepository.findOne(id);
        comment.setTask(task);

        return (CommentDTO) transformers.convertEntityToDto(commentRepository.save(comment), CommentDTO.class);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }

    @Override
    public CommentDTO updateComment(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Comment comment = commentRepository.findOne(id);

        Comment newComment = new Comment();
        values.remove("creator");
        BeanUtils.populate(newComment, values);

        comment.setContent(newComment.getContent());

        return (CommentDTO) transformers.convertEntityToDto(commentRepository.save(comment), CommentDTO.class);
    }
}
