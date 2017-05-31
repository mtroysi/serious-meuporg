package com.example;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.enumeration.ItemEnum;
import com.example.enumeration.PeriodicityEnum;
import com.example.enumeration.PriorityEnum;
import com.example.enumeration.RoleEnum;
import com.example.enumeration.StatusEnum;
import com.example.enumeration.TypeNotifEnum;
import com.example.model.Board;
import com.example.model.BoardUser;
import com.example.model.ColonneKanban;
import com.example.model.Comment;
import com.example.model.Item;
import com.example.model.Notification;
import com.example.model.Periodicity;
import com.example.model.Role;
import com.example.model.Tag;
import com.example.model.Task;
import com.example.model.TaskUser;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.BoardUserRepository;
import com.example.repository.ColonneKanbanRepository;
import com.example.repository.CommentRepository;
import com.example.repository.ItemRepository;
import com.example.repository.NotificationRepository;
import com.example.repository.PeriodicityRepository;
import com.example.repository.RoleRepository;
import com.example.repository.TagRepository;
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

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private TagRepository tagRepository;

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

	private Task task4;

	private Task task5;

	private Task task6;

	private User user2;

	private Tag tag1;

	private Tag tag2;

	private Comment comment1;

	private Comment comment2;

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
		user1.setExperience(35L);
		user1.setMoney(51750L);
		user1.setLevel(50L);
		user1.setBoardUsers(null);
		user1.setTaskUserBids(null);
		user1.setTaskUsers(null);
		user1.setIsSuperAdmin(true);
		this.user1 = this.userRepository.save(user1);

		User user2 = new User();
		user2.setId(2L);
		user2.setVersion(1);
		user2.setDateCreation(new Date());
		user2.setEmail("useeer@user.fr");
		user2.setFirstName("User2");
		user2.setLastName("user2");
		user2.setPassword("user");
		user2.setExperience(35L);
		user2.setMoney(175L);
		user2.setLevel(1L);
		user2.setBoardUsers(null);
		user2.setTaskUserBids(null);
		user2.setTaskUsers(null);
		user2.setIsSuperAdmin(false);
		this.user2 = this.userRepository.save(user2);
		
		User user3 = new User();
		user3.setId(3L);
		user3.setVersion(1);
		user3.setDateCreation(new Date());
		user3.setEmail("admin@admin.fr");
		user3.setFirstName("admin");
		user3.setLastName("admin");
		user3.setPassword("admin");
		user3.setExperience(35L);
		user3.setMoney(175L);
		user3.setLevel(1L);
		user3.setBoardUsers(null);
		user3.setTaskUserBids(null);
		user3.setTaskUsers(null);
		user3.setIsSuperAdmin(true);
		this.userRepository.save(user3);
		
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

		/* CREATION NOTIFICATION */
		Notification notif1 = new Notification();
		notif1.setId(1L);
		notif1.setContent("Title de la notification");
		notif1.setTitle("Notfi 1");
		notif1.setDateCreation(new Date());
		notif1.setUser(this.user1);
		notif1.setVersion(1);
		notif1.setIsRead(false);
		notif1.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif1);

		Notification notif2 = new Notification();
		notif2.setId(2L);
		notif2.setContent("Title de la notification");
		notif2.setTitle("Notfi 3");
		notif2.setDateCreation(new Date());
		notif2.setUser(this.user1);
		notif2.setVersion(1);
		notif2.setIsRead(true);
		notif2.setType(TypeNotifEnum.information);
		this.notificationRepository.save(notif2);

		Notification notif3 = new Notification();
		notif3.setId(3L);
		notif3.setContent("Title de la notification");
		notif3.setTitle("Notfi 3");
		notif3.setDateCreation(new Date());
		notif3.setUser(this.user1);
		notif3.setVersion(1);
		notif3.setIsRead(true);
		notif3.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif3);

		Notification notif4 = new Notification();
		notif4.setId(4L);
		notif4.setContent("Title de la notification");
		notif4.setTitle("Notfi 4");
		notif4.setDateCreation(new Date());
		notif4.setUser(this.user1);
		notif4.setVersion(1);
		notif4.setIsRead(true);
		notif4.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif4);

		Notification notif5 = new Notification();
		notif5.setId(5L);
		notif5.setContent("Title de la notification");
		notif5.setTitle("Notfi 5");
		notif5.setDateCreation(new Date());
		notif5.setUser(this.user1);
		notif5.setVersion(1);
		notif5.setIsRead(true);
		notif5.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif5);

		Notification notif6 = new Notification();
		notif6.setId(6L);
		notif6.setContent("Title de la notification");
		notif6.setTitle("Notfi 6");
		notif6.setDateCreation(new Date());
		notif6.setUser(this.user1);
		notif6.setVersion(1);
		notif6.setIsRead(true);
		notif6.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif6);

		Notification notif7 = new Notification();
		notif7.setId(7L);
		notif7.setContent("Title de la notification");
		notif7.setTitle("Notfi 7");
		notif7.setDateCreation(new Date());
		notif7.setUser(this.user1);
		notif7.setVersion(1);
		notif7.setIsRead(true);
		notif7.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif7);

		Notification notif8 = new Notification();
		notif8.setId(8L);
		notif8.setContent("Title de la notification");
		notif8.setTitle("Notfi 8");
		notif8.setDateCreation(new Date());
		notif8.setUser(this.user1);
		notif8.setVersion(1);
		notif8.setIsRead(true);
		notif8.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif8);

		Notification notif9 = new Notification();
		notif9.setId(9L);
		notif9.setContent("Title de la notification");
		notif9.setTitle("Notfi 9");
		notif9.setDateCreation(new Date());
		notif9.setUser(this.user1);
		notif9.setVersion(1);
		notif9.setIsRead(true);
		notif9.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif9);

		Notification notif10 = new Notification();
		notif10.setId(10L);
		notif10.setContent("Title de la notification");
		notif10.setTitle("Notfi 10");
		notif10.setDateCreation(new Date());
		notif10.setUser(this.user1);
		notif10.setVersion(1);
		notif10.setIsRead(true);
		notif10.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif10);

		Notification notif11 = new Notification();
		notif11.setId(11L);
		notif11.setContent("Title de la notification");
		notif11.setTitle("Notfi 11");
		notif11.setDateCreation(new Date());
		notif11.setUser(this.user1);
		notif11.setVersion(1);
		notif11.setIsRead(true);
		notif11.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif11);

		Notification notif12 = new Notification();
		notif12.setId(12L);
		notif12.setContent("Title de la notification");
		notif12.setTitle("Notfi 12");
		notif12.setDateCreation(new Date());
		notif12.setUser(this.user1);
		notif12.setVersion(1);
		notif12.setIsRead(true);
		notif12.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif12);

		Notification notif13 = new Notification();
		notif13.setId(13L);
		notif13.setContent("Title de la notification");
		notif13.setTitle("Notfi 13");
		notif13.setDateCreation(new Date());
		notif13.setUser(this.user1);
		notif13.setVersion(1);
		notif13.setIsRead(true);
		notif13.setType(TypeNotifEnum.error);
		this.notificationRepository.save(notif13);

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
		board1.setMoneyDoneTask(1);
		board1.setMoneyWinBid(1);
		board1.setExpDoneTask(15);
		board1.setExpWinBid(15);
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
		col1.setColor("gray");
		col1.setStatus(StatusEnum.TODO);
		col1.setTitle("TODO");
		this.col1 = colonneKanbanRepository.save(col1);

		ColonneKanban col2 = new ColonneKanban();
		col2.setId(2L);
		col2.setVersion(1);
		col2.setBoard(this.board1);
		col2.setColor("primary");
		col2.setStatus(StatusEnum.IN_PROGRESS);
		col2.setTitle("IN_PROGRESS");
		this.col2 = colonneKanbanRepository.save(col2);

		ColonneKanban col3 = new ColonneKanban();
		col3.setId(3L);
		col3.setVersion(1);
		col3.setBoard(this.board1);
		col3.setColor("bluegraylight");
		col3.setStatus(StatusEnum.DONE);
		col3.setTitle("DONE");
		this.col3 = colonneKanbanRepository.save(col3);

		/* CREATION TAG */
		Tag tag1 = new Tag();
		tag1.setId(1L);
		tag1.setVersion(1);
		tag1.setName("Tag 1");
		tag1.setColor("bluegray");
		this.tag1 = tagRepository.save(tag1);

		Tag tag2 = new Tag();
		tag2.setId(2L);
		tag2.setVersion(2);
		tag2.setName("Tag 2");
		tag2.setColor("lime");
		this.tag2 = tagRepository.save(tag2);

		/* CREATION TASK */
		Task task1 = new Task();
		task1.setId(1L);
		task1.setBid(false);
		task1.setBoard(this.board1);
		task1.setChecklists(null);
		task1.setContent("Description de la tache");
		task1.setCreator(this.user1);
		task1.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task1.setDateEnd(new Date(sdf.parse("31/06/2016").getTime()));
		task1.setDateEndBid(null);
		task1.setDuration(5.00);
		task1.setIsBid(false);
		task1.setLinks(null);
		task1.setPeriodicity(this.period1);
		task1.setPriority(PriorityEnum.URGENT_IMPORTANT);
		ArrayList<Tag> taskTag1 = new ArrayList<>();
		taskTag1.add(tag1);
		task1.setTags(taskTag1);
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
		task2.setDateEnd(new Date(sdf.parse("31/06/2016").getTime()));
		task2.setDateEndBid(null);
		task2.setDuration(5.00);
		task2.setIsBid(false);
		task2.setLinks(null);
		task2.setPeriodicity(this.period2);
		task2.setPriority(PriorityEnum.URGENT_IMPORTANT);
		ArrayList<Tag> taskTag2 = new ArrayList<>();
		taskTag2.add(tag2);
		task2.setTags(taskTag2);
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
		task3.setDateEnd(new Date(sdf.parse("31/06/2016").getTime()));
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

		Task task4 = new Task();
		task4.setId(4L);
		task4.setBid(false);
		task4.setBoard(this.board1);
		task4.setChecklists(null);
		task4.setContent("Description de la tache 4");
		task4.setCreator(this.user1);
		task4.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task4.setDateEndBid(new Date(sdf.parse("07/07/2017").getTime()));
		task4.setDateEnd(new Date(sdf.parse("31/06/2016").getTime()));
		task4.setDuration(5.00);
		task4.setIsBid(true);
		task4.setLinks(null);
		task4.setPeriodicity(null);
		task4.setPriority(PriorityEnum.NOT_URGENT_IMPORTANT);
		task4.setTags(null);
		task4.setTaskParents(null);
		task4.setTaskUserBids(null);
		task4.setTaskUsers(null);
		task4.setTitle("Titre de la tache 4 (sans user et au enchere)");
		task4.setVersion(1);
		this.task4 = taskRepository.save(task4);

		Task task5 = new Task();
		task5.setId(5L);
		task5.setBid(false);
		task5.setBoard(this.board1);
		task5.setChecklists(null);
		task5.setContent("Description de la tache 5");
		task5.setCreator(this.user1);
		task5.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task5.setDateEndBid(new Date(sdf.parse("07/07/2017").getTime()));
		task5.setDateEnd(new Date(sdf.parse("31/06/2016").getTime()));
		task5.setDuration(5.00);
		task5.setIsBid(true);
		task5.setLinks(null);
		task5.setPeriodicity(null);
		task5.setPriority(PriorityEnum.NOT_URGENT_IMPORTANT);
		task5.setTags(null);
		task5.setTaskParents(null);
		task5.setTaskUserBids(null);
		task5.setTaskUsers(null);
		task5.setTitle("Titre de la tache 5 (bid witout user)");
		task5.setVersion(1);
		this.task5 = taskRepository.save(task5);

		Task task6 = new Task();
		task6.setId(6L);
		task6.setBid(false);
		task6.setBoard(this.board1);
		task6.setChecklists(null);
		task6.setContent("Description de la tache6");
		task6.setCreator(this.user1);
		task6.setDateCreation(new Date(sdf.parse("01/01/2016").getTime()));
		task6.setDateEnd(new Date(sdf.parse("31/06/2016").getTime()));
		task6.setDateEndBid(null);
		task6.setDuration(5.00);
		task6.setIsBid(false);
		task6.setLinks(null);
		task6.setPeriodicity(null);
		task6.setPriority(PriorityEnum.NOT_URGENT_IMPORTANT);
		task6.setTags(null);
		task6.setTaskParents(null);
		task6.setTaskUserBids(null);
		task6.setTaskUsers(null);
		task6.setTitle("Titre de la tache 6 (bid witout user)");
		task6.setVersion(1);
		this.task6 = taskRepository.save(task6);

		/* CREATION COMMENTAIRE */
		Comment comment1 = new Comment();
		comment1.setId(1L);
		comment1.setVersion(1);
		comment1.setCreator(user1);
		comment1.setContent("First !");
		comment1.setDateCreation(new Date(sdf.parse("01/01/2017").getTime()));
		comment1.setTask(task1);
		this.comment1 = commentRepository.save(comment1);

		Comment comment2 = new Comment();
		comment2.setId(2L);
		comment2.setVersion(1);
		comment2.setCreator(user1);
		comment2.setContent("Tro cool !");
		comment2.setDateCreation(new Date(sdf.parse("02/01/2017").getTime()));
		comment2.setTask(task1);
		this.comment2 = commentRepository.save(comment2);

		/* CREATION TASKUSER */
		TaskUser taskUser1 = new TaskUser();
		taskUser1.setId(1L);
		taskUser1.setDateBegin(new Date(sdf.parse("12/01/2016").getTime()));
		taskUser1.setDurationReel(null);
		taskUser1.setStatus(StatusEnum.IN_PROGRESS);
		taskUser1.setTask(this.task1);
		taskUser1.setUser(this.user1);
		taskUser1.setColonneKanban(col1);
		taskUser1.setVersion(1);
		this.taskUser1 = taskUserRepository.save(taskUser1);

		TaskUser taskUser2 = new TaskUser();
		taskUser2.setId(2L);
		taskUser2.setDateBegin(new Date(sdf.parse("12/01/2016").getTime()));
		taskUser2.setDurationReel(null);
		taskUser2.setStatus(StatusEnum.IN_PROGRESS);
		taskUser2.setTask(this.task2);
		taskUser2.setUser(this.user1);
		taskUser1.setColonneKanban(col1);
		taskUser2.setVersion(1);
		this.taskUser2 = taskUserRepository.save(taskUser2);

		TaskUser taskUser3 = new TaskUser();
		taskUser3.setId(3L);
		taskUser3.setDateBegin(new Date(sdf.parse("12/01/2016").getTime()));
		taskUser3.setDurationReel(null);
		taskUser3.setStatus(StatusEnum.IN_PROGRESS);
		taskUser3.setTask(this.task3);
		taskUser3.setUser(this.user1);
		taskUser3.setVersion(1);
		this.taskUser3 = taskUserRepository.save(taskUser3);

		/* CREATION OBJETS */
		Item item1 = new Item();
		item1.setName("Joli fond d'écran");
		item1.setKeyItem("FOND_TOULOUSE");
		item1.setDescription("Fond d'écran pour la page de guilde représentant la ville de Toulouse");
		item1.setPrice(250L);
		item1.setRequiredLevel(1L);
		item1.setReusable(Boolean.TRUE);
		item1.setType(ItemEnum.WALLPAPER);
		item1.setUrl("https://wallpaperscraft.com/image/toulouse_city_square_night_france_58716_1920x1080.jpg");
		item1.setImage("https://wallpaperscraft.com/image/toulouse_city_square_night_france_58716_1920x1080.jpg");
		itemRepository.save(item1);

		Item item2 = new Item();
		item2.setType(ItemEnum.AVATAR);
		item2.setKeyItem("AVATAR_CHEVALIER");
		item2.setName("Avatar chevalier");
		item2.setDescription("Avatar chevalier");
		item2.setImage("https://secure.gravatar.com/avatar/2ccebdcad2bc218b8ac2f9e8cdecea98?s=200&d=identicon&r=g");
		item2.setUrl("https://secure.gravatar.com/avatar/2ccebdcad2bc218b8ac2f9e8cdecea98?s=200&d=identicon&r=g");
		item2.setReusable(Boolean.TRUE);
		item2.setPrice(1000L);
		item2.setRequiredLevel(10L);
		item2 = itemRepository.save(item2);

		Item item3 = new Item();
		item3.setType(ItemEnum.AVATAR);
		item3.setName("Avatar Mechant");
		item3.setKeyItem("AVATAR_MECHANT");
		item3.setDescription("Avatar Mechant");
		item3.setImage("http://www.snut.fr/wp-content/uploads/2015/12/image-de-souris-5-150x150.png");
		item3.setUrl("http://www.snut.fr/wp-content/uploads/2015/12/image-de-souris-5-150x150.png");
		item3.setReusable(Boolean.TRUE);
		item3.setPrice(1000L);
		item3.setRequiredLevel(10L);
		item3 = itemRepository.save(item3);

		Item item4 = new Item();
		item4.setName("Malediction de la neige");
		item4.setKeyItem("CURSE_SNOW");
		item4.setDescription("Génère de la neige sur l'ecran de l'utilisateur pendant 7 jours");
		item4.setPrice(175L);
		item4.setRequiredLevel(2L);
		item4.setDuration(7);
		item4.setReusable(Boolean.TRUE);
		item4.setType(ItemEnum.CURSE);
		item4.setImage(
				"https://scontent.cdninstagram.com/t51.2885-19/s150x150/13130013_1681586922105019_1341226456_a.jpg");
		itemRepository.save(item4);

		Item item5 = new Item();
		item5.setKeyItem("SPELL_HAPPY");
		item5.setName("Sort de bonne humeur");
		item5.setDescription("Génère aléatoirement des notifications pour vous mettre de bonne humeur");
		item5.setPrice(175L);
		item5.setRequiredLevel(2L);
		item5.setReusable(Boolean.FALSE);
		item5.setType(ItemEnum.SPELL);
		item5.setImage(
				"https://previews.123rf.com/images/anastasiiam/anastasiiam1605/anastasiiam160500090/57128892-Simple-flat-like-icon-in-pink-color-Like-counter-notification-emblem-isolated-on-white-background--Stock-Vector.jpg");
		itemRepository.save(item5);
		
		Item item6 = new Item();
		item6.setKeyItem("CURSE_BREBIS");
		item6.setName("Malediction de la brebis");
		item6.setDescription("Petite musique de la brebis");
		item6.setPrice(175L);
		item6.setRequiredLevel(2L);
		item6.setDuration(3);
		item6.setReusable(Boolean.FALSE);
		item6.setType(ItemEnum.CURSE);
		item6.setImage("https://dechairetdelait.files.wordpress.com/2014/04/chevreau.jpg");
		itemRepository.save(item6);
		
		Item item7 = new Item();
		item7.setKeyItem("CURSE_JCVD");
		item7.setName("Malediction de Jean-Claude");
		item7.setDescription("Jean-Claude va vous permettre de travailler plus rapidement pendant 3jours");
		item7.setPrice(175L);
		item7.setRequiredLevel(2L);
		item7.setDuration(3);
		item7.setReusable(Boolean.FALSE);
		item7.setType(ItemEnum.CURSE);
		item7.setImage("http://www.jookos.com/wp-content/uploads/2016/06/url-4.jpg");
		itemRepository.save(item7);
		
		Item item8 = new Item();
		item8.setKeyItem("FOND_ANGERS");
		item8.setName("Fond d'écran d'Angers");
		item8.setDescription("");
		item8.setPrice(500L);
		item8.setRequiredLevel(2L);
		item8.setReusable(Boolean.FALSE);
		item8.setType(ItemEnum.WALLPAPER);
		item8.setUrl("http://www.hostpic.org/images/1608091515530095.jpeg");
		item8.setImage("http://www.hostpic.org/images/1608091515530095.jpeg");
		itemRepository.save(item8);
		
		Item item9 = new Item();
		item9.setKeyItem("FOND_SEA");
		item9.setName("FOnd d'écran de la mer");
		item9.setDescription("");
		item9.setPrice(750L);
		item9.setRequiredLevel(5L);
		item9.setReusable(Boolean.FALSE);
		item9.setType(ItemEnum.WALLPAPER);
		item9.setUrl("http://www.weesk.com/wallpaper/nature/mers-oceans-plages/barque-en-bord-de-mer/barque-en-bord-de-mer-720px.jpg");
		item9.setImage("http://www.weesk.com/wallpaper/nature/mers-oceans-plages/barque-en-bord-de-mer/barque-en-bord-de-mer-720px.jpg");
		itemRepository.save(item9);
		
		return "FINI";
	}

	@Transactional
	public void deleteAll() {
		this.itemRepository.deleteAll();
		this.notificationRepository.deleteAll();
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
