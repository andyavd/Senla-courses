package eu.senla.andyavd.hoteladministrator.model.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.model.entities.Visitor;

import java.util.ArrayList;

public class VisitorsStorage {

	private List<Entity> visitors = new ArrayList<Entity>();
	private int counter = 0;
	
	public List<Entity> getVisitors() {
		return visitors;
	}
	
	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
		visitors.get(counter).setId(counter + 1);
		counter++;
		}

	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitor.setHistory(history);
	}

	public void deleteVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.size(); i++) {
			if (visitors.get(i) == visitor) {
				visitors.remove(i);
			}
		}
	}
}