package com.example;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.enumeration.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enumeration.PeriodicityEnum;
import com.example.enumeration.PriorityEnum;
import com.example.enumeration.StatusEnum;
import com.example.model.Board;
import com.example.model.BoardUser;
import com.example.model.ColonneKanban;
import com.example.model.Periodicity;
import com.example.model.Role;
import com.example.model.Task;
import com.example.model.TaskUser;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.BoardUserRepository;
import com.example.repository.ColonneKanbanRepository;
import com.example.repository.PeriodicityRepository;
import com.example.repository.RoleRepository;
import com.example.repository.TaskRepository;
import com.example.repository.TaskUserRepository;
import com.example.repository.UserRepository;

@RestController
public class CreateTestData {

	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRepository roleRepository;
	
	 @Autowired
	 private PeriodicityRepository periodicityRepository;
	
	 @Autowired
	 private ColonneKanbanRepository colonneKanbanRepository;
	
	 @Autowired
	 private TaskRepository taskRepository;
	
	 @Autowired
	 private TaskUserRepository taskUserRepository;
	
	 @Autowired
	 private BoardRepository boardRepository;
	
	 @Autowired
	 private BoardUserRepository boardUserRepository;
	
	 private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	 private Role role1;
	 
	 private Role role2;
	 
	 private User user1;
	 
	 private Periodicity period1;
	 
	 private Periodicity period2;
	 
	 private Periodicity period3;
	 
	 private Board board1;
	 
	 private BoardUser boardUser1;

	private ColonneKanban col1;

	private ColonneKanban col2;

	private ColonneKanban col3;

	private Task task1;

	private Task task2;

	private Task task3;

	private TaskUser taskUser1;

	private TaskUser taskUser2;

