package eu.senla.andyavd.api.view;

import java.time.LocalDate;
import java.util.List;

import eu.senla.andyavd.Room;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.exceptions.EmptyRoomException;
import eu.senla.andyavd.exceptions.NotEmptyRoomException;

public interface IHotelManager {

	/* ========================Rooms=========================== */

	public void addRoom(Room room);

	public List<Room> getRooms();

	public List<Room> getEmptyRooms();

	public Integer getEmptyRoomsNumber(List<Room> rooms);

	public List<Room> sortRoomsByCapacity();

	public List<Room> sortRoomsByPrice();

	public List<Room> sortRoomsByStars();

	public List<Room> sortEmptyRoomsByCapacity();

	public List<Room> sortEmptyRoomsByPrice();

	public List<Room> sortEmptyRoomsByStars();

	public Double billVisitor(Visitor visitor);

	public List<Room> getEmptyRoomsOnDate(LocalDate date);

	public void changeRoomStatus(Room room);

	public List<RoomHistory> getLastVisitorsOfRoom(Room room);

	public void changePriceOnRoom(Room room, double dailyPrice);

	public Room getRoomById(Integer id);

	/* ========================Visitors======================== */

	public void addVisitor(Visitor visitor);

	public List<Visitor> getVisitors();

	public void deleteVisitor(Visitor visitor);

	public List<Visitor> sortVisitorsByName();

	public void addServicesToVisitor(Visitor visitor, Service service);

	public List<Service> getVisitorServices(Visitor visitor);

	public List<Service> sortVisitorServicesByPrice(Visitor visitor);

	public Integer getTotalVisitorsOnDate(LocalDate date);

	public Visitor getVisitorById(Integer id);

	/* ========================Services======================== */

	public void addService(Service service);

	public List<Service> getServices();

	void deleteService(Service service);

	public List<Service> sortServicesByName();

	public List<Service> sortServicesByPrice();

	public void changePriceOnService(Service service, double dailyPrice);

	public Service getServiceById(Integer id);

	/* ========================Process========================= */

	public void checkInVisitor(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate)
			throws NotEmptyRoomException;

	public void checkOutVisitor(Visitor visitor, Room room) throws EmptyRoomException;

	public void exit();

	public void loadFromFile();

	boolean isRoomStatus();

	public Room cloneRoom(Room room);

	public List<RoomHistory> getHistories();

	public void setServices(List<Service> services);

	public void setVisitors(List<Visitor> visitors);

	public void deleteRoom(Room room);

	public void exportRoomsToCSV();

	public void importRoomsFromCSV();

	public void exportServicesToCSV();

	public void importServicesFromCSV();

	public void importVisitorsFromCSV();

	public void exportVisitorsToCSV();

//	public void initializedManagers();
}
