package eu.senla.andyavd.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.controllers.IVisitorManager;
import eu.senla.andyavd.api.storages.IVisitorsStorage;

public class VisitorManager implements IVisitorManager {
	
	final static Logger logger = Logger.getLogger(VisitorManager.class);

	private IVisitorsStorage visitorsStorage = (IVisitorsStorage) DependencyInjection.getInstance().getInstance(IVisitorsStorage.class);
	public VisitorManager() {
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
	public List<Visitor> getVisitors() {
		return visitorsStorage.getVisitors();
	}

	@Override
	public Integer getTotalVisitorsOnDate(LocalDate date) {
		Integer count = 0;
		List<Visitor> visitors = getVisitors();
		for (int i = 0; i < visitors.size(); i++) {
			Visitor visitor = visitors.get(i);
			if (visitor != null && visitor.getHistory() != null) {
				if (date.isAfter(visitor.getHistory().getCheckInDate()) && date.isBefore(visitor.getHistory().getCheckOutDate())) {
					count++;
				}
			}
		}
		return count;
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
			logger.error("No such visitor to bill!");
		}
	}

	@Override
	public List<Service> getVisitorServices(Visitor visitor) {
		return visitor.getHistory().getService();
	}

	@Override
	public List<Visitor> sortVisitors(Comparator<Visitor> comparator) {
		List<Visitor> sortedVisitors = visitorsStorage.getVisitors();
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
		return visitorsStorage.getVisitorById(id);
	}

	@Override
	public void setVisitors(List<Visitor> visitors) {
		visitorsStorage.setVisitors(visitors);
		
	}
	
	@Override
	public void importFromCsv() {
		visitorsStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
		visitorsStorage.exportToCsv();
	}
}
