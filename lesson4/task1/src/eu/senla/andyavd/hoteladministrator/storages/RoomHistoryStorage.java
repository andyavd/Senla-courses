package eu.senla.andyavd.hoteladministrator.storages;

import java.util.Arrays;

import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

public class RoomHistoryStorage {

	private RoomHistory[] histories = new RoomHistory[10];
	private int counter = 0;

	private RoomHistory[] castEntitiesArray(Entity[] entities) {
		RoomHistory[] historyArray = Arrays.copyOf(entities, entities.length, RoomHistory[].class);
		return historyArray;
	}
	
	public void addHistory(RoomHistory history) {

		 if (histories[histories.length-1] != null) {
		 histories = castEntitiesArray(ArrayWorker.expandArray(histories));
		 }
		histories[counter] = history;
		histories[counter].setId(counter + 1);
		counter++;
	}

	public RoomHistory[] getHistory() {
		return histories;
	}
}
