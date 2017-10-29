package eu.senla.andyavd.hoteladministrator.managers;

import java.time.LocalDate;
import java.time.Period;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.utils.Printer;

public class HotelManager {

	RoomManager rm = new RoomManager();
	VisitorManager vm = new VisitorManager();
	ServiceManager sm = new ServiceManager();
	RoomHistoryManager rhm = new RoomHistoryManager();

	/* ========================Rooms=========================== */

	public void createRoom(Room room) {
		rm.addRoom(room);
	}

	public void printRooms() {
		rm.showRooms();
	}

	public void printEmptyRooms() {
		rm.showEmptyRooms();
	}

	public void printEmptyRoomsNumber() {
		Printer.print(rm.showEmptyRoomsNumber().toString());
	}

	public void printDetailsOfRoomNumber(int roomNumber) {

		StringBuilder s = new StringBuilder();
		s.append("Room details: ");
		s.append(rm.showRoomDetails(roomNumber));

		Printer.print(s.toString());
	}

	public void sortRoomsBy(String parameter) {
		String p = parameter;
		switch (p) {
		case "price":
			rm.sortRoomsByPrice();
			break;
		case "capacity":
			rm.sortRoomsByCapacity();
			break;
		case "stars":
			rm.sortRoomsByStars();
			break;
		default:
			Printer.print("Please, choose price, capacity or stars as a parameter.");
			break;
		}
	}

	public void sortEmptyRoomsBy(String parameter) {
		String p = parameter;
		switch (p) {
		case "price":
			rm.sortEmptyRoomsByPrice();
			break;
		case "capacity":
			rm.sortEmptyRoomsByCapacity();
			break;
		case "stars":
			rm.sortEmptyRoomsByStars();
			break;
		default:
			Printer.print("Please, choose price, capacity or stars as a parameter EMPTY.");
			break;
		}
	}

	public void billVisitor(Visitor visitor) {

		if (visitor.getRoom() != null) {

			Period period = Period.between(visitor.getCheckInDate(), visitor.getCheckOutDate());
			int days = period.getDays();
			double payment = visitor.getRoom().getDailyPrice() * days;

			StringBuilder s = new StringBuilder();
			s.append("Visitor ");
			s.append(visitor.getLastName());
			s.append(" needs to pay ");
			s.append(payment);
			s.append(" USD for the room #");
			s.append(visitor.getRoom().getRoomNumber());
			Printer.print(s.toString());
		} else {
			StringBuilder s = new StringBuilder();
			s.append("Visitor ");
			s.append(visitor.getLastName());
			s.append(" isn't checked-in any Room.");
			Printer.print(s.toString());
		}
	}

	public void showRoomsEmptyOnAParticularDate(String date) {
		rm.showRoomsEmptyOnAParticularDate(date);
	}

	public void repairRoom(Room room) {
		if (room.getStatus() == RoomStatus.EMPTY) {
			room.setStatus(RoomStatus.SERVICED);
			Printer.print("Room is closed for services now. Sorry.");
		} else if (room.getStatus() == RoomStatus.OCCUPIED) {
			Printer.print("Room is occupied. Please wait for the Visitor to check-out.");
		} else {
			Printer.print("Room is already being serviced.");
		}
	}

