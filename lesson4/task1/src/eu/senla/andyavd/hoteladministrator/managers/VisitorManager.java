package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.api.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoryStorage;
import eu.senla.andyavd.hoteladministrator.storages.VisitorsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;
import eu.senla.andyavd.hoteladministrator.utils.Printer;

public class VisitorManager implements IVisitorManager {

	VisitorsStorage vs = new VisitorsStorage();
	RoomHistoryStorage his = new RoomHistoryStorage();
	
	private final static String path = Path.VISITOR_STORAGE_PATH.getPath();
	
	private Visitor[] castEntitiesArray(Entity[] entities) {
		Visitor[] visitorArray = Arrays.copyOf(entities, entities.length, Visitor[].class);
		return visitorArray;
	}
	
	@Override
	public void addVisitor(Visitor visitor) {
		vs.addVisitor(visitor);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		vs.deleteVisitor(visitor);
	}

	@Override
	public Visitor[] showVisitors() {
		return vs.getVisitors();
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		vs.updateVisitor(visitor, history);
	}

	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) {
		if (visitor.getHistory() != null) {
			for (int i = 0; i < visitor.getHistory().getService().length; i++) {
				if (visitor.getHistory().getService()[i] == null) {
					visitor.getHistory().getService()[i] = service;
					break;
				}
			}

		} else {
			Printer.print("No such checked-in user");
		}
	}

	@Override
	public Service[] showVisitorServices(Visitor visitor) {

		return visitor.getHistory().getService();
	}

	/* ======================sorting============================ */

	@Override
	public void sortVisitors(Comparator comparator) {
		Arrays.sort(vs.getVisitors(), comparator);
	}

	@Override
	public void sortVisitorServicesByPrice(Visitor visitor, Comparator comparator) {
		Arrays.sort(showVisitorServices(visitor), comparator);
	}

	public void saveToFile() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(vs.getVisitors()), ArrayWorker.getArraySize(vs.getVisitors()));
		FileWriter.writeToFile(stringArray, path); 
	}
	public String[] loadFromFile() {
		return FileReader.readFromFile(this.path);
	}
}
