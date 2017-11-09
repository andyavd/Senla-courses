package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;
import eu.senla.andyavd.hoteladministrator.model.entities.Visitor;

public interface IVisitorManager {
	public void addVisitor(Visitor visitor);

	public void deleteVisitor(Visitor visitor);

	public List<Entity> getVisitors();

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void addServicesToVisitor(Visitor visitor, Service service);

	public List<Entity> showVisitorServices(Visitor visitor);

	@SuppressWarnings("rawtypes")
	public void sortVisitorServicesByPrice(Visitor visitor, Comparator comparator);

	@SuppressWarnings("rawtypes")
	void sortVisitors(Comparator comparator);
	
}