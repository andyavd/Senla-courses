package eu.senla.andyavd.hoteladministrator.view;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.api.controllers.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.api.controllers.IRoomManager;
import eu.senla.andyavd.hoteladministrator.api.controllers.IServiceManager;
import eu.senla.andyavd.hoteladministrator.api.controllers.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.api.view.IHotelManager;
import eu.senla.andyavd.hoteladministrator.controllers.RoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.controllers.RoomManager;
import eu.senla.andyavd.hoteladministrator.controllers.ServiceManager;
import eu.senla.andyavd.hoteladministrator.controllers.VisitorManager;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.utils.SerializationUtil;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.NotEmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByCapacity;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByStars;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByName;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.visitors.SortingVisitorsByName;
import eu.senla.andyavd.properties.Settings;

public class HotelManager implements IHotelManager {

	final static Logger logger = Logger.getLogger(HotelManager.class);

	private IRoomManager roomManager = new RoomManager();
	private IVisitorManager visitorManager = new VisitorManager();
	private IServiceManager serviceManager = new ServiceManager();
	private IRoomHistoryManager roomHistoryManager = new RoomHistoryManager();

	private static HotelManager hotelManager;

	private HotelManager() {

	}

	public static HotelManager getInstance() {
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
	public void cloneRoom(Room room) throws IOException, ClassNotFoundException {
		roomManager.cloneRoom(room);
	}

	@Override
	public List<Room> getRooms() {
		return roomManager.getRooms();
	}

	@Override
	public List<Room> getEmptyRooms(List<Room> rooms) {
		return roomManager.getEmptyRooms(rooms);
	}

	@Override
	public Integer getEmptyRoomsNumber(List<Room> rooms) {
		return roomManager.getEmptyRoomsNumber(rooms);
	}

	@Override
	public List<Room> sortRoomsByCapacity() {
		List<Room> sortedRooms = roomManager.sortRooms(new SortingRoomsByCapacity());
		return sortedRooms;
	}

	@Override
	public List<Room> sortRoomsByPrice() {
		List<Room> sortedRooms = roomManager.sortRooms(new SortingRoomsByPrice());
		return sortedRooms;
	}

	@Override
	public List<Room> sortRoomsByStars() {
		List<Room> sortedRooms = roomManager.sortRooms(new SortingRoomsByStars());
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByCapacity() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(new SortingRoomsByCapacity());
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByPrice() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(new SortingRoomsByPrice());
		return sortedRooms;
	}

	@Override
	public List<Room> sortEmptyRoomsByStars() {
		List<Room> sortedRooms = roomManager.sortEmptyRooms(new SortingRoomsByStars());
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

		if (isAllowed) {
			return true;
		} else {
			return false;
		}
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

		List<RoomHistory> lastVisitorsOfRoom = new ArrayList<RoomHistory>();
		Integer variableProperty = Integer.parseInt(Settings.getInstance().getProperty("count"));

		if (room.getHistories().size() <= variableProperty) {

			for (int i = 0; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null) {
					lastVisitorsOfRoom.add(room.getHistories().get(i));
				}
			}

		} else {
			for (int i = room.getHistories().size() - variableProperty; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null) {
					lastVisitorsOfRoom.add(room.getHistories().get(i));
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
		List<Visitor> sortedVisitors = visitorManager.sortVisitors(new SortingVisitorsByName());
		return sortedVisitors;
	}

	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) throws NullPointerException {
		visitorManager.addServicesToVisitor(visitor, service);
	}

	@Override
	public List<Service> getVisitorServices(Visitor visitor) {

		List<Service> visitorServices = new ArrayList<Service>();

		for (int i = 0; i < visitorManager.getVisitorServices(visitor).size(); i++) {
			if (visitorManager.getVisitorServices(visitor).get(i) != null) {

				visitorServices.add(visitorManager.getVisitorServices(visitor).get(i));
			}
		}
		return visitorServices;
	}

	@Override
	public List<Service> sortVisitorServicesByPrice(Visitor visitor) {
		List<Service> sortedServices = visitorManager.sortVisitorServicesByPrice(visitor, new SortingServicesByPrice());
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
		List<Service> sortedServices = serviceManager.sortServices(new SortingServicesByName());
		return sortedServices;
	}

	@Override
	public List<Service> sortServicesByPrice() {
		List<Service> sortedServices = serviceManager.sortServices(new SortingServicesByPrice());
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
			throw new NotEmptyRoomException("Room is not empty!");
		}
	}

	@Override
	public void checkOutVisitor(Visitor visitor, Room room) throws EmptyRoomException {

		if (room.getStatus().equals(RoomStatus.OCCUPIED)) {
			for (int i = 0; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null && room.getHistories().get(i).getVisitor().equals(visitor)
						&& room.getHistories().get(i).getStatus().equals(RoomHistoryStatus.CHECKIN)) {

					room.getHistories().get(i).setStatus(RoomHistoryStatus.CHECKOUT);
					visitor.setHistory(null);
					room.setStatus(RoomStatus.EMPTY);

					break;
				}
			}

		} else {
			throw new EmptyRoomException("Room has no visitors!");
		}
	}

	@Override
	public void saveToFile() {

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
}
