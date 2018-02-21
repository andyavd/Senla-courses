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
import eu.senla.andyavd.api.managers.IRoomHistoryManager;
import eu.senla.andyavd.api.managers.IRoomManager;
import eu.senla.andyavd.api.managers.IServiceManager;
import eu.senla.andyavd.api.managers.IVisitorManager;
import eu.senla.andyavd.api.view.IHotelManager;
import eu.senla.andyavd.enums.SortType;


@Storage
public class HotelManager implements IHotelManager {

	private final static Logger logger = Logger.getLogger(HotelManager.class);

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
		try {
			roomManager.addRoom(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<Room> getRooms() {
		try {
			return roomManager.getRooms();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void updateRoom(Room room) {
		try {
			roomManager.updateRoom(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public void deleteRoom(int id) {
		try {
			roomManager.deleteRoom(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public Room getRoomById(int id) {
		try {
			return roomManager.getRoomById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public Room cloneRoom(int id) {
		try {
			return roomManager.cloneRoom(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Room> getUsedRooms() {
		try {
			return roomManager.getUsedRooms();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Room> getEmptyRooms() {
		try {
			return roomManager.getEmptyRooms();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public Integer getEmptyRoomsNumber(List<Room> rooms) {
		try {
			return roomManager.getEmptyRoomsNumber(rooms);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Room> sortRoomsByCapacity() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomManager.sortRooms(SortType.capacity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}
	@Override
	public List<Room> sortRoomsByPrice() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomManager.sortRooms(SortType.daily_price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}
	@Override
	public List<Room> sortRoomsByStars() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomManager.sortRooms(SortType.stars);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}
	@Override
	public List<Room> sortEmptyRoomsByCapacity() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomManager.sortEmptyRooms(SortType.capacity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}
	@Override
	public List<Room> sortEmptyRoomsByPrice() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomManager.sortEmptyRooms(SortType.daily_price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}
	@Override
	public List<Room> sortEmptyRoomsByStars() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomManager.sortEmptyRooms(SortType.stars);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}
	@Override
	public Double billVisitor(int visitorId) {
		try {
			return roomHistoryManager.billVisitor(visitorId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Room> getEmptyRoomsOnDate(LocalDate date) {
		try {
			return roomManager.getEmptyRoomsOnDate(date);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public boolean isRoomStatus() {
		boolean isAllowed = Settings.getCustomProperties().isStatus();
		return isAllowed;
	}
	@Override
	public void changeRoomStatus(int id) {
		try {
			roomManager.changeRoomStatus(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId) {
		try {
			return roomHistoryManager.getLastVisitorsOfRoom(roomId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void changeRoomPrice(int id, Double dailyPrice) {
		try {
			roomManager.changeRoomPrice(id, dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/* ========================Visitors======================== */

	@Override
	public void addVisitor(Visitor visitor) {
		try {
			visitorManager.addVisitor(visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<Visitor> getVisitors() {
		try {
			return visitorManager.getVisitors();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void updateVisitor(Visitor visitor) {
		try {
			visitorManager.updateVisitor(visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public void deleteVisitor(int id) {
		try {
			visitorManager.deleteVisitor(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public Visitor getVisitorById(int id) {
		try {
			return visitorManager.getVisitorById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Visitor> sortVisitorsByName() {
		try {
			return visitorManager.getVisitors();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void addServicesToVisitor(int visitorId, int serviceId) {
		try {
			roomHistoryManager.addServicesToVisitor(visitorId, serviceId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<Service> getVisitorServices(int visitorId) {
		try {
			return roomHistoryManager.getVisitorServices(visitorId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public Integer getTotalVisitorsOnDate(String date) {
		try {
			return roomHistoryManager.getTotalVisitorsOnDate(date);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/* ========================Services======================== */

	@Override
	public void addService(Service service) {
		try {
			serviceManager.addService(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<Service> getServices() {
		try {
			return serviceManager.getServices();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void updateService(Service service) {
		try {
			serviceManager.updateService(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public void deleteService(int id) {
		try {
			serviceManager.deleteService(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public Service getServiceById(int id) {
		try {
			return serviceManager.getServiceById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Service> sortServicesByPrice() {
		List<Service> sortedServices = null;
		try {
			sortedServices = serviceManager.sortServices(SortType.daily_price);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedServices;
	}
	@Override
	public void changeServicePrice(int id, double dailyPrice) {
		try {
			serviceManager.changeServicePrice(id, dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/* ========================Process========================= */

	@Override
	public List<RoomHistory> getHistories() {
		try {
			return roomHistoryManager.getHistories();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void checkInVisitor(RoomHistory history) {
		try {
			roomHistoryManager.addHistory(history);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public List<Visitor> getCheckedVisitors() {
		try {
			return visitorManager.getCheckedVisitors();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public List<Visitor> getCheckedVisitorsWithServices(){
		try {
			return visitorManager.getCheckedVisitorsWithServices();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	@Override
	public void checkOutVisitor(int visitorId) {
		try {
			roomHistoryManager.checkOutVisitor(visitorId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	@Override
	public void loadFromFile() {
		Printer.print("Hello-Hello!");
	}
	@Override
	public void exit() {
//		SerializationUtil.serialize(getRooms(), getServices(), getVisitors(), getHistories());
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
