package eu.senla.andyavd.hoteladministrator.controller;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.model.storages.RoomHistoryStorage;

public class RoomHistoryManager implements IRoomHistoryManager {

	public RoomHistoryStorage his = new RoomHistoryStorage();

	@Override
	public void addHistory(RoomHistory history) {
		his.addHistory(history);
	}

	@Override
	public List<Entity> showHistories() {
		return his.getHistory();
	}
}