	public void getLastVisitorsOfARoom(Room room) {
		int newSize = 0;
		for (int i = 0; i < room.getHistories().length; i++) {
			if ((room.getHistories()[i] != null) && (room.getHistories()[i].getStatus() == RoomHistoryStatus.CHECKIN)) {
				newSize++;
			}
		}
		RoomHistory[] checkInHistories = new RoomHistory[newSize];
		int newIndex = 0;
		for (int i = 0; i < room.getHistories().length; i++) {
			if ((room.getHistories()[i] != null) && (room.getHistories()[i].getStatus() == RoomHistoryStatus.CHECKIN)) {
				checkInHistories[newIndex] = room.getHistories()[i];
				newIndex++;
			}
		}
		if (checkInHistories.length <= 3) {
			for (int i = 0; i < checkInHistories.length; i++) {

				StringBuilder s = new StringBuilder();
				s.append("Visitors of a Room #");
				s.append(checkInHistories[i].getRoom().getRoomNumber());
				s.append(": ");
				s.append(checkInHistories[i].getVisitor().getLastName());
				s.append(", cheched-in ");
				s.append(checkInHistories[i].getCheckInDate());
				s.append(", checked-out ");
				s.append(checkInHistories[i].getCheckOutDate());

				Printer.print(s.toString());
			}
		} else {
			StringBuilder s3 = new StringBuilder();
			s3.append("Visitors of a Room #");
			s3.append(checkInHistories[checkInHistories.length-1].getRoom().getRoomNumber());
			s3.append(": ");
			s3.append(checkInHistories[checkInHistories.length-1].getVisitor().getLastName());
			s3.append(", cheched-in ");
			s3.append(checkInHistories[checkInHistories.length-1].getCheckInDate());
			s3.append(", checked-out ");
			s3.append(checkInHistories[checkInHistories.length-1].getCheckOutDate());
			Printer.print(s3.toString());
			
			StringBuilder s2 = new StringBuilder();
			s2.append("Visitors of a Room #");
			s2.append(checkInHistories[checkInHistories.length-2].getRoom().getRoomNumber());
			s2.append(": ");
			s2.append(checkInHistories[checkInHistories.length-2].getVisitor().getLastName());
			s2.append(", cheched-in ");
			s2.append(checkInHistories[checkInHistories.length-2].getCheckInDate());
			s2.append(", checked-out ");
			s2.append(checkInHistories[checkInHistories.length-2].getCheckOutDate());
			Printer.print(s3.toString());
			
			StringBuilder s1 = new StringBuilder();
			s1.append("Visitors of a Room #");
			s1.append(checkInHistories[checkInHistories.length-3].getRoom().getRoomNumber());
			s1.append(": ");
			s1.append(checkInHistories[checkInHistories.length-3].getVisitor().getLastName());
			s1.append(", cheched-in ");
			s1.append(checkInHistories[checkInHistories.length-3].getCheckInDate());
			s1.append(", checked-out ");
			s1.append(checkInHistories[checkInHistories.length-3].getCheckOutDate());
			Printer.print(s3.toString());
			
			
		}
	}

	public void changePriceOnRoom(Room room, double dailyPrice) {
		room.setDailyPrice(dailyPrice);
	}


	/* ========================Visitors======================== */

	public void receiveVisitor(Visitor visitor) {
		vm.addVisitor(visitor);
	}

	public void printVisitors() {
		for (int i = 0; i < vm.showVisitors().length; i++) {
			Printer.print(vm.showVisitors()[i].toString());
		}
		// vm.showVisitors();
	}

	public void deleteVisitor(Visitor visitor) {
		vm.deleteVisitor(visitor);
	}

	public void showAndSortVisitorsBy(String parameter) {
		String p = parameter;
		switch (p) {
		case "lastName":
			vm.sortVisitorsByName();
			break;
		case "checkOutDate":
			vm.sortVisitorsByCheckOutDate();
			break;
		default:
			Printer.print("Please, choose lastName or checkOutDate as a parameter.");
			break;
		}
	}

	public void addServicesToVisitor(Visitor visitor, Service service) {
		vm.addServicesToVisitor(visitor, service);
	}

	public void printVisitorServices(Visitor visitor) {
		for (int i = 0; i < vm.showVisitorServices(visitor).length; i++) {
			StringBuilder s = new StringBuilder();
			s.append("Visitor ");
			s.append(visitor.getLastName());
			s.append(" has used ");
			s.append(vm.showVisitorServices(visitor)[i]);
			Printer.print(s.toString());
		}
	}

	public void sortVisitorServicesBy(Visitor visitor, String parameter) {
		String p = parameter;
		switch (p) {
		case "name":
			vm.sortVisitorServicesByName(visitor);
			break;
		case "price":
			vm.sortVisitorServicesByPrice(visitor);
			break;
		default:
			Printer.print("Please, choose price or name as a parameter.");
			break;
		}
	}

	// public void sortVisitorServicesByName(Visitor visitor) {
	// vm.sortVisitorServicesByName(visitor);
	// }
	// public void sortVisitorServicesByPrice(Visitor visitor) {
	// vm.sortVisitorServicesByPrice(visitor);
	// }

	/* ========================Services======================== */

	public void createService(Service service) {
		sm.addService(service);
		;
	}

	public void printServices() {
		sm.showServices();
	}

	public void sortServicesBy(String parameter) {
		String p = parameter;
		switch (p) {
		case "price":
			sm.sortServicesByPrice();
			break;
		case "name":
			sm.sortServicesByName();
			break;
		default:
			Printer.print("Please, choose price or name as a parameter.");
			break;
		}
	}

	public void changePriceOnService(Service service, double dailyPrice) {
		service.setDailyPrice(dailyPrice);
	}
	/* ========================Process========================= */

