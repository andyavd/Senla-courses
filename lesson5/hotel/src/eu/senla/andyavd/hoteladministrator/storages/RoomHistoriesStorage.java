package eu.senla.andyavd.hoteladministrator.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IRoomHistoriesStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public class RoomHistoriesStorage implements IRoomHistoriesStorage{

	private List<AEntity> histories = new ArrayList<AEntity>();
	private int counter = 0;
	
	@Override
	public void addHistory(RoomHistory history) {
		histories.add(history);
		histories.get(counter).setId(counter + 1);
		counter++;
	}

	@Override
	public List<AEntity> getHistory() {
		return histories;
	}
}