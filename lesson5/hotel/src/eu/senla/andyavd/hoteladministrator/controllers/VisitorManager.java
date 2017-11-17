package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.controllers.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.VisitorsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class VisitorManager implements IVisitorManager {
	
	FileReader fileReader = new FileReader();
	FileWriter fileWriter = new FileWriter();
	
	private final static String path = Path.VISITOR_STORAGE_PATH.getPath();

	@Override
	public void addVisitor(Visitor visitor) {
		VisitorsStorage.getInstance().addVisitor(visitor);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		VisitorsStorage.getInstance().deleteVisitor(visitor);
	}

	@Override
	public List<Visitor> getVisitors() {
		return VisitorsStorage.getInstance().getVisitors();
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		VisitorsStorage.getInstance().updateVisitor(visitor, history);
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
	public List<Service> getVisitorServices(Visitor visitor) {
		return visitor.getHistory().getService();
	}

	@Override
	public List<Visitor> sortVisitors(Comparator<Visitor> comparator) {
		List<Visitor> sortedVisitors = VisitorsStorage.getInstance().getVisitors();
		Collections.sort(sortedVisitors, comparator);
		return sortedVisitors;
	}

	@Override
	public List<Service> sortVisitorServicesByPrice(Visitor visitor, Comparator<Service> comparator) {
		List<Service> sortedVisitors = getVisitorServices(visitor);
		Collections.sort(sortedVisitors, comparator);
		return sortedVisitors;
	}
	
	@Override
	public Visitor getVisitorById(Integer id) {
		return VisitorsStorage.getInstance().getVisitorById(id);
	}

	@Override
	public void saveToFile() {
		fileWriter.writeToFile(ArrayWorker.arrayToString(VisitorsStorage.getInstance().getVisitors()), path);
	}

	@Override
	public String[] loadFromFile() {
		return fileReader.readFromFile(VisitorManager.path);
	}

	@Override
	public void setVisitors(List<Visitor> visitors) {
		VisitorsStorage.getInstance().setVisitors(visitors);
	}
}
