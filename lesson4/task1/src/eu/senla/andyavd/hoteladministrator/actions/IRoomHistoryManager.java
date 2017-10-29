package eu.senla.andyavd.hoteladministrator.actions;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomHistoryManager {
	public void addHistory(RoomHistory history);
	public void showHistories();
}
