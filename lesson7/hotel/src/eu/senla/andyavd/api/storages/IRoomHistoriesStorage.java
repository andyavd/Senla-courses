package eu.senla.andyavd.api.storages;

import java.util.List;

import eu.senla.andyavd.entities.RoomHistory;

public interface IRoomHistoriesStorage {

	public void addHistory(RoomHistory history);

	public List<RoomHistory> getHistories();

	void setHistories(List<RoomHistory> histories);

	void importFromCsv();

	void exportToCsv();

}
