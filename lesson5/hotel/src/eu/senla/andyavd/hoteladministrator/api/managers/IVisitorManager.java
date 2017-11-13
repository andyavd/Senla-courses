package eu.senla.andyavd.hoteladministrator.api.managers;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public interface IVisitorManager {
	public void addVisitor(Visitor visitor);

	public void deleteVisitor(Visitor visitor);

	public List<AEntity> getVisitors();

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void addServicesToVisitor(Visitor visitor, Service service);

	public List<AEntity> showVisitorServices(Visitor visitor);

	public List<AEntity> sortVisitorServicesByPrice(Visitor visitor, Comparator<AEntity> comparator);

	public List<AEntity> sortVisitors(Comparator<AEntity> comparator);
	
	public Visitor getVisitorById(Integer id);

	public void saveToFile();

	public String[] loadFromFile();
	
}