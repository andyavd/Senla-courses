package eu.senla.andyavd.hoteladministrator.view;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.facade.IHotelManager;
import eu.senla.andyavd.hoteladministrator.api.managers.IRoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.api.managers.IRoomManager;
import eu.senla.andyavd.hoteladministrator.api.managers.IServiceManager;
import eu.senla.andyavd.hoteladministrator.api.managers.IVisitorManager;
import eu.senla.andyavd.hoteladministrator.controllers.RoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.controllers.RoomManager;
import eu.senla.andyavd.hoteladministrator.controllers.ServiceManager;
import eu.senla.andyavd.hoteladministrator.controllers.VisitorManager;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.NotEmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByCapacity;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByStars;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByName;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.visitors.SortingVisitorsByName;

public class HotelManager implements IHotelManager {

	private IRoomManager roomManager = new RoomManager();
	private IVisitorManager visitorManager = new VisitorManager();
	private IServiceManager serviceManager = new ServiceManager();
	private IRoomHistoryManager roomHistoryManager = new RoomHistoryManager();

	private static HotelManager hotelManager;

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
	public List<AEntity> getRooms() {
		return roomManager.getRooms();
	}

	@Override
	public List<AEntity> getEmptyRooms() {
		return roomManager.showEmptyRooms();
	}

	@Override
	public Integer getEmptyRoomsNumber() {
		return roomManager.showEmptyRoomsNumber();
	}

	@Override
	public String getRoomDetails(Room room) {

		StringBuilder s = new StringBuilder();
		s.append("Room details: ");
		s.append(roomManager.getRoomDetails(room));

		return s.toString();
	}

	@Override
	public List<AEntity> sortRoomsByCapacity() {
		List<AEntity> sortedRooms = roomManager.sortRooms(new SortingRoomsByCapacity());
		return sortedRooms;
	}

	@Override
	public List<AEntity> sortRoomsByPrice() {
		List<AEntity> sortedRooms = roomManager.sortRooms(new SortingRoomsByPrice());
		return sortedRooms;
	}

	@Override
	public List<AEntity> sortRoomsByStars() {
		List<AEntity> sortedRooms = roomManager.sortRooms(new SortingRoomsByStars());
		return sortedRooms;
	}

	@Override
	public List<AEntity> sortEmptyRoomsByCapacity() {
		List<AEntity> sortedRooms = roomManager.sortEmptyRooms(new SortingRoomsByCapacity());
		return sortedRooms;
	}

	@Override
	public List<AEntity> sortEmptyRoomsByPrice() {
		List<AEntity> sortedRooms = roomManager.sortEmptyRooms(new SortingRoomsByPrice());
		return sortedRooms;
	}

	@Override
	public List<AEntity> sortEmptyRoomsByStars() {
		List<AEntity> sortedRooms = roomManager.sortEmptyRooms(new SortingRoomsByStars());
		return sortedRooms;
	}

	@Override
	public String billVisitor(Visitor visitor) throws NullPointerException {

		if (visitor.getHistory() != null) {

			Period period = Period.between(visitor.getHistory().getCheckInDate(),
					visitor.getHistory().getCheckOutDate());
			int days = period.getDays();
			double payment = visitor.getHistory().getRoom().getDailyPrice() * days;

			StringBuilder s = new StringBuilder();
			s.append(visitor.getLastName());
			s.append(" needs to pay ");
			s.append(payment);
			s.append(" USD for the room #");
			s.append(visitor.getHistory().getRoom().getRoomNumber());

			return s.toString();
		} else {
			throw new NullPointerException("No such visitor to bill!");
		}
	}

