package lesson7.hotel.src.eu.senla.andyavd.controllers;

import java.util.List;

import lesson7.dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import lesson7.hotel.src.eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import lesson7.hotel.src.eu.senla.andyavd.api.storages.IRoomHistoriesStorage;
import lesson7.hotel.src.eu.senla.andyavd.entities.RoomHistory;

public class RoomHistoryManager implements IRoomHistoryManager {
	
	private IRoomHistoriesStorage historiesStorage = (IRoomHistoriesStorage) DependencyInjection.getInstance().getInstance(IRoomHistoriesStorage.class);
	
	public RoomHistoryManager() {
	}
	
	@Override
	public void addHistory(RoomHistory history) {
		historiesStorage.addHistory(history);
	}

	@Override
	public List<RoomHistory> getHistories() {
		return historiesStorage.getHistories();
	}
}
