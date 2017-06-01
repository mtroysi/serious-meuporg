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
import com.example.dto.TaskUserBidDTO;
import com.example.dto.TaskUserDTO;
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
import com.example.repository.TaskUserRepository;
import com.example.repository.UserRepository;
import com.example.service.TaskUserBidService;
import com.example.service.UserService;
import com.example.transformers.Transformers;

@Service
public class TaskUserBidServiceImpl implements TaskUserBidService {

    @Autowired
    private TaskRepository taskRepo;
    
    @Autowired
    private TaskUserRepository taskUserRepo;

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

        List<TaskUser> listTaskUser = taskUserRepo.findTaskUserBidByBoardIdAndDate(idBoard, new Date());
        User user = userRepo.findOne(idUser);
        if (user != null) {
            UserDTO userDto = (UserDTO) transformers.convertEntityToDto(user, UserDTO.class);

            return listTaskUser.stream().map((TaskUser taskUser) -> {
                TaskUserBid taskUserBid = taskUser.getTaskUserBids().stream()
                		 .filter((TaskUserBid tub) -> tub.getUser().getId().equals(user.getId())).findFirst()
                		 .orElse(null);
                
                
                if (taskUserBid == null) {
                    return new TaskUserBidDTO((TaskUserDTO) transformers.convertEntityToDto(taskUser, TaskUserDTO.class), null,
                            null);
                } else {
                    return new TaskUserBidDTO((TaskUserDTO) transformers.convertEntityToDto(taskUser, TaskUserDTO.class), userDto,
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
        return taskUserBidRepo.findByTaskUserTaskBoardIdAndTaskUserTaskDateEndBidBefore(idBoard, new Date()).stream()
                .map((TaskUserBid tub) -> {
                    return (TaskUserBidDTO) transformers.convertEntityToDto(tub, TaskUserBidDTO.class);
                }).collect(Collectors.toList());
    }

    /*
     * (non-Javadoc)
     * @see com.example.service.TaskUserBidService#addOrUpdateTaskUserBid(java.lang.Long, java.lang.Double)
     */
    @Override
    public TaskUserBidDTO addOrUpdateTaskUserBid(Long idTaskUser, Double duration) {
        User user = userService.getCurrentUser();

        TaskUserBid tub = taskUserBidRepo.findByTaskUserIdAndUserId(idTaskUser, user.getId());

        if (tub != null) {
            tub.setDuration(duration);
            tub = taskUserBidRepo.save(tub);
        } else {
            TaskUser taskuser = taskUserRepo.findOne(idTaskUser);
            if (taskuser != null) {
                TaskUserBid tub_new = new TaskUserBid();
                tub_new.setDuration(duration);
                tub_new.setTaskUser(taskuser);
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
    public List<TaskUserBidDTO> addNewTaskInBid(List<Long> listIdTaskUsers, Long dateEnd) {
        List<TaskUserBidDTO> list = new ArrayList<>();

        listIdTaskUsers.stream().forEach((Long id) -> {
            TaskUser taskUser = taskUserRepo.findOne(id);
            taskUser.getTask().setBid(true);
            taskUser.getTask().setDateEndBid(new Date(dateEnd));
            list.add(new TaskUserBidDTO((TaskUserDTO) transformers.convertEntityToDto(taskUserRepo.save(taskUser), TaskUserDTO.class),
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
            TaskUser taskUser = taskUserRepo.findOne(bid.getIdTaskUser());

            if (taskUser != null) {
            	//Update taskUser
                taskUser.setDateBegin(new Date());
                taskUser.setDurationReel(null);
                taskUser.setStatus(StatusEnum.TODO);
                
                List<User> listuser= bid.getListUserId().stream().map((Long idUser) -> {
                    User user = userRepo.findOne(idUser);
                    if (user != null) {
                        // Add Exp + Level + Money
                        userService.manageMoneyExpUser(user, board.getMoneyWinBid(), board.getExpWinBid());
                        // Add notif
                        this.createNotifWinBid(taskUser, user);
                        return user;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        		taskUser.setUser(listuser);
        		
            	
                // modification de la task (duration + isBid + dateEndBid
        		taskUser.getTask().setIsBid(false);
        		taskUser.getTask().setDateEndBid(null);
        		taskUser.getTask().setDuration(bid.getDuration());

                // suppression des enchères dans la BDD
        		taskUser.getTaskUserBids().clear();

                taskUserRepo.save(taskUser);
            }
        });
    }

    /**
     * Create notif (win bid)
     *
     * @param task
     * @param user
     */
    private void createNotifWinBid(TaskUser taskUser, User user) {
        Notification notif = new Notification();
        notif.setContent(ConstanteGameMaster.WIN_BID_CONTENT + " " + taskUser.getTask() != null ? taskUser.getTask().getTitle() : "");
        notif.setTitle(ConstanteGameMaster.WIN_BID_TITLE);
        notif.setDateCreation(new Date());
        notif.setIsRead(false);
        notif.setType(TypeNotifEnum.information);
        notif.setUser(user);
        notifRepo.save(notif);
    }

}
