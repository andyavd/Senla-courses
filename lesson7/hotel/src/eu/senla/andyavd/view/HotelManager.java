package eu.senla.andyavd.view;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.api.controllers.IRoomHistoryManager;
import eu.senla.andyavd.api.controllers.IRoomManager;
import eu.senla.andyavd.api.controllers.IServiceManager;
import eu.senla.andyavd.api.controllers.IVisitorManager;
import eu.senla.andyavd.api.view.IHotelManager;
import eu.senla.andyavd.di.DependencyInjection;
import eu.senla.andyavd.entities.Room;
import eu.senla.andyavd.entities.RoomHistory;
import eu.senla.andyavd.entities.Service;
import eu.senla.andyavd.entities.Visitor;
import eu.senla.andyavd.enums.RoomHistoryStatus;
import eu.senla.andyavd.enums.RoomStatus;
import eu.senla.andyavd.properties.Settings;
import eu.senla.andyavd.utils.SerializationUtil;
import eu.senla.andyavd.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.utils.exceptions.NotEmptyRoomException;
import eu.senla.andyavd.utils.sorters.rooms.ByCapacity;
import eu.senla.andyavd.utils.sorters.rooms.ByPrice;
import eu.senla.andyavd.utils.sorters.rooms.ByStars;
import eu.senla.andyavd.utils.sorters.services.ByDailyPrice;
import eu.senla.andyavd.utils.sorters.services.ByName;
import eu.senla.andyavd.utils.sorters.visitors.ByLastName;

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
	public void deleteRoom(Room room) {
		roomManager.deleteRoom(room);
	}
	
	@Override
	public Room cloneRoom(Room room) {
		return roomManager.cloneRoom(room);
	}

	@Override
	public List<Room> getRooms() {
		return roomManager.getRooms();
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
		List<Room> sortedRooms = roomManager.sortRooms(new ByCapacity());
		return sortedRooms;
	}

	@Override
	public List<Room> sortRoomsByPrice() {
		List<Room> sortedRooms = roomManager.sortRooms(new ByPrice());
		return sortedRooms;
	}

	@Override
	public List<Room> sortRoomsByStars() {
		List<Room> sortedRooms = roomManager.sortRooms(new ByStars());
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByCapacity() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(new ByCapacity());
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByPrice() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(new ByPrice());
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByStars() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(new ByStars());
		return sortedRooms;
	}

	@Override
	public Double billVisitor(Visitor visitor) throws NullPointerException {

		if (visitor.getHistory() != null) {

			Period period = Period.between(visitor.getHistory().getCheckInDate(),
					visitor.getHistory().getCheckOutDate());
			int days = period.getDays();
			double payment = visitor.getHistory().getRoom().getDailyPrice() * days;

			return payment;
		} else {
			logger.error("No such visitor to bill!");
			return null;
		}
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(LocalDate date) {
		return roomManager.getEmptyRoomsOnDate(date);
	}

	@Override
	public boolean isRoomStatus() {

		boolean isAllowed = Boolean.parseBoolean(Settings.getInstance().getProperty("status"));

		return isAllowed;
	}

	@Override
	public void changeRoomStatus(Room room) {

		if (room.getStatus().equals(RoomStatus.EMPTY)) {

			room.setStatus(RoomStatus.SERVICED);
			roomManager.updateStorage(room.getId(), room);
		}
	}

	@Override
	public List<RoomHistory> getLastVisitorsOfRoom(Room room) {

		Integer variableProperty = Integer.parseInt(Settings.getInstance().getProperty("count"));
		List<RoomHistory> lastVisitorsOfRoom = new ArrayList<RoomHistory>();
		List<RoomHistory> histories = room.getHistories();

		if (histories.size() <= variableProperty) {

			for (int i = 0; i < histories.size(); i++) {
				RoomHistory history = histories.get(i);
				if (history != null) {
					lastVisitorsOfRoom.add(history);
				}
			}

		} else {
			for (int i = histories.size() - variableProperty; i < histories.size(); i++) {
				RoomHistory history = histories.get(i);
				if (history != null) {
					lastVisitorsOfRoom.add(history);
				}
			}
		}
		return lastVisitorsOfRoom;
	}

	@Override
	public void changePriceOnRoom(Room room, double dailyPrice) {
		room.setDailyPrice(dailyPrice);
	}

	@Override
	public Room getRoomById(Integer id) {
		return roomManager.getRoomById(id);
	}

	public void setRooms(List<Room> rooms) {
		roomManager.setRooms(rooms);
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
	public void deleteVisitor(Visitor visitor) {
		visitorManager.deleteVisitor(visitor);
	}

	@Override
	public List<Visitor> sortVisitorsByName() {
		List<Visitor> sortedVisitors = visitorManager.sortVisitors(new ByLastName());
		return sortedVisitors;
	}

	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) throws NullPointerException {
		visitorManager.addServicesToVisitor(visitor, service);
	}

	@Override
	public List<Service> getVisitorServices(Visitor visitor) {

		List<Service> visitorServices = new ArrayList<Service>();
		List<Service> services = visitorManager.getVisitorServices(visitor);
		for (int i = 0; i < services.size(); i++) {
			Service service = services.get(i);
			if (service != null) {
				visitorServices.add(service);
			}
		}
		return visitorServices;
	}

	@Override
	public List<Service> sortVisitorServicesByPrice(Visitor visitor) {
		List<Service> sortedServices = visitorManager.sortVisitorServicesByPrice(visitor, new ByDailyPrice());
		return sortedServices;
	}

	@Override
	public Integer getTotalVisitorsOnDate(LocalDate date) {
		return visitorManager.getTotalVisitorsOnDate(date);
	}

	@Override
	public Visitor getVisitorById(Integer id) {
		return visitorManager.getVisitorById(id);
	}

	@Override
	public void setVisitors(List<Visitor> visitors) {
		visitorManager.setVisitors(visitors);
	};

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
	public void setServices(List<Service> services) {
		serviceManager.setServices(services);
	}

	@Override
	public void deleteService(Service service) {
		serviceManager.deleteService(service);
	}

	@Override
	public List<Service> sortServicesByName() {
		List<Service> sortedServices = serviceManager.sortServices(new ByName());
		return sortedServices;
	}

	@Override
	public List<Service> sortServicesByPrice() {
		List<Service> sortedServices = serviceManager.sortServices(new ByDailyPrice());
		return sortedServices;
	}

	@Override
	public void changePriceOnService(Service service, double dailyPrice) {
		service.setDailyPrice(dailyPrice);
	}

	@Override
	public Service getServiceById(Integer id) {
		return serviceManager.getServiceById(id);
	}

	/* ========================Process========================= */

	@Override
	public List<RoomHistory> getHistories() {
		return roomHistoryManager.getHistories();

	}

	@Override
	public void checkInVisitor(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate)
			throws NotEmptyRoomException {

		if (room.getStatus().equals(RoomStatus.EMPTY)) {
			RoomHistory newHistory = new RoomHistory();

			newHistory.setVisitor(visitor);
			newHistory.setRoom(room);
			newHistory.setCheckInDate(checkInDate);
			newHistory.setCheckOutDate(checkOutDate);
			newHistory.setStatus(RoomHistoryStatus.CHECKIN);

			roomHistoryManager.addHistory(newHistory);

			roomManager.updateRoom(room, newHistory);
			room.setStatus(RoomStatus.OCCUPIED);
			visitorManager.updateVisitor(visitor, newHistory);

		} else {
			logger.error("The Room is not empty!");
			return;
		}
	}

	@Override
	public void checkOutVisitor(Visitor visitor, Room room) throws EmptyRoomException {

		if (room.getStatus().equals(RoomStatus.OCCUPIED)) {
			
			List<RoomHistory> histories = room.getHistories();
			for (int i = 0; i < histories.size(); i++) {
				RoomHistory history = histories.get(i);
				if (history != null && history.getVisitor().equals(visitor)&& history.getStatus().equals(RoomHistoryStatus.CHECKIN)){
					history.setStatus(RoomHistoryStatus.CHECKOUT);
					visitor.setHistory(null);
					room.setStatus(RoomStatus.EMPTY);
					break;
				}
			}

		} else {
			logger.error("The room is empty!");
			return;
		}
	}

	@Override
	public void exit() {
		SerializationUtil.serialize(getRooms(), getServices(), getVisitors(), getHistories());
	}

	@Override
	public void loadFromFile() {
		try {
			SerializationUtil.deserialize();
		} catch (Exception e) {
			logger.error("Failed to load from file!", e);
		}
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
