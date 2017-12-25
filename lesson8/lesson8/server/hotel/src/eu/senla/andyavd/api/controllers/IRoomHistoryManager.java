package lesson8.server.hotel.src.eu.senla.andyavd.api.controllers;

import java.util.List;

import lesson8.server.hotel.src.eu.senla.andyavd.entities.RoomHistory;

public interface IRoomHistoryManager {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistories();

}