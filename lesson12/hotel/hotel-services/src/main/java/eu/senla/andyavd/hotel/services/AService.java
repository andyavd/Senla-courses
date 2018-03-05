package eu.senla.andyavd.hotel.services;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;

public abstract class AService {
	protected Session session = HibernateUtil.getSession();
}
