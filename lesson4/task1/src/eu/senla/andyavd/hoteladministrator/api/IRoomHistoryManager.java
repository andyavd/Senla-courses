package eu.senla.andyavd.hoteladministrator.api;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomHistoryManager {

	public void addHistory(RoomHistory history);

	public RoomHistory[] showHistories();

}
