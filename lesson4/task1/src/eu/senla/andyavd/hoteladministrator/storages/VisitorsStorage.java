package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class VisitorsStorage {

	private Visitor[] visitors = new Visitor[5];
	private int counter = 0;

	public void addVisitor(Visitor visitor) {
		// if (visitor[visitor.length-1] != null) {
		// visitor = (Visitor[]) ArrayWorker.expand(visitor);
		// }
		visitors[counter] = visitor;
		visitors[counter].setId(counter + 1);
		counter++;
	}

	public Visitor[] getVisitors() {
		return visitors;
	}

	public void updateVisitor(Visitor visitor, RoomHistory history) {
		for (int i = 0; i < visitor.getHistories().length; i++) {
			if (visitor.getHistories()[i] == null) {
				visitor.getHistories()[i] = history;
				break;
			}
		}
	}

	public void deleteVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.length; i++) {
			if (visitors[i] == visitor) {
				visitors[i] = null;
			}
		}
	}
}
