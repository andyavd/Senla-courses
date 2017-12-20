package eu.senla.andyavd.hoteladministrator.api.controllers;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public interface IRoomHistoryManager {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistories();

}