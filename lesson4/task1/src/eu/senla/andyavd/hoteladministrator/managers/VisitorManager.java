package eu.senla.andyavd.hoteladministrator.managers;

import java.time.LocalDate;
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

	VisitorsStorage visitorsStorage = new VisitorsStorage();
	RoomHistoryStorage roomHistoryStorage = new RoomHistoryStorage();
	
	private final static String path = Path.VISITOR_STORAGE_PATH.getPath();
	
	private Visitor[] castEntitiesArray(Entity[] entities) {
		Visitor[] visitorArray = Arrays.copyOf(entities, entities.length, Visitor[].class);
		return visitorArray;
	}
	
	@Override
	public void addVisitor(Visitor visitor) {
		visitorsStorage.addVisitor(visitor);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		visitorsStorage.deleteVisitor(visitor);
	}

	@Override
	public Visitor[] getVisitors() {
		return visitorsStorage.getVisitors();
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitorsStorage.updateVisitor(visitor, history);
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
	public Service[] getVisitorServices(Visitor visitor) {

		return visitor.getHistory().getService();
	}
	
	public String getTotalVisitorsOnDate(LocalDate date) {

		Integer count = 0;
		for (int i = 0; i < visitorsStorage.getVisitors().length; i++) {
			if (visitorsStorage.getVisitors()[i] != null && visitorsStorage.getVisitors()[i].getHistory() != null) {
				if ((visitorsStorage.getVisitors()[i].getHistory().getCheckInDate().isBefore(date)
						|| visitorsStorage.getVisitors()[i].getHistory().getCheckInDate().isEqual(date))
						&& (visitorsStorage.getVisitors()[i].getHistory().getCheckOutDate().isAfter(date)
								|| visitorsStorage.getVisitors()[i].getHistory().getCheckOutDate().isEqual(date))) {
					count++;
				}
			}
		}
		StringBuilder s = new StringBuilder();
		s.append("There are ");
		s.append(count);
		s.append(" visitors on ");
		s.append(date);

		return s.toString();
	}

	@Override
	public Visitor[] sortVisitors(Comparator comparator) {
		Visitor[] sortedVisitors = visitorsStorage.getVisitors();
		Arrays.sort(sortedVisitors, comparator);
		return sortedVisitors;
	}

	@Override
	public Service[] sortVisitorServicesByPrice(Visitor visitor, Comparator<Service> comparator) {
		Service[] sortedVisitorServices = getVisitorServices(visitor);
		Arrays.sort(sortedVisitorServices, comparator);
		return sortedVisitorServices;
	}

	public void saveToFile() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(visitorsStorage.getVisitors()), ArrayWorker.getArraySize(visitorsStorage.getVisitors()));
		FileWriter.writeToFile(stringArray, path); 
	}
	public String[] loadFromFile() {
		return FileReader.readFromFile(this.path);
	}
}
