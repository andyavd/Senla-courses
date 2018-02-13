package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.Service;

public interface IServiceDao extends IAEntityDao<Service>{
	List<Service> sortServicesByName(Connection connection);
	List<Service> sortServicesByPrice(Connection connection);
}