package lesson7.hotel.src.eu.senla.andyavd.api.controllers;

import java.util.List;

import lesson7.hotel.src.eu.senla.andyavd.entities.RoomHistory;

public interface IRoomHistoryManager {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistories();

}