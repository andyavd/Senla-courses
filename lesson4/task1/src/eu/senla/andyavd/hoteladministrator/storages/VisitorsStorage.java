package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class VisitorsStorage {

	private Visitor[] visitors = new Visitor[5];

	public void addVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.length; i++) {

			// if(i == visitors.length - 1) {
			// Visitor[] stretchedArray = new Visitor[visitors.length * 2];
			// System.arraycopy(visitors, 0, stretchedArray, 0, visitors.length - 1);
			// visitors = stretchedArray;
			// }
			if (visitors[i] == null) {
				visitors[i] = visitor;
				visitors[i].setId(i + 1);
				break;
			}
		}
	}

	public Visitor[] getVisitors() {
		return visitors;
	}

	public void updateVisitor(Visitor visitor, RoomHistory history) {
		for(int i=0; i<visitor.getHistories().length; i++) {
			if(visitor.getHistories()[i] == null) {
				visitor.getHistories()[i] = history;
				break;
			}
		}
	}
	
	public void deleteVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.length; i++) {
			if(visitors[i] == visitor) {
				visitors[i] = null;
			}
		}
	}
}