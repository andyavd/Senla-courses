package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;

public interface IVisitorDao extends IAEntityDao<Visitor>{
	List<Service> getVisitorServices(Connection connection, Visitor visitor);
	Double billVisitor(Connection connection, Visitor visitor);
	List<Visitor> sortVisitorsByName(Connection connection);
	List<Service> sortVisitorServicesByPrice(Connection connection, Visitor visitor);
}