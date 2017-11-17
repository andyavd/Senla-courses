package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.controllers.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoriesStorage;

public class RoomHistoryManager implements IRoomHistoryManager {

	@Override
	public void addHistory(RoomHistory history) {
		RoomHistoriesStorage.getInstance().addHistory(history);
	}

	@Override
	public List<RoomHistory> getHistories() {
		return RoomHistoriesStorage.getInstance().getHistory();
	}
}
