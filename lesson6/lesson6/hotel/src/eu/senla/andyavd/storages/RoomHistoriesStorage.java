package lesson6.hotel.src.eu.senla.andyavd.storages;

import java.util.ArrayList;
import java.util.List;

import lesson6.hotel.src.eu.senla.andyavd.api.storages.IRoomHistoriesStorage;
import lesson6.hotel.src.eu.senla.andyavd.entities.RoomHistory;

public class RoomHistoriesStorage implements IRoomHistoriesStorage{

	private List<RoomHistory> histories = new ArrayList<RoomHistory>();
	
	private static RoomHistoriesStorage roomHistoriesStorage;

	public static RoomHistoriesStorage getInstance() {
		if (roomHistoriesStorage == null) {
			roomHistoriesStorage = new RoomHistoriesStorage();
		}
		return roomHistoriesStorage;
	}
	
	@Override
	public void addHistory(RoomHistory history) {
		histories.add(history);
		histories.get(histories.size()-1).setId(histories.size());
	}

	@Override
	public List<RoomHistory> getHistories() {
		return histories;
	}
	
	@Override
	public void setHistories(List<RoomHistory> histories) {
		this.histories = histories;
	}
}
