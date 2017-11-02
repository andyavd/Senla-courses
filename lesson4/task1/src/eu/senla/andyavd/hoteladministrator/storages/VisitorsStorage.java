package eu.senla.andyavd.hoteladministrator.storages;

import java.util.Arrays;

import com.danco.training.TextFileWorker;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

public class VisitorsStorage {

	private Visitor[] visitors = new Visitor[5];
	private int counter = 0;
	private final TextFileWorker textFileWorker = new TextFileWorker(Path.VISITOR_STORAGE_PATH.getPath());

	public void addVisitor(Visitor visitor) {
//		 if (visitors[visitors.length-1] != null) {
//		 visitors = (Visitor[]) ArrayWorker.expandVisitor(visitors);
//		 }
		visitors[counter] = visitor;
		visitors[counter].setId(counter + 1);
		counter++;
	}

	public Visitor[] getVisitors() {
		return visitors;
	}

	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitor.setHistory(history);
	}

	public void deleteVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.length; i++) {
			if (visitors[i] == visitor) {
				visitors[i] = null;
			}
		}
	}
	public void save() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(visitors), ArrayWorker.getArraySize(visitors));
		textFileWorker.writeToFile(stringArray);
	}	
}
