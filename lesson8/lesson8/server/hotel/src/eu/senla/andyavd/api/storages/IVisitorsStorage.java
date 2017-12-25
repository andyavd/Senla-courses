package lesson8.server.hotel.src.eu.senla.andyavd.api.storages;

import java.util.List;

import lesson8.server.hotel.src.eu.senla.andyavd.entities.RoomHistory;
import lesson8.server.hotel.src.eu.senla.andyavd.entities.Visitor;

public interface IVisitorsStorage {

	public List<Visitor> getVisitors();
	
	public void setVisitors(List<Visitor> visitors);

	public void addVisitor(Visitor visitor);

	public void updateVisitor(Visitor visitor, RoomHistory history);

	public void deleteVisitor(Visitor visitor);

	public Visitor getVisitorById(Integer id);
}
