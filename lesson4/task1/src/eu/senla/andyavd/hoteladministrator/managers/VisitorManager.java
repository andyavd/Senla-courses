package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;

import eu.senla.andyavd.hoteladministrator.actions.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.storages.VisitorsStorage;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.sorters.servisesorters.SortingServicesByName;
import eu.senla.andyavd.hoteladministrator.utils.sorters.servisesorters.SortingServicesByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.visitorsorters.SortingVisitorsByCheckOutDate;
import eu.senla.andyavd.hoteladministrator.utils.sorters.visitorsorters.SortingVisitorsByName;

public class VisitorManager implements IVisitorManager {

	VisitorsStorage vs = new VisitorsStorage();

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
		int newSize = 0;
		for (int i = 0; i < vs.getVisitors().length; i++) {
			if (vs.getVisitors()[i] != null) {
				newSize++;
			}
		}
		Visitor[] notEmptyVisitors = new Visitor[newSize];
		int newIndex = 0;
		for (int i = 0; i < vs.getVisitors().length; i++) {
			if (vs.getVisitors()[i] != null) {
				notEmptyVisitors[newIndex] = vs.getVisitors()[i];
				newIndex++;
			}
		}
		return notEmptyVisitors;
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		vs.updateVisitor(visitor, history);
	}

	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) {

		for (int i = 0; i < visitor.getHistories().length; i++) {
			if (visitor.getHistories()[i].getId() == visitor.getLastHistoryWithCheckInStatusId()) {
				visitor.setLastHistoryWithCheckInStatusIdIndex(i);
				break;
			}
		}

		for (int i = 0; i < visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()]
				.getService().length; i++) {
			if (visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()].getService()[i] == null) {
				visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()].getService()[i] = service;
				break;
			}
		}
	}

	@Override
	public Service[] showVisitorServices(Visitor visitor) {

		int newSize = 0;
		for (int i = 0; i < visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()]
				.getService().length; i++) {
			if (visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()].getService()[i] != null) {
				newSize++;
			}
		}

		Service[] notEmptyServices = new Service[newSize];
		int newIndex = 0;
		for (int i = 0; i < visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()]
				.getService().length; i++) {
			if (visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()].getService()[i] != null) {
				notEmptyServices[newIndex] = visitor.getHistories()[visitor.getLastHistoryWithCheckInStatusIdIndex()]
						.getService()[i];
				newIndex++;
			}
		}
		return notEmptyServices;
	}

	@Override
	public void sortVisitorServicesByName(Visitor visitor) {
		Arrays.sort(showVisitorServices(visitor), new SortingServicesByName());
		for (int i = 0; i < showVisitorServices(visitor).length; i++) {
			Printer.print(showVisitorServices(visitor)[i].toString());
		}
	}

	@Override
	public void sortVisitorServicesByPrice(Visitor visitor) {
		Arrays.sort(showVisitorServices(visitor), new SortingServicesByPrice());
		for (int i = 0; i < showVisitorServices(visitor).length; i++) {
			Printer.print(showVisitorServices(visitor)[i].toString());
		}
	}

	/* ======================sorting============================ */

	@Override
	public void sortVisitorsByName() {
		Arrays.sort(vs.getVisitors(), new SortingVisitorsByName());
		for (int i = 0; i < vs.getVisitors().length; i++) {
			if (vs.getVisitors()[i].getCheckOutDate() != null) {
				Printer.print(vs.getVisitors()[i].toString());
			}
		}
	}

	@Override
	public void sortVisitorsByCheckOutDate() {

		int newSize = 0;
		for (int i = 0; i < vs.getVisitors().length; i++) {
			if (vs.getVisitors()[i].getCheckOutDate() != null) {
				newSize++;
			}
		}
		Visitor[] notEmptyVisitors = new Visitor[newSize];
		int newIndex = 0;
		for (int i = 0; i < vs.getVisitors().length; i++) {
			if (vs.getVisitors()[i].getCheckOutDate() != null) {
				notEmptyVisitors[newIndex] = vs.getVisitors()[i];
				newIndex++;
			}
		}

		Arrays.sort(notEmptyVisitors, new SortingVisitorsByCheckOutDate());
		for (int i = 0; i < notEmptyVisitors.length; i++) {
			// if(notEmptyVisitors[i].getCheckOutDate() != null) {
			Printer.print(notEmptyVisitors[i].toString());
			// }
		}
	}
}
