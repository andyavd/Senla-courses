package eu.senla.andyavd.hoteladministrator.managers;

import eu.senla.andyavd.hoteladministrator.api.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoryStorage;

public class RoomHistoryManager implements IRoomHistoryManager {

	public RoomHistoryStorage roomHistoryStorage = new RoomHistoryStorage();

	@Override
	public void addHistory(RoomHistory history) {
		roomHistoryStorage.addHistory(history);
	}

	@Override
	public RoomHistory[] showHistories() {
		return roomHistoryStorage.getHistory();
	}
}
