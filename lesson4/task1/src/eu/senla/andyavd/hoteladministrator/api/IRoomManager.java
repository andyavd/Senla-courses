package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public interface IVisitorManager {
	public void addVisitor(Visitor visitor);

	public void deleteVisitor(Visitor visitor);

	public Visitor[] showVisitors();

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void addServicesToVisitor(Visitor visitor, Service service);

	public Service[] showVisitorServices(Visitor visitor);

	public void sortVisitorServicesByPrice(Visitor visitor, Comparator comparator);

	void sortVisitors(Comparator comparator);
	
}
