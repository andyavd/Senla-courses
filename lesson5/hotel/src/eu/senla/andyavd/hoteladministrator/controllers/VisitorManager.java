package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.managers.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.api.storages.IVisitorsStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.VisitorsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class VisitorManager implements IVisitorManager {

	private IVisitorsStorage visitorsStorage = new VisitorsStorage();
	private final static String path = Path.VISITOR_STORAGE_PATH.getPath();

	@Override
	public void addVisitor(Visitor visitor) {
		visitorsStorage.addVisitor(visitor);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		visitorsStorage.deleteVisitor(visitor);
	}

	@Override
	public List<AEntity> getVisitors() {
		return visitorsStorage.getVisitors();
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitorsStorage.updateVisitor(visitor, history);
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
	public List<AEntity> showVisitorServices(Visitor visitor) {

		return visitor.getHistory().getService();
	}

	@Override
	public List<AEntity> sortVisitors(Comparator<AEntity> comparator) {
		List<AEntity> sortedVisitors = visitorsStorage.getVisitors();
		Collections.sort(sortedVisitors, comparator);
		return sortedVisitors;
	}

	@Override
	public List<AEntity> sortVisitorServicesByPrice(Visitor visitor, Comparator<AEntity> comparator) {
		List<AEntity> sortedVisitors = showVisitorServices(visitor);
		Collections.sort(sortedVisitors, comparator);
		return sortedVisitors;
	}
	
	@Override
	public Visitor getVisitorById(Integer id) {
		return visitorsStorage.getVisitorById(id);
	}

	@Override
	public void saveToFile() {
		FileWriter.writeToFile(ArrayWorker.arrayToString(visitorsStorage.getVisitors()), path);
	}

	@Override
	public String[] loadFromFile() {
		return FileReader.readFromFile(VisitorManager.path);
	}
}
