package eu.senla.andyavd.hoteladministrator.managers;

import eu.senla.andyavd.hoteladministrator.api.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoryStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

public class RoomHistoryManager implements IRoomHistoryManager {

	public RoomHistoryStorage his = new RoomHistoryStorage();
	ArrayWorker aw = new ArrayWorker();

	@Override
	public void addHistory(RoomHistory history) {
		his.addHistory(history);
	}

	@Override
	public RoomHistory[] showHistories() {
		return aw.getNotNullRoomHistories(his.getHistory());
	}
}
