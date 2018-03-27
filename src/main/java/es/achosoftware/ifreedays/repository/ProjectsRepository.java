package es.achosoftware.ifreedays.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.achosoftware.ifreedays.model.Project;

@Repository("projectsRepository")
public interface ProjectsRepository extends JpaRepository<Project, Integer>{
	
	List<Project> findProjectsByUserId(int userId);
	
	

}
