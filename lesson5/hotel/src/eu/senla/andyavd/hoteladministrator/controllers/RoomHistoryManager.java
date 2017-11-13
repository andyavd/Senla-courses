package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.managers.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.api.storages.IRoomHistoriesStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoriesStorage;

public class RoomHistoryManager implements IRoomHistoryManager {

	private IRoomHistoriesStorage roomHistoryManager = new RoomHistoriesStorage();

	@Override
	public void addHistory(RoomHistory history) {
		roomHistoryManager.addHistory(history);
	}

	@Override
	public List<AEntity> showHistories() {
		return roomHistoryManager.getHistory();
	}
}
