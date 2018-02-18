package eu.senla.andyavd.api.controllers;

import java.util.List;

import eu.senla.andyavd.Visitor;

public interface IVisitorManager {
	void addVisitor(Visitor visitor);
	List<Visitor> getVisitors();
	void updateVisitor(Visitor visitor);
	void deleteVisitor(int id);
	Visitor getVisitorById(int id);
	List<Visitor> getCheckedVisitors();
	List<Visitor> getCheckedVisitorsWithServices();
	void importFromCsv();
	void exportToCsv();
}