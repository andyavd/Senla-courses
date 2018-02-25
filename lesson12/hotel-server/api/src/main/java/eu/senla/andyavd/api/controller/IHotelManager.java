package eu.senla.andyavd.api.controller;

import java.time.LocalDate;
import java.util.List;

import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.beans.RoomHistory;
import eu.senla.andyavd.beans.Service;
import eu.senla.andyavd.beans.Visitor;

public interface IHotelManager {

	/* ========================Rooms=========================== */

	void addRoom(Room room);

	List<Room> getRooms();

	void updateRoom(Room room);

	List<Room> getEmptyRooms();

	List<Room> getUsedRooms();

	Integer getEmptyRoomsNumber(List<Room> rooms);

	List<Room> sortRoomsByCapacity();

	List<Room> sortRoomsByPrice();

	List<Room> sortRoomsByStars();

	List<Room> sortEmptyRoomsByCapacity();

	List<Room> sortEmptyRoomsByPrice();

	List<Room> sortEmptyRoomsByStars();

	Room cloneRoom(int id);

	Double billVisitor(int visitorId);

	List<Room> getEmptyRoomsOnDate(LocalDate date);

	void changeRoomStatus(int id);

	void changeRoomPrice(int id, Double dailyPrice);

	List<Visitor> getLastVisitorsOfRoom(int roomId);

	Room getRoomById(int id);

	/* ========================Visitors======================== */

	void addVisitor(Visitor visitor);

	List<Visitor> getVisitors();

	void updateVisitor(Visitor visitor);

	void deleteVisitor(int id);

	List<Visitor> getCheckedVisitors();

	List<Visitor> getCheckedVisitorsWithServices();

	List<Visitor> sortVisitorsByName();

	void addServicesToVisitor(int visitorId, int serviceId);

	List<Service> getVisitorServices(int visitorId);

	Integer getTotalVisitorsOnDate(String date);

	Visitor getVisitorById(int id);

	/* ========================Services======================== */

	void addService(Service service);

	List<Service> getServices();

	void updateService(Service service);

	void deleteService(int id);

	List<Service> sortServicesByPrice();

	void changeServicePrice(int id, double dailyPrice);

	Service getServiceById(int id);

	/* ========================Process========================= */

	void checkInVisitor(RoomHistory history);

	void checkOutVisitor(int visitorId);

	void exit();

	void loadFromFile();

	boolean isRoomStatus();

	List<RoomHistory> getHistories();

	void deleteRoom(int id);

	void exportRoomsToCSV();

	void importRoomsFromCSV();

	void exportServicesToCSV();

	void importServicesFromCSV();

	void importVisitorsFromCSV();

	void exportVisitorsToCSV();
}
