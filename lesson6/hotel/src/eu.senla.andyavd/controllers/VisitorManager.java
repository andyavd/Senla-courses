package eu.senla.andyavd.hoteladministrator.controllers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.api.controllers.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.storages.VisitorsStorage;

public class VisitorManager implements IVisitorManager {
	
	final static Logger logger = Logger.getLogger(VisitorManager.class);

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
		VisitorsStorage.getInstance().updateVisitor(visitor, history);
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
	public void setVisitors(List<Visitor> visitors) {
		VisitorsStorage.getInstance().setVisitors(visitors);
		
	}
}