	@Override
	public List<AEntity> getEmptyRoomsOnDate(LocalDate date) {

		List<AEntity> emptyRoomsOnDate = new ArrayList<AEntity>();

		for (int i = 0; i < roomManager.getRooms().size(); i++) {
			if (roomManager.getRooms().get(i) != null
					&& ((Room) roomManager.getRooms().get(i)).getStatus() == RoomStatus.OCCUPIED) {

				int r = 0;
				for (int k = 0; k < ((Room) roomManager.getRooms().get(i)).getHistories().size(); k++) {

					if (((Room) roomManager.getRooms().get(i)).getHistories().get(k) != null && (date
							.isBefore(((Room) roomManager.getRooms().get(i)).getHistories().get(k).getCheckInDate())
							|| date.isAfter(
									((Room) roomManager.getRooms().get(i)).getHistories().get(k).getCheckOutDate()))) {
						r = 1;
						continue;
					}

					if (r == 1) {
						emptyRoomsOnDate.add((roomManager.getRooms().get(i)));
						break;
					}
				}
			}
		}

		for (int i = 0; i < roomManager.getRooms().size(); i++) {
			if (roomManager.getRooms().get(i) != null
					&& ((Room) roomManager.getRooms().get(i)).getStatus() == RoomStatus.EMPTY) {
				emptyRoomsOnDate.add((roomManager.getRooms().get(i)));
			}
		}
		return emptyRoomsOnDate;
	}

	@Override
	public String changeRoomStatus(Room room) {

		String result = null;

		if (room.getStatus() == RoomStatus.EMPTY) {
			room.setStatus(RoomStatus.SERVICED);
			result = "Room is closed for services now.";
		} else if (room.getStatus() == RoomStatus.OCCUPIED) {
			result = "Room is occupied. Please wait for the Visitor to check-out.";
		} else {
			result = "Room is already being serviced.";
		}
		return result;
	}

