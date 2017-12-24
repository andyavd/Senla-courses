package lesson7.hotel.src.eu.senla.andyavd.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import lesson7.dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import lesson7.hotel.src.eu.senla.andyavd.api.controllers.IVisitorManager;
import lesson7.hotel.src.eu.senla.andyavd.api.storages.IVisitorsStorage;
import lesson7.hotel.src.eu.senla.andyavd.entities.RoomHistory;
import lesson7.hotel.src.eu.senla.andyavd.entities.Service;
import lesson7.hotel.src.eu.senla.andyavd.entities.Visitor;

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
}
