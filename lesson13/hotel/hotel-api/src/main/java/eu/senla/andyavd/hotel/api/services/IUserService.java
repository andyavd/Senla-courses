package eu.senla.andyavd.hotel.api.services;

import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IUserService {

	void registerUser(User user) throws Exception;

	List<User> getUsers(SortType type) throws Exception;

	void updateUser(User user) throws Exception;

	void deleteUser(User user) throws Exception;

	User getUserById(int id) throws Exception;
	
	User getUserByUsername(String username) throws Exception;
}
