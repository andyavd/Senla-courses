package eu.senla.andyavd.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.annotations.Storage;
import eu.senla.andyavd.api.storages.IRoomHistoriesStorage;
import eu.senla.andyavd.entities.RoomHistory;
import eu.senla.andyavd.utils.csvutils.CommonCSVReader;
import eu.senla.andyavd.utils.csvutils.CommonCSVWriter;

@Storage
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
	
	@Override
	public void importFromCsv() {
		@SuppressWarnings("unchecked")
		List<RoomHistory> importedRoomHistories = (List<RoomHistory>) CommonCSVReader.readFromFile(RoomHistory.class);
		for (RoomHistory history : importedRoomHistories) {
			if (!histories.contains(history)) {
				histories.add(history);
			}
		}
	}

	@Override
	public void exportToCsv() {
		CommonCSVWriter.writeToFile(histories);
	}
}
