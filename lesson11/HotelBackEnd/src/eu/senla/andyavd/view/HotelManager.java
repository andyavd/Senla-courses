package eu.senla.andyavd.view;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.RoomHistory;
//import eu.senla.andyavd.SerializationUtil;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Settings;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.annotations.Storage;
import eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import eu.senla.andyavd.api.controllers.IRoomManager;
import eu.senla.andyavd.api.controllers.IServiceManager;
import eu.senla.andyavd.api.controllers.IVisitorManager;
import eu.senla.andyavd.api.view.IHotelManager;
import eu.senla.andyavd.enums.SortType;


@Storage
public class HotelManager implements IHotelManager {

	final static Logger logger = Logger.getLogger(HotelManager.class);

	private IRoomHistoryManager roomHistoryManager = (IRoomHistoryManager) DependencyInjection.getInstance().getInstance(IRoomHistoryManager.class);
	private IRoomManager roomManager = (IRoomManager) DependencyInjection.getInstance().getInstance(IRoomManager.class);
	private IServiceManager serviceManager = (IServiceManager) DependencyInjection.getInstance().getInstance(IServiceManager.class);
	private IVisitorManager visitorManager = (IVisitorManager) DependencyInjection.getInstance().getInstance(IVisitorManager.class);
	private static IHotelManager hotelManager;
	private HotelManager() {
		loadFromFile();
	}
	public static IHotelManager getInstance() {
		if (hotelManager == null) {
			hotelManager = new HotelManager();
		}
		return hotelManager;
	}
	
	/* ========================Rooms=========================== */

