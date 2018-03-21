package eu.senla.andyavd.hotel.api.services;

import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IVisitorService {
	
	void addVisitor(Visitor visitor) throws Exception;

	List<Visitor> getVisitors(SortType type) throws Exception;

	void updateVisitor(Visitor visitor) throws Exception;

	void deleteVisitor(Visitor visitor) throws Exception;

	Visitor getVisitorById(int id) throws Exception;

	List<Visitor> getCheckedVisitors() throws Exception;

	List<Visitor> getCheckedVisitorsWithServices() throws Exception;

	void importFromCsv() throws Exception;

	void exportToCsv() throws Exception;
}