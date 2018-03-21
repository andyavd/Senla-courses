package eu.senla.andyavd.hotel.api.controller;

import java.util.Date;
import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.entity.beans.Visitor;

public interface IHotelManager {

	/* ========================Rooms=========================== */

	void addRoom(Room room);

	List<Room> getRooms();

	void updateRoom(Room room);

	List<Room> getEmptyRooms();

	List<Room> getUsedRooms();

	void deleteRoom(Room room);

	Integer getEmptyRoomsNumber();

	List<Room> sortRoomsByCapacity();

	List<Room> sortRoomsByPrice();

	List<Room> sortRoomsByStars();

	List<Room> sortEmptyRoomsByCapacity();

	List<Room> sortEmptyRoomsByPrice();

	List<Room> sortEmptyRoomsByStars();

	void cloneRoom(int id);

	Double billVisitor(int visitorId);

	List<Room> getEmptyRoomsOnDate(Date date);

	void changeRoomStatus(int id);

	void changeRoomPrice(int id, Double dailyPrice);

	List<Visitor> getLastVisitorsOfRoom(int roomId);

	Room getRoomById(int id);

	/* ========================Visitors======================== */

	void addVisitor(Visitor visitor);

	List<Visitor> getVisitors();

	void updateVisitor(Visitor visitor);

	void deleteVisitor(Visitor visitor);

	List<Visitor> getCheckedVisitors();

	List<Visitor> getCheckedVisitorsWithServices();

	List<Visitor> sortVisitorsByName();

	void addServicesToVisitor(int visitorId, Service service);

	List<Service> getVisitorServices(int visitorId);

	Integer getTotalVisitorsOnDate(Date date);

	Visitor getVisitorById(int id);

	/* ========================Services======================== */

	void addService(Service service);

	List<Service> getServices();

	void updateService(Service service);

	void deleteService(Service service);

	List<Service> sortServicesByPrice();

	void changeServicePrice(int id, double dailyPrice);

	Service getServiceById(int id);

	/* ==========================User========================== */

	void registerUser(User user);

	User getUserByUsername(String username);

	/* ========================Process========================= */

	void checkInVisitor(RoomHistory history);

	void checkOutVisitor(int visitorId);

	void exit();

	void start();

	boolean isRoomStatus();

	List<RoomHistory> getHistories();

	void exportRoomsToCSV();

	void importRoomsFromCSV();

	void exportServicesToCSV();

	void importServicesFromCSV();

	void importVisitorsFromCSV();

	void exportVisitorsToCSV();
	
	void addAudit(User user, String action);
}
