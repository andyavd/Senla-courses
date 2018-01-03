package hotel.src.eu.senla.andyavd.controllers;

import java.util.List;

import dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import hotel.src.eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import hotel.src.eu.senla.andyavd.api.storages.IRoomHistoriesStorage;
import hotel.src.eu.senla.andyavd.entities.RoomHistory;

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
	
	@Override
	public void importFromCsv() {
		historiesStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
		historiesStorage.exportToCsv();
	}
}