	@Override
	public List<String> getLastVisitorsOfRoom(Room room) {

		List<String> lastVisitorsOfRoom = new ArrayList<String>();

		if (room.getHistories().size() <= 3) {

			for (int i = 0; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null) {
					StringBuilder s = new StringBuilder();
					s.append("Visitors of a Room #");
					s.append(room.getHistories().get(i).getRoom().getRoomNumber());
					s.append(": ");
					s.append(room.getHistories().get(i).getVisitor().getLastName());
					s.append(", cheched-in ");
					s.append(room.getHistories().get(i).getCheckInDate());
					s.append(", checked-out ");
					s.append(room.getHistories().get(i).getCheckOutDate());

					lastVisitorsOfRoom.add(s.toString());
				}
			}

		} else {
			for (int i = room.getHistories().size() - 3; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null) {
					StringBuilder s = new StringBuilder();
					s.append("Visitors of a Room #");
					s.append(room.getHistories().get(i).getRoom().getRoomNumber());
					s.append(": ");
					s.append(room.getHistories().get(i).getVisitor().getLastName());
					s.append(", cheched-in ");
					s.append(room.getHistories().get(i).getCheckInDate());
					s.append(", checked-out ");
					s.append(room.getHistories().get(i).getCheckOutDate());

					lastVisitorsOfRoom.add(s.toString());
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

	/* ========================Visitors======================== */

	@Override
	public void addVisitor(Visitor visitor) {
		visitorManager.addVisitor(visitor);
	}
	
	@Override
	public List<AEntity> getVisitors() {
		return visitorManager.getVisitors();
	}
	
	@Override
	public void deleteVisitor(Visitor visitor) {
		visitorManager.deleteVisitor(visitor);
	}
	
	@Override
	public List<AEntity> sortVisitorsByName() {
		List<AEntity> sortedVisitors = visitorManager.sortVisitors(new SortingVisitorsByName());
		return sortedVisitors;
	}
	
	@Override
	public void addServicesToVisitor(Visitor visitor, Service service) throws NullPointerException {
		visitorManager.addServicesToVisitor(visitor, service);
	}
	
	@Override
	public List<String> getVisitorServices(Visitor visitor) {

		List<String> visitorServices = new ArrayList<String>();

		for (int i = 0; i < visitorManager.showVisitorServices(visitor).size(); i++) {
			if (visitorManager.showVisitorServices(visitor).get(i) != null) {
				StringBuilder s = new StringBuilder();
				s.append("Visitor ");
				s.append(visitor.getLastName());
				s.append(" has used ");
				s.append(visitorManager.showVisitorServices(visitor).get(i));

				visitorServices.add(s.toString());
			}
		}
		return visitorServices;
	}
	
	@Override
	public List<AEntity> sortVisitorServicesByPrice(Visitor visitor) {
		List<AEntity> sortedServices = visitorManager.sortVisitorServicesByPrice(visitor, new SortingServicesByPrice());
		return sortedServices;
	}
	
	@Override
	public String getTotalVisitorsOnDate(LocalDate date) {

		Integer count = 0;
		for (int i = 0; i < visitorManager.getVisitors().size(); i++) {
			if (visitorManager.getVisitors().get(i) != null
					&& ((Visitor) visitorManager.getVisitors().get(i)).getHistory() != null) {
				if ((((Visitor) visitorManager.getVisitors().get(i)).getHistory().getCheckInDate().isBefore(date)
						|| ((Visitor) visitorManager.getVisitors().get(i)).getHistory().getCheckInDate().isEqual(date))
						&& (((Visitor) visitorManager.getVisitors().get(i)).getHistory().getCheckOutDate().isAfter(date)
								|| ((Visitor) visitorManager.getVisitors().get(i)).getHistory().getCheckOutDate()
										.isEqual(date))) {
					count++;
				}
			}
		}
		StringBuilder s = new StringBuilder();
		s.append("There are ");
		s.append(count);
		s.append(" visitors on ");
		s.append(date);

		return s.toString();
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
	public List<AEntity> getServices() {
		return serviceManager.getServices();
	}
	
	@Override
	public List<AEntity> sortServicesByName() {
		List<AEntity> sortedServices = serviceManager.sortServices(new SortingServicesByName());
		return sortedServices;
	}
	
	@Override
	public List<AEntity> sortServicesByPrice() {
		List<AEntity> sortedServices = serviceManager.sortServices(new SortingServicesByPrice());
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
	public String checkInVisitor(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate)
			throws NotEmptyRoomException {

		if (room.getStatus() == RoomStatus.EMPTY) {
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

			StringBuilder s = new StringBuilder();
			s.append(visitor.getLastName());
			s.append(" was checked-in. Room #");
			s.append(room.getRoomNumber());

			return s.toString();

		} else {
			throw new NotEmptyRoomException("Room is not empty!");
		}
	}
	
	@Override
	public String checkOutVisitor(Visitor visitor, Room room) throws EmptyRoomException {

		String result = "";

		if (room.getStatus() == RoomStatus.OCCUPIED) {
			for (int i = 0; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null && room.getHistories().get(i).getVisitor() == visitor
						&& room.getHistories().get(i).getStatus() == RoomHistoryStatus.CHECKIN) {

					room.getHistories().get(i).setStatus(RoomHistoryStatus.CHECKOUT);
					visitor.setHistory(null);
					room.setStatus(RoomStatus.EMPTY);

					StringBuilder s = new StringBuilder();
					s.append(visitor.getLastName());
					s.append(" has checked-out from Room #");
					s.append(room.getRoomNumber());

					result = s.toString();

					break;
				}
			}

		} else {
			throw new EmptyRoomException("Room has no visitors!");
		}
		return result;
	}
	
	@Override
	public void saveToFile() {
		roomManager.saveToFile();
		serviceManager.saveToFile();
		visitorManager.saveToFile();
	}

	@Override
	public void loadFromFile() {
		Printer.printStringArray(visitorManager.loadFromFile());
		Printer.printStringArray(roomManager.loadFromFile());
		Printer.printStringArray(serviceManager.loadFromFile());
	}
}