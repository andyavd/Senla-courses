package eu.senla.andyavd.api.controllers;

import java.time.LocalDate;
import java.util.List;

import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;

public interface IVisitorManager {
	public void addVisitor(Visitor visitor);

	public void deleteVisitor(Visitor visitor);

	public List<Visitor> getVisitors();
	
	public Integer getTotalVisitorsOnDate(LocalDate date);

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void addServicesToVisitor(Visitor visitor, Service service);

	public List<Service> getVisitorServices(Visitor visitor);

	public List<Service> sortVisitorServicesByPrice(Visitor visitor);

	public List<Visitor> sortVisitors();

	public Visitor getVisitorById(Integer id);

	void importFromCsv();

	void exportToCsv();

}
