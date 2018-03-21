package eu.senla.andyavd.hotel.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.annotations.annotations.Storage;
import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.api.services.IAuditService;
import eu.senla.andyavd.hotel.api.services.IRoomHistoryService;
import eu.senla.andyavd.hotel.api.services.IRoomService;
import eu.senla.andyavd.hotel.api.services.IServiceService;
import eu.senla.andyavd.hotel.api.services.IUserService;
import eu.senla.andyavd.hotel.api.services.IVisitorService;
import eu.senla.andyavd.hotel.dao.dbconnector.HibernateUtil;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Audit;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.beans.RoomHistory;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotel.entity.enums.SortType;
import eu.senla.andyavd.hotel.property.Settings;
import eu.senla.andyavd.hotel.utils.common.Printer;

@Storage
public class HotelManager implements IHotelManager {

	private final static Logger logger = Logger.getLogger(HotelManager.class);

	private IRoomHistoryService roomHistoryService = (IRoomHistoryService) DependencyInjection.getInstance()
			.getClassInstance(IRoomHistoryService.class);
	private IRoomService roomService = (IRoomService) DependencyInjection.getInstance()
			.getClassInstance(IRoomService.class);
	private IServiceService serviceService = (IServiceService) DependencyInjection.getInstance()
			.getClassInstance(IServiceService.class);
	private IVisitorService visitorService = (IVisitorService) DependencyInjection.getInstance()
			.getClassInstance(IVisitorService.class);

	private IUserService userService = (IUserService) DependencyInjection.getInstance()
			.getClassInstance(IUserService.class);
	private IAuditService auditService = (IAuditService) DependencyInjection.getInstance()
			.getClassInstance(IAuditService.class);

	private static IHotelManager hotelManager;

	private HotelManager() {
		start();
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
			roomService.addRoom(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Room> getRooms() {
		try {
			return roomService.getRooms(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void updateRoom(Room room) {
		try {
			roomService.updateRoom(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteRoom(Room room) {
		try {
			roomService.deleteRoom(room);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Room getRoomById(int id) {
		try {
			return roomService.getRoomById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void cloneRoom(int id) {
		try {
			roomService.cloneRoom(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Room> getUsedRooms() {
		try {
			return roomService.getUsedRooms();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Room> getEmptyRooms() {
		try {
			return roomService.getEmptyRooms(SortType.roomNumber);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Integer getEmptyRoomsNumber() {
		try {
			return roomService.getEmptyRoomsNumber();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Room> sortRoomsByCapacity() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomService.getRooms(SortType.capacity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}

	@Override
	public List<Room> sortRoomsByPrice() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomService.getRooms(SortType.dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}

	@Override
	public List<Room> sortRoomsByStars() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomService.getRooms(SortType.stars);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByCapacity() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomService.getEmptyRooms(SortType.capacity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByPrice() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomService.getEmptyRooms(SortType.dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByStars() {
		List<Room> sortedRooms = null;
		try {
			sortedRooms = roomService.getEmptyRooms(SortType.stars);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedRooms;
	}

	@Override
	public Double billVisitor(int visitorId) {
		try {
			return roomHistoryService.billVisitor(visitorId) * roomService.getVisitorsRoom(visitorId).getDailyPrice();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Room> getEmptyRoomsOnDate(Date date) {
		try {
			return roomService.getEmptyRoomsOnDate(date);
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
			roomService.changeRoomStatus(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Visitor> getLastVisitorsOfRoom(int roomId) {
		try {
			return roomHistoryService.getLastVisitorsOfRoom(roomId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void changeRoomPrice(int id, Double dailyPrice) {
		try {
			roomService.changeRoomPrice(id, dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/* ========================Visitors======================== */

	@Override
	public void addVisitor(Visitor visitor) {
		try {
			visitorService.addVisitor(visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Visitor> getVisitors() {
		try {
			return visitorService.getVisitors(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void updateVisitor(Visitor visitor) {
		try {
			visitorService.updateVisitor(visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		try {
			visitorService.deleteVisitor(visitor);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Visitor getVisitorById(int id) {
		try {
			return visitorService.getVisitorById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Visitor> sortVisitorsByName() {
		try {
			return visitorService.getVisitors(SortType.lastName);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void addServicesToVisitor(int visitorId, Service service) {
		try {
			roomHistoryService.addServicesToVisitor(visitorId, service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Service> getVisitorServices(int visitorId) {
		try {
			return roomHistoryService.getVisitorServices(visitorId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public Integer getTotalVisitorsOnDate(Date date) {
		try {
			return roomHistoryService.getTotalVisitorsOnDate(date);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/* ========================Services======================== */

	@Override
	public void addService(Service service) {
		try {
			serviceService.addService(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Service> getServices() {
		try {
			return serviceService.getServices(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void updateService(Service service) {
		try {
			serviceService.updateService(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void deleteService(Service service) {
		try {
			serviceService.deleteService(service);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public Service getServiceById(int id) {
		try {
			return serviceService.getServiceById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Service> sortServicesByPrice() {
		List<Service> sortedServices = null;
		try {
			sortedServices = serviceService.getServices(SortType.dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sortedServices;
	}

	@Override
	public void changeServicePrice(int id, double dailyPrice) {
		try {
			serviceService.changeServicePrice(id, dailyPrice);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	/* ==========================User========================== */

	@Override
	public void registerUser(User user) {
		try {
			userService.registerUser(user);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public User getUserByUsername(String username) {
		try {
			return userService.getUserByUsername(username);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/* ========================Process========================= */

	@Override
	public List<RoomHistory> getHistories() {
		try {
			return roomHistoryService.getHistories(null);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void checkInVisitor(RoomHistory history) {
		try {
			roomHistoryService.addHistory(history);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public List<Visitor> getCheckedVisitors() {
		try {
			return visitorService.getCheckedVisitors();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Visitor> getCheckedVisitorsWithServices() {
		try {
			return visitorService.getCheckedVisitorsWithServices();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	@Override
	public void checkOutVisitor(int visitorId) {
		try {
			roomHistoryService.checkOutVisitor(visitorId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public void start() {
		Printer.print("Hello-Hello!");
	}

	@Override
	public void exit() {
		System.out.println("Bye-Bye");
		HibernateUtil.getInstance().closeSessionFactory();
	}

	// ========================CSV=========================

	public void exportRoomsToCSV() {
		try {
			roomService.exportToCsv();
			roomHistoryService.exportToCsv();
		} catch (Exception e) {
			logger.error("Failed to export Rooms data to CSV file", e);
		}
	}

	public void exportServicesToCSV() {
		try {
			serviceService.exportToCsv();
		} catch (Exception e) {
			logger.error("Failed to export Services data to CSV file", e);
		}
	}

	public void exportVisitorsToCSV() {
		try {
			visitorService.exportToCsv();
		} catch (Exception e) {
			logger.error("Failed to export Visitors data to CSV file", e);
		}
	}

	public void importRoomsFromCSV() {
		try {
			roomService.importFromCsv();
		} catch (Exception e) {
			logger.error("Rooms were not imported!", e);
		}
	}

	public void importServicesFromCSV() {
		try {
			serviceService.importFromCsv();
		} catch (Exception e) {
			logger.error("Services were not imported!", e);
		}
	}

	public void importVisitorsFromCSV() {
		try {
			visitorService.importFromCsv();
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}

	// ========================Audit=========================
	public void addAudit(User user, String action) {
		try {
			String date = LocalDateTime.now().toString();
			String name = user.getUserName();
			auditService.addAudit(new Audit(date, name, action));
		} catch (Exception e) {
			logger.error(e.getMessage());
			
		}
	}
}
