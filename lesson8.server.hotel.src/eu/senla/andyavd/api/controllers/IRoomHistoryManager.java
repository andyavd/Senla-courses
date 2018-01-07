package eu.senla.andyavd.api.controllers;

import java.util.List;

import eu.senla.andyavd.RoomHistory;

public interface IRoomHistoryManager {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistories();

	void importFromCsv();

	void exportToCsv();

}