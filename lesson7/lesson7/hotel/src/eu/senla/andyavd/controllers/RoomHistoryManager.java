package lesson7.hotel.src.eu.senla.andyavd.controllers;

import java.util.List;

import lesson7.hotel.src.eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import lesson7.hotel.src.eu.senla.andyavd.entities.RoomHistory;
import lesson7.hotel.src.eu.senla.andyavd.storages.RoomHistoriesStorage;

public class RoomHistoryManager implements IRoomHistoryManager {
	
	public RoomHistoryManager() {
	}
	
	@Override
	public void addHistory(RoomHistory history) {
		RoomHistoriesStorage.getInstance().addHistory(history);
	}

	@Override
	public List<RoomHistory> getHistories() {
		return RoomHistoriesStorage.getInstance().getHistories();
	}
}
