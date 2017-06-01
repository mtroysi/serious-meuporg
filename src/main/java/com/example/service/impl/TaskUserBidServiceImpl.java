package com.example.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConstanteGameMaster;
import com.example.dto.BidDTO;
import com.example.dto.TaskDTO;
import com.example.dto.TaskUserBidDTO;
import com.example.dto.UserDTO;
import com.example.enumeration.StatusEnum;
import com.example.enumeration.TypeNotifEnum;
import com.example.exception.GameMasterException;
import com.example.model.Board;
import com.example.model.Notification;
import com.example.model.Task;
import com.example.model.TaskUser;
import com.example.model.TaskUserBid;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.NotificationRepository;
import com.example.repository.TaskRepository;
import com.example.repository.TaskUserBidRepository;
import com.example.repository.UserRepository;
import com.example.service.TaskUserBidService;
import com.example.service.UserService;
import com.example.transformers.Transformers;

@Service
public class TaskUserBidServiceImpl implements TaskUserBidService {

    @Autowired
    private TaskRepository taskRepo;

    @Autowired
    private BoardRepository boardRepo;

    @Autowired
    private NotificationRepository notifRepo;

    @Autowired
    private TaskUserBidRepository taskUserBidRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private Transformers transformers;

    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserBidService#getTaskUserBidByBoardAndUser(java.lang.Long, java.lang.Long)
     */
    @Override
    public List<TaskUserBidDTO> getTaskUserBidByBoardAndUser(Long idBoard, Long idUser) {

        List<Task> listTask = taskRepo.findTaskBidByBoardIdAndDate(idBoard, new Date());
        User user = userRepo.findOne(idUser);
        if (user != null) {
            UserDTO userDto = (UserDTO) transformers.convertEntityToDto(user, UserDTO.class);

            return listTask.stream().map((Task task) -> {
                TaskUserBid taskUserBid = task.getTaskUserBids().stream()
                        .filter((TaskUserBid tub) -> tub.getUser().getId().equals(user.getId())).findFirst()
                        .orElse(null);
                if (taskUserBid == null) {
                    return new TaskUserBidDTO((TaskDTO) transformers.convertEntityToDto(task, TaskDTO.class), null,
                            null);
                } else {
                    return new TaskUserBidDTO((TaskDTO) transformers.convertEntityToDto(task, TaskDTO.class), userDto,
                            taskUserBid.getDuration());
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserBidService#getTaskUserBidEndByBoard(java.lang.Long)
     */
    @Override
    public List<TaskUserBidDTO> getTaskUserBidEndByBoard(Long idBoard) {
        return taskUserBidRepo.findByTaskBoardIdAndTaskDateEndBidBefore(idBoard, new Date()).stream()
                .map((TaskUserBid tub) -> {
                    return (TaskUserBidDTO) transformers.convertEntityToDto(tub, TaskUserBidDTO.class);
                }).collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserBidService#addOrUpdateTaskUserBid(java.lang.Long, java.lang.Double)
     */
    @Override
    public TaskUserBidDTO addOrUpdateTaskUserBid(Long idTask, Double duration) {
        User user = userService.getCurrentUser();

        TaskUserBid tub = taskUserBidRepo.findByTaskIdAndUserId(idTask, user.getId());

        if (tub != null) {
            tub.setDuration(duration);
            tub = taskUserBidRepo.save(tub);
        } else {
            Task task = taskRepo.findOne(idTask);
            if (task != null) {
                TaskUserBid tub_new = new TaskUserBid();
                tub_new.setDuration(duration);
                tub_new.setTask(task);
                tub_new.setUser(user);
                tub = taskUserBidRepo.save(tub_new);
            } else {
                throw new GameMasterException(ConstanteGameMaster.TASK_NOT_FOUND_ERROR);
            }
        }

        return (TaskUserBidDTO) transformers.convertEntityToDto(tub, TaskUserBidDTO.class);
    }

    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserBidService#addNewTaskInBid(java.util.List, java.lang.Long)
     */
    @Override
    public List<TaskUserBidDTO> addNewTaskInBid(List<Long> listTaskId, Long dateEnd) {
        List<TaskUserBidDTO> list = new ArrayList<>();

        listTaskId.stream().forEach((Long id) -> {
            Task task = taskRepo.findOne(id);
            task.setBid(true);
            task.setDateEndBid(new Date(dateEnd));
            list.add(new TaskUserBidDTO((TaskDTO) transformers.convertEntityToDto(taskRepo.save(task), TaskDTO.class),
                    null, null));
        });
        return list;
    }


    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserBidService#validBidByBoard(java.lang.Long, java.util.List)
     */
    @Override
    public void validBidByBoard(Long idBoard, List<BidDTO> listBidDTO) {
        Board board = boardRepo.findOne(idBoard);

        listBidDTO.stream().forEach((BidDTO bid) -> {
            Task task = taskRepo.findOne(bid.getIdTask());

            if (task != null) {
            	//Create TaskUser
                TaskUser taskUser = new TaskUser();
                taskUser.setColonneKanban(null);
                taskUser.setDateBegin(new Date());
                taskUser.setDurationReel(null);
                taskUser.setStatus(StatusEnum.TODO);
                taskUser.setTask(task);
                
                List<User> listuser= bid.getListUserId().stream().map((Long idUser) -> {
                    User user = userRepo.findOne(idUser);
                    if (user != null) {
                        // Add Exp + Level + Money
                        userService.manageMoneyExpUser(user, board.getMoneyWinBid(), board.getExpWinBid());
                        // Add notif
                        this.createNotifWinBid(task, user);
                        return user;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        		taskUser.setUser(listuser);
        		
        		// ajout de la taskUser à la tache
            	task.addTaskUsers(taskUser);
            	
                // modification de la task (duration + isBid + dateEndBid
                task.setIsBid(false);
                task.setDateEndBid(null);
                task.setDuration(bid.getDuration());

                // suppression des enchères dans la BDD
                task.getTaskUserBids().clear();

                taskRepo.save(task);
            }
        });
    }

    /**
     * Create notif (win bid)
     *
     * @param task
     * @param user
     */
    private void createNotifWinBid(Task task, User user) {
        Notification notif = new Notification();
        notif.setContent(ConstanteGameMaster.WIN_BID_CONTENT + " " + task.getTitle());
        notif.setTitle(ConstanteGameMaster.WIN_BID_TITLE);
        notif.setDateCreation(new Date());
        notif.setIsRead(false);
        notif.setType(TypeNotifEnum.information);
        notif.setUser(user);
        notifRepo.save(notif);
    }

}
