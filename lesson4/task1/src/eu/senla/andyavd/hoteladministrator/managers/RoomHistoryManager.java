package eu.senla.andyavd.hoteladministrator.managers;

import eu.senla.andyavd.hoteladministrator.actions.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoryStorage;

public class RoomHistoryManager implements IRoomHistoryManager {

	public RoomHistoryStorage his = new RoomHistoryStorage();

	@Override
	public void addHistory(RoomHistory history) {
		his.addHistory(history);
	}

	@Override
	public void showHistories() {
		for (int i = 0; i < his.getHistory().length; i++) {
			if (his.getHistory()[i] != null) {
				// Printer.print(his.getHistory()[i].toString());
			}
		}
	}
}
