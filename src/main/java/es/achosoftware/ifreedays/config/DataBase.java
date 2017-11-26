package es.achosoftware.ifreedays.config;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.achosoftware.ifreedays.model.Role;
import es.achosoftware.ifreedays.model.Skill;
import es.achosoftware.ifreedays.model.User;
import es.achosoftware.ifreedays.model.Vacation;
import es.achosoftware.ifreedays.repository.RoleRepository;
import es.achosoftware.ifreedays.repository.SkillRepository;
import es.achosoftware.ifreedays.repository.UserRepository;
import es.achosoftware.ifreedays.repository.VacationRepository;

@Configuration
public class DataBase {

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL).addScript("db/init.sql").addScript("db/insert-data.sql").build();
		return db;
	}
	
	//FIXME; El cabronazo de arriba debería insertar el puto registro [1, ADMIN], pero no le da la puta gana
	//Es como que insert-data.sql está en una transacción a parte, si tratas de cambiarlo incrementa las horas
	//horas malgastadas = 3
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private SkillRepository skillRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
	public void initialize() {
		Role role = new Role();
		role.setId(1);
		role.setRole("ADMIN");
		roleRepository.save(role);
		role = new Role();
		role.setId(2);
		role.setRole("USER");
		roleRepository.save(role);
		Set<Skill> skills = new HashSet<>();
		Skill skill = new Skill();
		skill.setId(1);
		skill.setName("RFP");
		skillRepository.save(skill);
		skills.add(skill);
		skill = new Skill();
		skill.setId(2);
		skill.setName("ISC");
		skills.add(skill);
		skillRepository.save(skill);
		User user = new User();
		user.setActive(1);
		user.setName("Nombre");
		user.setSkills(skills);
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		user.setEmail("email@hotmail.com");
		user.setLastName("apellido");
		user.setPassword(bCryptPasswordEncoder.encode("12345"));
		userRepository.save(user);
		user = new User();
		user.setActive(1);
		user.setName("Nombre2");
		user.setSkills(new HashSet<>(Arrays.asList(skill)));
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		user.setEmail("email2@hotmail.com");
		user.setLastName("apellido2");
		user.setPassword(bCryptPasswordEncoder.encode("12345"));
		userRepository.save(user);
		Vacation vacation = new Vacation();
		vacation.setUserid(user.getId());
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 11, 11);
		vacation.setDay(cal.getTime());
		vacationRepository.save(vacation);
	}
	
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }


}
