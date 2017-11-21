package eu.senla.andyavd.hoteladministrator.api.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public interface IVisitorManager {
	public void addVisitor(Visitor visitor);

	public void deleteVisitor(Visitor visitor);

	public List<Visitor> getVisitors();
	
	public Integer getTotalVisitorsOnDate(LocalDate date);

	public void setVisitors(List<Visitor> visitors);

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void addServicesToVisitor(Visitor visitor, Service service);

	public List<Service> getVisitorServices(Visitor visitor);

	public List<Service> sortVisitorServicesByPrice(Visitor visitor, Comparator<Service> comparator);

	public List<Visitor> sortVisitors(Comparator<Visitor> comparator);

	public Visitor getVisitorById(Integer id);

}
