package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public interface IVisitorManager {
	public void addVisitor(Visitor visitor);

	public void deleteVisitor(Visitor visitor);

	public Visitor[] getVisitors();

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void addServicesToVisitor(Visitor visitor, Service service);

	public Service[] getVisitorServices(Visitor visitor);

	public Service[] sortVisitorServicesByPrice(Visitor visitor, Comparator<Service> comparator);

	public Visitor[] sortVisitors(Comparator<Visitor> comparator);
	
}
