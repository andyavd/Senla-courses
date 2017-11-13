package eu.senla.andyavd.hoteladministrator.api.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public interface IVisitorsStorage {

	public List<AEntity> getVisitors();

	public void addVisitor(Visitor visitor);

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void deleteVisitor(Visitor visitor);

	public Visitor getVisitorById(Integer id);
}