	private TaskUser taskUser3;
	
	 
	@RequestMapping("/tools/createTestData")
	public String run() throws ParseException, ScriptException, SQLException, ParseException {
		// Suppression de tous les éléments de la base
		deleteAll();
		
		
		/* CREATION ROLE */
		Role role1 = new Role();
		role1.setId(1L);
		role1.setVersion(1);
		role1.setCode(RoleEnum.ADMIN);
		this.role1 = this.roleRepository.save(role1);
		
		
		Role role2 = new Role();
		role2.setId(2L);
		role2.setVersion(1);
		role2.setCode(RoleEnum.USER);
		this.role2 = this.roleRepository.save(role2);
		
		
		
		/* CREATION UTILISATEUR */
		User user1 = new User();
		user1.setId(1L);
		user1.setVersion(1);
		user1.setDateCreation(new Date());
		user1.setEmail("user@user.fr");
		user1.setFirstName("User");
		user1.setLastName("UserLastName");
		user1.setPassword("user");
		user1.setBoardUsers(null);
		user1.setTaskUserBids(null);
		user1.setTaskUsers(null);
		this.user1 = this.userRepository.save(user1);

		
		
		
		/* CREATION PERIODICITY */
		Periodicity period1 = new Periodicity();
		period1.setId(1L);
		period1.setDateBegin(new Date(sdf.parse("02/02/2016").getTime()));
		period1.setFrequency(5);
		period1.setPeriodicityChain(null);
		period1.setPeriodicityDateUpdate(new Date(sdf.parse("25/06/2016").getTime()));
		period1.setType(PeriodicityEnum.DAILY);
		period1.setVersion(1);
		this.period1 = this.periodicityRepository.save(period1);
		
		Periodicity period2 = new Periodicity();
		period2.setId(2L);
		period2.setDateBegin(new Date(sdf.parse("01/02/2016").getTime()));
		period2.setFrequency(1);
		period2.setPeriodicityChain(null);
		period2.setPeriodicityDateUpdate(null);
		period2.setType(PeriodicityEnum.DAILY);
		period2.setVersion(1);
		this.period2 = this.periodicityRepository.save(period2);
		
		Periodicity period3 = new Periodicity();
		period3.setId(3L);
		period3.setDateBegin(new Date(sdf.parse("01/02/2016").getTime()));
		period3.setFrequency(3);
		period3.setPeriodicityChain(null);
		period3.setPeriodicityDateUpdate(null);
		period3.setType(PeriodicityEnum.MONTHLY);
		period3.setVersion(1);
		this.period3 = this.periodicityRepository.save(period3);
		
		
		
		
		
		/* CREATION BOARD */
		Board board1 = new Board();
		board1.setId(1L);
		board1.setBoardUsers(null);
		board1.setColonneKanbans(null);
		board1.setColor("red");
		board1.setCreator(this.user1);
		board1.setDateCreation(new Date());
		board1.setName("Tableau Rouge");
		board1.setTasks(null);
		board1.setVersion(1);
		this.board1 = this.boardRepository.save(board1);
		
		
		
		
		/* CREATION BOARDUSER */	
		BoardUser boardUser1 = new BoardUser();
		boardUser1.setId(1L);
		boardUser1.setBoard(this.board1);
		boardUser1.setRole(this.role1);
		boardUser1.setUser(this.user1);
		boardUser1.setVersion(1);
		this.boardUser1 = this.boardUserRepository.save(boardUser1);
		
		
		
		/* CREATION COLONNE KANBAN */
		ColonneKanban col1 = new ColonneKanban();
		col1.setId(1L);
		col1.setVersion(1);
		col1.setBoard(this.board1);
		col1.setColor("red");
		col1.setIsDefault(true);
		col1.setStatus(StatusEnum.TODO);
		col1.setTitle("TODO");
		this.col1 = colonneKanbanRepository.save(col1);
		
		ColonneKanban col2 = new ColonneKanban();
		col2.setId(2L);
		col2.setVersion(1);
		col2.setBoard(this.board1);
		col2.setColor("red");
		col2.setIsDefault(false);
		col2.setStatus(StatusEnum.IN_PROGRESS);
		col2.setTitle("IN_PROGRESS");
		this.col2 = colonneKanbanRepository.save(col2);
		
		ColonneKanban col3 = new ColonneKanban();
		col3.setId(3L);
		col3.setVersion(1);
		col3.setBoard(this.board1);
		col3.setColor("red");
		col3.setIsDefault(false);
		col3.setStatus(StatusEnum.DONE);
		col3.setTitle("DONE");
		this.col3 = colonneKanbanRepository.save(col3);
		
		
		
		/* CREATION TASK */
		Task task1 = new Task();
		task1.setId(1L);
		task1.setBid(false);
		task1.setBoard(this.board1);
		task1.setChecklists(null);
		task1.setContent("Description de la tache");
		task1.setCreator(this.user1);
		task1.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task1.setDateEndBid(null);
		task1.setDuration(5.00);
		task1.setIsBid(false);
		task1.setLinks(null);
		task1.setPeriodicity(this.period1);
		task1.setPriority(PriorityEnum.URGENT_IMPORTANT);
		task1.setTags(null);
		task1.setTaskParents(null);
		task1.setTaskUserBids(null);
		task1.setTaskUsers(null);
		task1.setTitle("Titre de la tache 1");
		task1.setVersion(1);
		this.task1 = taskRepository.save(task1);
		
		Task task2 = new Task();
		task2.setId(2L);
		task2.setBid(false);
		task2.setBoard(this.board1);
		task2.setChecklists(null);
		task2.setContent("Description de la tache 2");
		task2.setCreator(this.user1);
		task2.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task2.setDateEndBid(null);
		task2.setDuration(5.00);
		task2.setIsBid(false);
		task2.setLinks(null);
		task2.setPeriodicity(this.period2);
		task2.setPriority(PriorityEnum.URGENT_IMPORTANT);
		task2.setTags(null);
		task2.setTaskParents(null);
		task2.setTaskUserBids(null);
		task2.setTaskUsers(null);
		task2.setTitle("Titre de la tache 2");
		task2.setVersion(1);
		this.task2 = taskRepository.save(task2);
		
		Task task3 = new Task();
		task3.setId(3L);
		task3.setBid(false);
		task3.setBoard(this.board1);
		task3.setChecklists(null);
		task3.setContent("Description de la tache 3");
		task3.setCreator(this.user1);
		task3.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task3.setDateEndBid(null);
		task3.setDuration(5.00);
		task3.setIsBid(false);
		task3.setLinks(null);
		task3.setPeriodicity(null);
		task3.setPriority(PriorityEnum.NOT_URGENT_IMPORTANT);
		task3.setTags(null);
		task3.setTaskParents(null);
		task3.setTaskUserBids(null);
		task3.setTaskUsers(null);
		task3.setTitle("Titre de la tache 3 (without)");
		task3.setVersion(1);
		this.task3 = taskRepository.save(task3);
		
		
		
		
		/* CREATION TASKUSER */
		TaskUser taskUser1 = new TaskUser();
		taskUser1.setId(1L);
		taskUser1.setDateBegin(new Date(sdf.parse("12/01/2016").getTime()));
		taskUser1.setDateEnd(new Date(sdf.parse("31/03/2016").getTime()));
		taskUser1.setDurationReel(null);
		taskUser1.setStatus(StatusEnum.IN_PROGRESS);
		taskUser1.setTask(this.task1);
		taskUser1.setUser(this.user1);
		taskUser1.setVersion(1);
		this.taskUser1 = taskUserRepository.save(taskUser1);
		
		TaskUser taskUser2 = new TaskUser();
		taskUser2.setId(2L);
		taskUser2.setDateBegin(new Date(sdf.parse("12/01/2016").getTime()));
		taskUser2.setDateEnd(new Date(sdf.parse("28/05/2017").getTime()));
		taskUser2.setDurationReel(null);
		taskUser2.setStatus(StatusEnum.IN_PROGRESS);
		taskUser2.setTask(this.task2);
		taskUser2.setUser(this.user1);
		taskUser2.setVersion(1);
		this.taskUser2 = taskUserRepository.save(taskUser2);
		
		TaskUser taskUser3 = new TaskUser();
		taskUser3.setId(3L);
		taskUser3.setDateBegin(new Date(sdf.parse("12/01/2016").getTime()));
		taskUser3.setDateEnd(new Date(sdf.parse("30/06/2017").getTime()));
		taskUser3.setDurationReel(null);
		taskUser3.setStatus(StatusEnum.IN_PROGRESS);
		taskUser3.setTask(this.task3);
		taskUser3.setUser(this.user1);
		taskUser3.setVersion(1);
		this.taskUser3 = taskUserRepository.save(taskUser3);
		
		return "FINI";
	}
	
	@Transactional
	public void deleteAll() {
		this.boardUserRepository.deleteAll();
		this.taskUserRepository.deleteAll();
		this.boardRepository.deleteAll();
		this.colonneKanbanRepository.deleteAll();
		this.taskRepository.deleteAll();
		this.periodicityRepository.deleteAll();
		this.userRepository.deleteAll();
		this.roleRepository.deleteAll();
	}
}
