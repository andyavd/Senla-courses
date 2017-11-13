package eu.senla.andyavd.hoteladministrator.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IVisitorsStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

import java.util.ArrayList;

public class VisitorsStorage implements IVisitorsStorage{

	private List<AEntity> visitors = new ArrayList<AEntity>();
	private int counter = 0;

	@Override
	public List<AEntity> getVisitors() {
		return visitors;
	}
	
	@Override
	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
		visitors.get(counter).setId(counter + 1);
		counter++;
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
			if (((Visitor) visitors.get(i)).getId() == id) {
				visitor = (Visitor) visitors.get(i);
			}
		}
		return visitor;
	}
}