	@Override
	public void addRoom(Room room) {
		roomManager.addRoom(room);
	}
	@Override
	public List<Room> getRooms() {
		return roomManager.getRooms();
	}
	@Override
	public void updateRoom(Room room) {
		roomManager.updateRoom(room);
	}
	@Override
	public void deleteRoom(int id) {
		roomManager.deleteRoom(id);
	}
	@Override
	public Room getRoomById(int id) {
		return roomManager.getRoomById(id);
	}
	@Override
	public Room cloneRoom(int id) {
		return roomManager.cloneRoom(id);
	}
	@Override
	public List<Room> getUsedRooms() {
		return roomManager.getUsedRooms();
	}
	@Override
	public List<Room> getEmptyRooms() {
		return roomManager.getEmptyRooms();
	}
	@Override
	public Integer getEmptyRoomsNumber(List<Room> rooms) {
		return roomManager.getEmptyRoomsNumber(rooms);
	}
	@Override
	public List<Room> sortRoomsByCapacity() {
		List<Room> sortedRooms = roomManager.sortRooms(SortType.capacity);
		return sortedRooms;
	}
	@Override
	public List<Room> sortRoomsByPrice() {
		List<Room> sortedRooms = roomManager.sortRooms(SortType.daily_price);
		return sortedRooms;
	}
	@Override
	public List<Room> sortRoomsByStars() {
		List<Room> sortedRooms = roomManager.sortRooms(SortType.stars);
		return sortedRooms;
	}
	@Override
	public List<Room> sortEmptyRoomsByCapacity() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(SortType.capacity);
		return sortedRooms;
	}
	@Override
	public List<Room> sortEmptyRoomsByPrice() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(SortType.daily_price);
		return sortedRooms;
	}
	@Override
	public List<Room> sortEmptyRoomsByStars() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(SortType.stars);
		return sortedRooms;
	}
	@Override
	public Double billVisitor(int visitorId) {
		return roomHistoryManager.billVisitor(visitorId);
	}
	@Override
	public List<Room> getEmptyRoomsOnDate(LocalDate date) {
		return roomManager.getEmptyRoomsOnDate(date);
	}
	@Override
	public boolean isRoomStatus() {
		boolean isAllowed = Settings.getCustomProperties().isStatus();
		return isAllowed;
	}
	@Override
	public void changeRoomStatus(int id) {
		roomManager.changeRoomStatus(id);
	}
	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId) {
		return roomHistoryManager.getLastVisitorsOfRoom(roomId);
	}
	@Override
	public void changeRoomPrice(int id, Double dailyPrice) {
		roomManager.changeRoomPrice(id, dailyPrice);
	}

	/* ========================Visitors======================== */

	@Override
	public void addVisitor(Visitor visitor) {
		visitorManager.addVisitor(visitor);
	}
	@Override
	public List<Visitor> getVisitors() {
		return visitorManager.getVisitors();
	}
	@Override
	public void updateVisitor(Visitor visitor) {
		visitorManager.updateVisitor(visitor);
	}
	@Override
	public void deleteVisitor(int id) {
		visitorManager.deleteVisitor(id);
	}
	@Override
	public Visitor getVisitorById(int id) {
		return visitorManager.getVisitorById(id);
	}
	@Override
	public List<Visitor> sortVisitorsByName() {
		return visitorManager.getVisitors();
	}
	@Override
	public void addServicesToVisitor(int visitorId, int serviceId) {
		roomHistoryManager.addServicesToVisitor(visitorId, serviceId);
	}
	@Override
	public List<Service> getVisitorServices(int visitorId) {
		return roomHistoryManager.getVisitorServices(visitorId);
	}
	@Override
	public Integer getTotalVisitorsOnDate(String date) {
		return roomHistoryManager.getTotalVisitorsOnDate(date);
	}

	/* ========================Services======================== */

	@Override
	public void addService(Service service) {
		serviceManager.addService(service);
	}
	@Override
	public List<Service> getServices() {
		return serviceManager.getServices();
	}
	@Override
	public void updateService(Service service) {
		serviceManager.updateService(service);
	}
	@Override
	public void deleteService(int id) {
		serviceManager.deleteService(id);
	}
	@Override
	public Service getServiceById(int id) {
		return serviceManager.getServiceById(id);
	}
	@Override
	public List<Service> sortServicesByPrice() {
		List<Service> sortedServices = serviceManager.sortServices(SortType.daily_price);
		return sortedServices;
	}
	@Override
	public void changeServicePrice(int id, double dailyPrice) {
		serviceManager.changeServicePrice(id, dailyPrice);
	}

	/* ========================Process========================= */

	@Override
	public List<RoomHistory> getHistories() {
		return roomHistoryManager.getHistories();
	}
	@Override
	public void checkInVisitor(RoomHistory history) {
		roomHistoryManager.addHistory(history);
	}
	@Override
	public List<Visitor> getCheckedVisitors() {
		return visitorManager.getCheckedVisitors();
	}
	@Override
	public List<Visitor> getCheckedVisitorsWithServices(){
		return visitorManager.getCheckedVisitorsWithServices();
	}
	@Override
	public void checkOutVisitor(int visitorId) {
		roomHistoryManager.checkOutVisitor(visitorId);
	}
	@Override
	public void loadFromFile() {
		Printer.print("Hello-Hello!");
	}
	@Override
	public void exit() {
		System.out.println("Bye-Bye");
	}

	// ========================CSV=========================
	
	public void exportRoomsToCSV() {
		try {
			roomManager.exportToCsv();
			roomHistoryManager.exportToCsv();
		} catch (Exception e) {
			logger.error("Failed to export Rooms data to CSV file", e);
		}
	}
	public void exportServicesToCSV() {
		try {
			serviceManager.exportToCsv();
		} catch (Exception e) {
			logger.error("Failed to export Services data to CSV file", e);
		}
	}
	public void exportVisitorsToCSV() {
		try {
			visitorManager.exportToCsv();
		} catch (Exception e) {
			logger.error("Failed to export Visitors data to CSV file", e);
		}
	}
	public void importRoomsFromCSV() {
		try {
			roomManager.importFromCsv();
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}
	public void importServicesFromCSV() {
		try {
			serviceManager.importFromCsv();
		} catch (Exception e) {
			logger.error("Services were not imported!", e);
		}
	}
	public void importVisitorsFromCSV() {
		try {
			visitorManager.importFromCsv();
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}
}
