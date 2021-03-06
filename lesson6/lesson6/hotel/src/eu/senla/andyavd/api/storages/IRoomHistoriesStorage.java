package lesson6.hotel.src.eu.senla.andyavd.api.storages;

import java.util.List;

import lesson6.hotel.src.eu.senla.andyavd.entities.RoomHistory;

public interface IRoomHistoriesStorage {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistories();

	void setHistories(List<RoomHistory> histories);

}
