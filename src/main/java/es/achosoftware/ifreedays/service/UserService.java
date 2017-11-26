package es.achosoftware.ifreedays.service;

import java.util.List;

import es.achosoftware.ifreedays.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public List<User> listUsersWithSkill(Integer id);
	public List<User> listUsers();
	public void delete(Integer id);
}
