package eu.senla.andyavd.hoteladministrator.api.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomHistoriesStorage {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistory();
}
