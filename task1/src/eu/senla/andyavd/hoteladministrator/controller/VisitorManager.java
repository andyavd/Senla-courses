package eu.senla.andyavd.hoteladministrator.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;
import eu.senla.andyavd.hoteladministrator.model.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.model.storages.RoomHistoryStorage;
import eu.senla.andyavd.hoteladministrator.model.storages.VisitorsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class VisitorManager implements IVisitorManager {

	VisitorsStorage vs = new VisitorsStorage();
	RoomHistoryStorage his = new RoomHistoryStorage();
	
	private final static String path = Path.VISITOR_STORAGE_PATH.getPath();
	
	@Override
	public void addVisitor(Visitor visitor) {
		vs.addVisitor(visitor);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		vs.deleteVisitor(visitor);
	}

	@Override
	public List<Entity> getVisitors() {
		return vs.getVisitors();
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		vs.updateVisitor(visitor, history);
	}

	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) {
		if (visitor.getHistory() != null) {

			visitor.getHistory().getService().add(service);

		} else {
			throw new NullPointerException("No such checked-in user!");
		}
	}

	@Override
	public List<Entity> showVisitorServices(Visitor visitor) {

		return visitor.getHistory().getService();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void sortVisitors(Comparator comparator) {
		Collections.sort(vs.getVisitors(), comparator);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void sortVisitorServicesByPrice(Visitor visitor, Comparator comparator) {
		Collections.sort(showVisitorServices(visitor), comparator);
	}

	public void saveToFile() {
		FileWriter.writeToFile(ArrayWorker.arrayToString(vs.getVisitors()), path); 
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(VisitorManager.path);
	}
}
