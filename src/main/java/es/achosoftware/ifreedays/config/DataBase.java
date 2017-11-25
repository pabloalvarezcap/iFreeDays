package es.achosoftware.ifreedays.config;

import java.util.Arrays;
import java.util.HashSet;

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
import es.achosoftware.ifreedays.repository.RoleRepository;
import es.achosoftware.ifreedays.repository.SkillRepository;
import es.achosoftware.ifreedays.repository.UserRepository;

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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
	public void initialize() {
		Role role = new Role();
		role.setId(1);
		role.setRole("ADMIN");
		roleRepository.save(role);
		Skill skill = new Skill();
		skill.setId(1);
		skill.setName("RFP");
		skillRepository.save(skill);
		skill = new Skill();
		skill.setId(2);
		skill.setName("ISC");
		skillRepository.save(skill);
		User user = new User();
		user.setActive(1);
		user.setName("Nombre");
		user.setSkills(new HashSet<>(Arrays.asList(skill)));
		user.setRoles(new HashSet<>(Arrays.asList(role)));
		user.setEmail("email@hotmail.com");
		user.setLastName("apellido");
		user.setPassword(bCryptPasswordEncoder.encode("12345"));
		userRepository.save(user);
	}
	
    @Bean
    ServletRegistrationBean h2servletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }


}
