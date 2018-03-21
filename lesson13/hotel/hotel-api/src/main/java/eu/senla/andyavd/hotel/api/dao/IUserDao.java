package eu.senla.andyavd.hotel.api.dao;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.entity.beans.User;

public interface IUserDao extends IGenericDao<User> {

	User getUserByUsername(Session session, String username) throws Exception;
	
}