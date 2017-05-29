package com.example.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.CommentDTO;
import com.example.model.Comment;
import com.example.model.Task;
import com.example.model.User;
import com.example.repository.CommentRepository;
import com.example.repository.TaskRepository;
import com.example.repository.UserRepository;
import com.example.service.CommentService;
import com.example.transformers.Transformers;

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

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.CommentService#findComment(java.lang.Long)
     */
    @Override
    public CommentDTO findComment(Long id) {
        return (CommentDTO) transformers.convertEntityToDto(commentRepository.findOne(id), CommentDTO.class);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.CommentService#addCommentToTask(java.lang.Long, java.util.Map, java.lang.Long)
     */
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

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.CommentService#deleteComment(java.lang.Long)
     */
    @Override
    public void deleteComment(Long id) {
        commentRepository.delete(id);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.CommentService#updateComment(java.lang.Long, java.util.Map)
     */
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
