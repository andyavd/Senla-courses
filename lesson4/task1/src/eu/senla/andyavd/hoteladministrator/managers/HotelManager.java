package eu.senla.andyavd.hoteladministrator.managers;

import java.time.LocalDate;
import java.time.Period;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByCapacity;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByStars;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByName;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.visitors.SortingVisitorsByName;

public class HotelManager {

	RoomManager roomManager = new RoomManager();
	VisitorManager visitorManager = new VisitorManager();
	ServiceManager serviceManager = new ServiceManager();
	RoomHistoryManager roomHistoryManager = new RoomHistoryManager();

	public void createRoom(Room room) {
		roomManager.addRoom(room);
	}

	public void printRooms() {

		Printer.printArray(roomManager.getRooms());
		System.out.println(roomManager.getRooms().length);
	}

	public void printEmptyRooms() {
		Printer.printArray(roomManager.getEmptyRooms());
	}

	public void printEmptyRoomsNumber() {
		Printer.print(roomManager.getEmptyRoomsNumber().toString());
	}

	public void printDetailsOfRoom(Room room) {

		StringBuilder s = new StringBuilder();
		s.append("Room details: ");
		s.append(roomManager.getRoomDetails(room));

		Printer.print(s.toString());
	}

	public void sortRoomsByCapacity() {
		Room[] rooms = roomManager.sortRooms(new SortingRoomsByCapacity());
		Printer.printArray(rooms);
	}

	public void sortRoomsByPrice() {
		Room[] rooms = roomManager.sortRooms(new SortingRoomsByPrice());
		Printer.printArray(rooms);
	}

	public void sortRoomsByStars() {
		Room[] rooms = roomManager.sortRooms(new SortingRoomsByStars());
		Printer.printArray(rooms);
	}

	public void sortEmptyRoomsByCapacity() {
		Room[] rooms = roomManager.sortEmptyRooms(new SortingRoomsByCapacity());
		Printer.printArray(rooms);
	}

	public void sortEmptyRoomsByPrice() {
		Room[] rooms = roomManager.sortEmptyRooms(new SortingRoomsByPrice());
		Printer.printArray(rooms);
	}

	public void sortEmptyRoomsByStars() {
		Room[] rooms = roomManager.sortEmptyRooms(new SortingRoomsByStars());
		Printer.printArray(rooms);
	}

