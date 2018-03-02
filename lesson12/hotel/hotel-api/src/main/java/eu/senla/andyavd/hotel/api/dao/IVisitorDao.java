package eu.senla.andyavd.hotel.api.dao;

import java.util.List;

import org.hibernate.Session;

import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IVisitorDao extends IGenericDao<Visitor> {
	
	List<Visitor> getCheckedVisitors(Session session, SortType type) throws Exception;

	List<Visitor> getCheckedVisitorsWithServices(Session session, SortType type) throws Exception;
}