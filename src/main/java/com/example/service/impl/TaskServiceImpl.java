package com.example.service.impl;

import com.example.dto.TaskDTO;
import com.example.dto.TaskLiteDTO;
import com.example.enumeration.PriorityEnum;
import com.example.model.Tag;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private Transformers transformers;

    /**
     * Crée une tâche
     * @param values données de la tâche à créer
     * @return DTO de la tâche créée
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public TaskDTO createTask(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Task task = new Task();
        BeanUtils.setProperty(task,"priority", PriorityEnum.valueOf((String) values.get("priority")));
        values.remove("priority");
        BeanUtils.populate(task, values);
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskDTO.class);
    }

    /**
     * Retourne toutes les tâches en base de données
     * @return liste de toutes les tâches en base de données
     */
    @Override
    public List<TaskDTO> listAllTask() {
        Iterable<Task> res = taskRepository.findAll();
        List<TaskDTO> list = new ArrayList<>();
        for (Task task : res) {
            TaskDTO dto = (TaskDTO) transformers.convertEntityToDto(task, TaskDTO.class);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retourne la tâche dont l'id est passé en paramètre
     * @param id l'id de la tâche
     * @return la tâche correspondante
     */
    @Override
    public TaskDTO listTask(Long id) {
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.findOne(id), TaskDTO.class);
    }

    /**
     * Modifie une tâche
     * @param id l'id de la tâche à modifier
     * @param values données modifiées
     * @return DTO de la tâche modifiée
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public TaskDTO updateTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Task task = taskRepository.findOne(id);

        task.setPriority(PriorityEnum.valueOf((String) values.get("priority")));
        values.remove("priority");

        values.remove("taskComments");

        BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
        BeanUtils.populate(task, values);
        return (TaskDTO) transformers.convertEntityToDto(taskRepository.save(task), TaskDTO.class);
    }

    /**
     * Supprime une tâche
     * @param id l'id de la tâche à supprimer
     */
    @Override
    public void deleteTask(Long id) {
        taskRepository.delete(id);
    }

    /**
     * Ajoute un tag à une tâche
     * @param id l'id de la tâche
     * @param values données du tag à créer
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public void addTagToTask(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Tag tag = new Tag();
        BeanUtils.populate(tag,values);
        Task task = taskRepository.findOne(id);
        List<Tag> tagList = task.getTags();
        tagList.add(tag);
        task.setTags(tagList);
        taskRepository.save(task);
    }

    /**
     * Retourne toutes les tâches non-assignées d'un tableau
     * @param boardId id du tableau
     * @return tâches non-assignées du tableau
     */
    @Override
    public List<TaskLiteDTO> getTaskWithoutUser(Long boardId){
        List<Task> list = taskRepository.findTaskByUserIsNullAndBoardId(boardId);

        return list.stream().map((Task task) -> (TaskLiteDTO)transformers.convertEntityToDto(task, TaskLiteDTO.class)).collect(Collectors.toList());
    }
}
