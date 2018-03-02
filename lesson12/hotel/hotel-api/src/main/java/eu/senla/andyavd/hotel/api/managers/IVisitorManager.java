package eu.senla.andyavd.hotel.api.managers;

import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.Visitor;

public interface IVisitorManager {
	
	void addVisitor(Visitor visitor) throws Exception;

	List<Visitor> getVisitors() throws Exception;

	void updateVisitor(Visitor visitor) throws Exception;

	void deleteVisitor(Visitor visitor) throws Exception;

	Visitor getVisitorById(int id) throws Exception;

	List<Visitor> getCheckedVisitors() throws Exception;

	List<Visitor> getCheckedVisitorsWithServices() throws Exception;

	void importFromCsv() throws Exception;

	void exportToCsv() throws Exception;
}