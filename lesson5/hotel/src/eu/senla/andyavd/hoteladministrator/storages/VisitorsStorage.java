package eu.senla.andyavd.hoteladministrator.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IVisitorsStorage;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class VisitorsStorage implements IVisitorsStorage {

	private List<Visitor> visitors = new ArrayList<Visitor>();

	private static VisitorsStorage visitorsStorage;

	private VisitorsStorage() {

	}

	public static VisitorsStorage getInstance() {
		if (visitorsStorage == null) {
			visitorsStorage = new VisitorsStorage();
		}
		return visitorsStorage;
	}

	@Override
	public List<Visitor> getVisitors() {
		return visitors;
	}

	@Override
	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

	@Override
	public void addVisitor(Visitor visitor) {
		visitors.get(visitors.size() - 1).setId(visitors.size());
		visitors.add(visitor);
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitor.setHistory(history);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.size(); i++) {
			if (visitors.get(i) == visitor) {
				visitors.remove(i);
			}
		}
	}

	@Override
	public Visitor getVisitorById(Integer id) {

		Visitor visitor = null;

		for (int i = 0; i < visitors.size(); i++) {
			if (visitors.get(i).getId() == id) {
				visitor = visitors.get(i);
			}
		}
		return visitor;
	}
}
