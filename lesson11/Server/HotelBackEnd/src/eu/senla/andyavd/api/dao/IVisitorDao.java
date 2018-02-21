package eu.senla.andyavd.api.dao;

import java.sql.Connection;
import java.util.List;

import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.enums.SortType;

public interface IVisitorDao extends IGenericDao<Visitor>{
	List<Visitor> getCheckedVisitors(Connection connection, SortType type) throws Exception;
	List<Visitor> getCheckedVisitorsWithServices(Connection connection) throws Exception;
}