	public void billVisitor(Visitor visitor) {

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
			Printer.print(s.toString());
		} else {
			StringBuilder s = new StringBuilder();
			s.append(visitor.getLastName());
			s.append(" isn't checked-in any Room.");
			Printer.print(s.toString());
		}
	}

	public void showEmptyRoomsOnDate(LocalDate date) {

		for (int i = 0; i < roomManager.getRooms().length; i++) {
			if (roomManager.getRooms()[i] != null && roomManager.getRooms()[i].getStatus() == RoomStatus.OCCUPIED) {

				int r = 0;
				for (int k = 0; k < roomManager.getRooms()[i].getHistories().length; k++) {

					if (roomManager.getRooms()[i].getHistories()[k] != null
							&& (date.isBefore(roomManager.getRooms()[i].getHistories()[k].getCheckInDate())
									|| date.isAfter(roomManager.getRooms()[i].getHistories()[k].getCheckOutDate()))) {
						r = 1;
						continue;
					}

					if (r == 1) {
						Printer.print(roomManager.getRooms()[i].toString());
						break;
					}
				}
			}
		}

		for (int i = 0; i < roomManager.getRooms().length; i++) {
			if (roomManager.getRooms()[i] != null && roomManager.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
				Printer.print(roomManager.getRooms()[i].toString());
			}
		}
	}

	public void changeRoomStatus(Room room) {
		if (room.getStatus() == RoomStatus.EMPTY) {
			room.setStatus(RoomStatus.SERVICED);
			Printer.print("Room is closed for services now.");
		} else if (room.getStatus() == RoomStatus.OCCUPIED) {
			Printer.print("Room is occupied. Please wait for the Visitor to check-out.");
		} else {
			Printer.print("Room is already being serviced.");
		}
	}

	public void getLastVisitorsOfRoom(Room room) {

		if (ArrayWorker.getNotNullArray(room.getHistories()).length <=3 ) {

			for (int i = 0; i < ArrayWorker.getNotNullArray(room.getHistories()).length; i++) {
				if (room.getHistories()[i] != null) {
					StringBuilder s = new StringBuilder();
					s.append("Visitors of a Room #");
					s.append(room.getHistories()[i].getRoom().getRoomNumber());
					s.append(": ");
					s.append(room.getHistories()[i].getVisitor().getLastName());
					s.append(", cheched-in ");
					s.append(room.getHistories()[i].getCheckInDate());
					s.append(", checked-out ");
					s.append(room.getHistories()[i].getCheckOutDate());

					Printer.print(s.toString());
				}
			}
		} 
		else {
			for (int i = ArrayWorker.getNotNullArray(room.getHistories()).length-3; 
					i < ArrayWorker.getNotNullArray(room.getHistories()).length; i++) {
				if (room.getHistories()[i] != null) {
					StringBuilder s = new StringBuilder();
					s.append("Visitors of a Room #");
					s.append(room.getHistories()[i].getRoom().getRoomNumber());
					s.append(": ");
					s.append(room.getHistories()[i].getVisitor().getLastName());
					s.append(", cheched-in ");
					s.append(room.getHistories()[i].getCheckInDate());
					s.append(", checked-out ");
					s.append(room.getHistories()[i].getCheckOutDate());

					Printer.print(s.toString());
				}
			}
		}
	}

	public void changePriceOnRoom(Room room, double dailyPrice) {
		room.setDailyPrice(dailyPrice);
	}

	public void receiveVisitor(Visitor visitor) {
		visitorManager.addVisitor(visitor);
	}

	public void printVisitors() {
		Printer.printArray(visitorManager.getVisitors());
	}

	public void deleteVisitor(Visitor visitor) {
		visitorManager.deleteVisitor(visitor);
	}

	public void sortVisitorsByName() {
		Visitor [] visitors = visitorManager.sortVisitors(new SortingVisitorsByName());
		Printer.printArray(visitors);
	}

	public void addServicesToVisitor(Visitor visitor, Service service) {
		visitorManager.addServicesToVisitor(visitor, service);
	}

	public void printVisitorServices(Visitor visitor) {
		for (int i = 0; i < visitorManager.getVisitorServices(visitor).length; i++) {
			if (visitorManager.getVisitorServices(visitor)[i] != null) {
				StringBuilder s = new StringBuilder();
				s.append("Visitor ");
				s.append(visitor.getLastName());
				s.append(" has used ");
				s.append(visitorManager.getVisitorServices(visitor)[i]);
				Printer.print(s.toString());
			}
		}
	}

	public void sortVisitorServicesByPrice(Visitor visitor) {
		Service[] visitorServices = visitorManager.sortVisitorServicesByPrice(visitor, new SortingServicesByPrice());
		Printer.printArray(visitorServices);
	}

	public void printTotalVisitorsOnDate(LocalDate date) {

		Printer.print(visitorManager.getTotalVisitorsOnDate(date));
	}

	public void createService(Service service) {
		serviceManager.addService(service);
		;
	}

	public void printServices() {
		Printer.printArray(serviceManager.getServices());
	}

	public void sortServicesByName() {
		Service [] services = serviceManager.sortServices(new SortingServicesByName());
		Printer.printArray(services);
	}

	public void sortServicesByPrice() {
		Service [] services = serviceManager.sortServices(new SortingServicesByPrice());
		Printer.printArray(services);
	}

	public void changePriceOnService(Service service, double dailyPrice) {
		service.setDailyPrice(dailyPrice);
	}

	public void checkInVisitorInRoom(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate) {

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
			s.append(", was checked-in. Room #");
			s.append(room.getRoomNumber());
			Printer.print(s.toString());

		} else if (room.getStatus() == RoomStatus.SERVICED) {
			StringBuilder s = new StringBuilder();
			s.append("Room #");
			s.append(room.getRoomNumber());
			s.append(" is being serviced now.");
			Printer.print(s.toString());
		} else if (room.getStatus() == RoomStatus.OCCUPIED) {
			StringBuilder s = new StringBuilder();
			s.append("Room #");
			s.append(room.getRoomNumber());
			s.append(" is occupied now.");
			Printer.print(s.toString());
		}
	}

	@SuppressWarnings("unused")
	public void checkOutVisitorFromRoom(Visitor visitor, Room room) {

		if (room.getStatus() != RoomStatus.OCCUPIED) {
			StringBuilder s = new StringBuilder();
			s.append("The Room#");
			s.append(room.getRoomNumber());
			s.append(" has no Visitors.");
			Printer.print(s.toString());

		} else {
			for (int i = 0; i < room.getHistories().length; i++) {
				if (room.getHistories()[i] != null && room.getHistories()[i].getVisitor() == visitor
						&& room.getHistories()[i].getStatus() == RoomHistoryStatus.CHECKIN) {

					StringBuilder s = new StringBuilder();
					s.append(visitor.getLastName());
					s.append(" has checked-out from Room #");
					s.append(room.getRoomNumber());
					Printer.print(s.toString());

					room.getHistories()[i].setStatus(RoomHistoryStatus.CHECKOUT);
					visitor.setHistory(null);
					room.setStatus(RoomStatus.EMPTY);

					break;
				}
			}
		}
	}

	public void saveToFile() {
		roomManager.saveToFile();
		serviceManager.saveToFile();
		visitorManager.saveToFile();
	}

	public void loadFromFile() {
		Printer.printStringArray(visitorManager.loadFromFile());
		Printer.printStringArray(roomManager.loadFromFile());
		Printer.printStringArray(serviceManager.loadFromFile());
	}
}
