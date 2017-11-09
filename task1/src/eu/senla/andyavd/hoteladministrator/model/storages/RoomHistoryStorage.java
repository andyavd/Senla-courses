package eu.senla.andyavd.hoteladministrator.model.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;

public class RoomHistoryStorage {

	private List<Entity> histories = new ArrayList<Entity>();
	private int counter = 0;
	
	public void addHistory(RoomHistory history) {
		histories.add(history);
		histories.get(counter).setId(counter + 1);
		counter++;
	}

	public List<Entity> getHistory() {
		return histories;
	}
}