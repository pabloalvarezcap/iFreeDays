//package es.achosoftware.ifreedays.config;
//
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import es.achosoftware.ifreedays.model.Role;
//import es.achosoftware.ifreedays.model.Skill;
//import es.achosoftware.ifreedays.model.User;
//import es.achosoftware.ifreedays.repository.RoleRepository;
//import es.achosoftware.ifreedays.repository.SkillRepository;
//import es.achosoftware.ifreedays.repository.UserRepository;
//import es.achosoftware.ifreedays.repository.VacationRepository;
//
//@Configuration
//public class DataBase {
////
//////	@Bean
//////	public DataSource dataSource() {
//////		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//////		builder.setType(EmbeddedDatabaseType.HSQL);
//////		builder.addScript("db/init.sql");
//////		builder.addScript("db/insert-data.sql");
//////		EmbeddedDatabase db = builder.build();
//////		return db;
//////	}
//////	
////	//FIXME; El cabronazo de arriba debería insertar el puto registro [1, ADMIN], pero no le da la puta gana
////	//Es como que insert-data.sql está en una transacción a parte, si tratas de cambiarlo incrementa las horas
////	//horas malgastadas = 3
//	@Autowired
//    private RoleRepository roleRepository;
//	@Autowired
//    private SkillRepository skillRepository;
//	@Autowired
//    private UserRepository userRepository;
//	@Autowired
//    private VacationRepository vacationRepository;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
////
//    @PostConstruct
//	public void initialize() {
//    	Set<Role> roles = new HashSet<>();
//		Role role = new Role();
//		role.setId(1);
//		role.setName("ADMIN");
//		roleRepository.save(role);
//		roles.add(role);
//		role = new Role();
//		role.setId(2);
//		role.setName("USER");
//		roleRepository.save(role);
//		roles.add(role);
//		List<Skill> skills = skillRepository.findAll();
//		Skill skill = new Skill();
//		skill.setId(1);
//		skill.setName("RFP");
//		skillRepository.save(skill);
//		skills.add(skill);
//		skill = new Skill();
//		skill.setId(2);
//		skill.setName("ISC");
//		skillRepository.save(skill);
//		skills.add(skill);
//		skill = new Skill();
//		skill.setId(3);
//		skill.setName("Spring");
//		skillRepository.save(skill);
//		skills.add(skill);
//		skill = new Skill();
//		skill.setId(4);
//		skill.setName("SQL");
//		skillRepository.save(skill);
//		skills.add(skill);
//		
//		Set<Skill> skills = new HashSet<>();
//		role = new Role();
//		role.setId(1);
//		role.setName("ADMIN");
//		User user = new User();
//		user.setActive(1);
//		user.setName("Nombre");
//		user.setSkills(skills);
//		user.setRoles(new HashSet<>(Arrays.asList(role)));
//		user.setEmail("email@hotmail.com");
//		user.setLastName("apellido");
//		user.setPassword(bCryptPasswordEncoder.encode("12345"));
//		userRepository.save(user);
//		
//		
//		
//		user = new User();
//		user.setActive(1);
//		user.setName("Pepe");
//		skill = new Skill();
//		skill.setId(3);
//		skill.setName("Spring");
//		user.setSkills(new HashSet<>(Arrays.asList(skill)));
//		user.setRoles(new HashSet<>(Arrays.asList(role)));
//		user.setEmail("email2@hotmail.com");
//		user.setLastName("Martinez");
//		user.setPassword(bCryptPasswordEncoder.encode("12345"));
//		userRepository.save(user);
////		
////		
////		
//		for (int x=20; x<40; ++x) {
//			Role role = new Role();
//			role.setId(2);
//			role.setName("USER");
//			user = new User();
//			user.setActive(1);
//			user.setName("Nombre" + x);
////			skill = new Skill();
////			skill.setId(2);
////			skill.setName("ISC");
//			user.setSkills(skills.stream().collect(Collectors.toSet()));
//			user.setRoles(new HashSet<>(Arrays.asList(role)));
//			user.setEmail("email" + x + "@hotmail.com");
//			user.setLastName("apellido" + x);
//			user.setPassword(bCryptPasswordEncoder.encode("12345"));
//			userRepository.save(user);
//		}
//////		Vacation vacation = new Vacation();
//////		vacation.setUserid(user.getId());
//////		Calendar cal = Calendar.getInstance();
//////		cal.set(2017, 11, 11);
//////		vacation.setDay(cal.getTime());
//////		vacationRepository.save(vacation);
////		List<User> users = userRepository.findAll();
////		for (User u : users) {
////			System.out.println(u);
////		}
//	}
//    
//}
////	
//////    @Bean
//////    ServletRegistrationBean h2servletRegistration(){
//////        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//////        registrationBean.addUrlMappings("/console/*");
//////        return registrationBean;
//////    }
////
////
////}