	public void checkInVisitorInARoom(Visitor visitor, Room room, String checkInDate,
			String checkOutDate) { /* yyyy-mm-dd */

		if (room.getStatus() == RoomStatus.EMPTY) {
			RoomHistory newHistory = new RoomHistory();

			newHistory.setVisitor(visitor);
			newHistory.setRoom(room);

			LocalDate inDate = LocalDate.parse(checkInDate);
			LocalDate outDate = LocalDate.parse(checkOutDate);

			newHistory.setCheckInDate(inDate);
			newHistory.setCheckOutDate(outDate);
			newHistory.setStatus(RoomHistoryStatus.CHECKIN);

			rhm.addHistory(newHistory);

			rm.updateRoom(room, newHistory);
			room.setStatus(RoomStatus.OCCUPIED);
			/* delete */ room.setCheckInDate(inDate);
			/* delete */ room.setCheckOutDate(outDate);
			vm.updateVisitor(visitor, newHistory);
			visitor.setLastHistoryWithCheckInStatusId(newHistory.getId());
			/* delete */ visitor.setRoom(room);
			/* delete */ visitor.setCheckInDate(inDate);
			/* delete */ visitor.setCheckOutDate(outDate);

			StringBuilder s = new StringBuilder();
			s.append("Mr ");
			s.append(visitor.getLastName());
			s.append(", You have been successfully checked-in. Room #");
			s.append(room.getRoomNumber());
			s.append(".");
			Printer.print(s.toString());

		} else if (room.getStatus() == RoomStatus.SERVICED) {
			StringBuilder s = new StringBuilder();
			s.append("Soory, Mr ");
			s.append(visitor.getLastName());
			s.append(", Room #");
			s.append(room.getRoomNumber());
			s.append(" is being serviced now. Please, choose another room.");
			Printer.print(s.toString());
		} else if (room.getStatus() == RoomStatus.OCCUPIED) {
			StringBuilder s = new StringBuilder();
			s.append("Soory, Mr ");
			s.append(visitor.getLastName());
			s.append(", Room #");
			s.append(room.getRoomNumber());
			s.append(" is occupied now. Please, choose another room.");
			Printer.print(s.toString());
		}
	}

	@SuppressWarnings("unused")
	public void checkOutVisitorFromARoom(Visitor visitor, Room room) {

		if (room.getStatus() != RoomStatus.OCCUPIED) {
			StringBuilder s = new StringBuilder();
			s.append("The Room#");
			s.append(room.getRoomNumber());
			s.append(" has no Visitors.");
			Printer.print(s.toString());

		} else {
			for (int i = 0; i < room.getHistories().length; i++) {
				if ((room.getHistories()[i] != null) && (room.getHistories()[i + 1] == null)
						&& (room.getHistories()[i].getVisitor() == visitor)) {

					RoomHistory newHistory = new RoomHistory();

					newHistory.setVisitor(visitor);
					newHistory.setRoom(room);
					newHistory.setStatus(RoomHistoryStatus.CHECKOUT);

					rhm.addHistory(newHistory);

					rm.updateRoom(room, newHistory);
					room.setStatus(RoomStatus.EMPTY);
					vm.updateVisitor(visitor, newHistory);

					StringBuilder s = new StringBuilder();
					s.append("Mr ");
					s.append(visitor.getLastName());
					s.append(", You have been successfully checked-out. Room #");
					s.append(room.getRoomNumber());
					s.append(". See You next time!");
					Printer.print(s.toString());
					break;

				} else {
					StringBuilder s = new StringBuilder();
					s.append("The Room#");
					s.append(room.getRoomNumber());
					s.append(" has no visitor ");
					s.append(visitor.getLastName());
					Printer.print(s.toString());
					break;
				}
			}
		}
	}

	public void getTotalVisitorsOnDate(String date) {
		Integer count = 0;

		LocalDate currentDate = LocalDate.parse(date);

		for (int i = 0; i < vm.showVisitors().length; i++) {
			if (vm.showVisitors()[i].getCheckOutDate() != null) {
				if ((vm.showVisitors()[i].getCheckInDate().isBefore(currentDate)
						|| vm.showVisitors()[i].getCheckInDate().isEqual(currentDate))
						&& (vm.showVisitors()[i].getCheckOutDate().isAfter(currentDate)
								|| vm.showVisitors()[i].getCheckOutDate().isEqual(currentDate))) {
					count++;
				}
			}
		}
		StringBuilder s = new StringBuilder();
		s.append("There are ");
		s.append(count);
		s.append(" visitors on ");
		s.append(date);

		Printer.print(s.toString());
	}

}
