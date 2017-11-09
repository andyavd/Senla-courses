package eu.senla.andyavd.hoteladministrator.api;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;

public interface IRoomHistoryManager {

	public void addHistory(RoomHistory history);

	public List<Entity> showHistories();

}