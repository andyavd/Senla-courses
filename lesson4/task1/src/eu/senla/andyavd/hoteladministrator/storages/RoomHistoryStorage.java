package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public class RoomHistoryStorage {

	private RoomHistory[] histories = new RoomHistory[10];
	private int counter = 0;

	public void addHistory(RoomHistory history) {

		// if (histories[histories.length-1] != null) {
		// histories = (RoomHistory[]) ArrayWorker.expand(histories);
		// }
		histories[counter] = history;
		histories[counter].setId(counter + 1);
		counter++;
	}

	public RoomHistory[] getHistory() {
		return histories;
	}
}
