package eu.senla.andyavd.controllers;

import java.util.List;

import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import eu.senla.andyavd.api.storages.IRoomHistoriesStorage;

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
