package eu.senla.andyavd.hotel.api.dao;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.entity.beans.Service;

public interface IServiceDao extends IGenericDao<Service> {
	
	void changeServicePrice(Session session, Double dailyPrice, int id) throws Exception;
}