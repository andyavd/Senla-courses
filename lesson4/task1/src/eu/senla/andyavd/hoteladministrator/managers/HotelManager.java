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
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByCapacity;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.rooms.SortingRoomsByStars;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByName;
import eu.senla.andyavd.hoteladministrator.utils.sorters.services.SortingServicesByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.visitors.SortingVisitorsByName;

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

		Printer.printArray(rm.showRooms());
		System.out.println(rm.showRooms().length);
	}

	public void printEmptyRooms() {
		Printer.printArray(rm.showEmptyRooms());
	}

	public void printEmptyRoomsNumber() {
		Printer.print(rm.showEmptyRoomsNumber().toString());
	}

	public void printDetailsOfRoom(Room room) {

		StringBuilder s = new StringBuilder();
		s.append("Room details: ");
		s.append(rm.showRoomDetails(room));

		Printer.print(s.toString());
	}

	public void sortRoomsByCapacity() {
		rm.sortRooms(new SortingRoomsByCapacity());
		Printer.printArray(rm.showRooms());
	}

	public void sortRoomsByPrice() {
		rm.sortRooms(new SortingRoomsByPrice());
		Printer.printArray(rm.showRooms());
	}

	public void sortRoomsByStars() {
		rm.sortRooms(new SortingRoomsByStars());
		Printer.printArray(rm.showRooms());
	}

	public void sortEmptyRoomsByCapacity() {
		rm.sortEmptyRooms(new SortingRoomsByCapacity());
		Printer.printArray(rm.showEmptyRooms());
	}

	public void sortEmptyRoomsByPrice() {
		rm.sortEmptyRooms(new SortingRoomsByPrice());
		Printer.printArray(rm.showEmptyRooms());
	}

	public void sortEmptyRoomsByStars() {
		rm.sortEmptyRooms(new SortingRoomsByStars());
		Printer.printArray(rm.showEmptyRooms());
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

	public void showEmptyRoomsOnADate(LocalDate date) {
		rm.showRoomsEmptyOnADate(date);
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

	public void getLastVisitorsOfARoom(Room room) {

		for (int i = 0; i < room.getHistories().length; i++) {
			if (room.getHistories()[i] != null && room.getHistories().length <= 3) {
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
			} else if (room.getHistories().length > 3) {
				if (room.getHistories()[i] != null && room.getHistories()[i + 1] == null) {
					StringBuilder s1 = new StringBuilder();
					s1.append("Visitors of a Room #");
					s1.append(room.getHistories()[i].getRoom().getRoomNumber());
					s1.append(": ");
					s1.append(room.getHistories()[i].getVisitor().getLastName());
					s1.append(", cheched-in ");
					s1.append(room.getHistories()[i].getCheckInDate());
					s1.append(", checked-out ");
					s1.append(room.getHistories()[i].getCheckOutDate());
					Printer.print(s1.toString());

					StringBuilder s2 = new StringBuilder();
					s2.append("Visitors of a Room #");
					s2.append(room.getHistories()[i - 1].getRoom().getRoomNumber());
					s2.append(": ");
					s2.append(room.getHistories()[i - 1].getVisitor().getLastName());
					s2.append(", cheched-in ");
					s2.append(room.getHistories()[i - 1].getCheckInDate());
					s2.append(", checked-out ");
					s2.append(room.getHistories()[i - 1].getCheckOutDate());
					Printer.print(s2.toString());

					StringBuilder s3 = new StringBuilder();
					s3.append("Visitors of a Room #");
					s3.append(room.getHistories()[i - 2].getRoom().getRoomNumber());
					s3.append(": ");
					s3.append(room.getHistories()[i - 2].getVisitor().getLastName());
					s3.append(", cheched-in ");
					s3.append(room.getHistories()[i - 2].getCheckInDate());
					s3.append(", checked-out ");
					s3.append(room.getHistories()[i - 2].getCheckOutDate());
					Printer.print(s3.toString());
				}
			}
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
		Printer.printArray(vm.showVisitors());
	}

	public void deleteVisitor(Visitor visitor) {
		vm.deleteVisitor(visitor);
	}

	public void sortVisitorsByName() {
		vm.sortVisitors(new SortingVisitorsByName());
		Printer.printArray(vm.showVisitors());
	}

	public void addServicesToVisitor(Visitor visitor, Service service) {
		vm.addServicesToVisitor(visitor, service);
	}

	public void printVisitorServices(Visitor visitor) {
		for (int i = 0; i < vm.showVisitorServices(visitor).length; i++) {
			if (vm.showVisitorServices(visitor)[i] != null) {
				StringBuilder s = new StringBuilder();
				s.append("Visitor ");
				s.append(visitor.getLastName());
				s.append(" has used ");
				s.append(vm.showVisitorServices(visitor)[i]);
				Printer.print(s.toString());
			}
		}
	}

	public void sortVisitorServicesByPrice(Visitor visitor) {
		vm.sortVisitorServicesByPrice(visitor, new SortingServicesByPrice());
		Printer.print("Sorted services for Visitor");
		Printer.printArray(vm.showVisitorServices(visitor));
	}

	public void getTotalVisitorsOnDate(LocalDate date) {

		Integer count = 0;
		for (int i = 0; i < vm.showVisitors().length; i++) {
			if (vm.showVisitors()[i] != null && vm.showVisitors()[i].getHistory() != null) {
				if ((vm.showVisitors()[i].getHistory().getCheckInDate().isBefore(date)
						|| vm.showVisitors()[i].getHistory().getCheckInDate().isEqual(date))
						&& (vm.showVisitors()[i].getHistory().getCheckOutDate().isAfter(date)
								|| vm.showVisitors()[i].getHistory().getCheckOutDate().isEqual(date))) {
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

	/* ========================Services======================== */

	public void createService(Service service) {
		sm.addService(service);
		;
	}

	public void printServices() {
		Printer.printArray(sm.getServices());
	}

	public void sortServicesByName() {
		sm.sortServices(new SortingServicesByName());
		Printer.printArray(sm.getServices());
	}

	public void sortServicesByPrice() {
		sm.sortServices(new SortingServicesByPrice());
		Printer.printArray(sm.getServices());
	}

	public void changePriceOnService(Service service, double dailyPrice) {
		service.setDailyPrice(dailyPrice);
	}

	/* ========================Process========================= */

	public void checkInVisitorInARoom(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate) {

		if (room.getStatus() == RoomStatus.EMPTY) {
			RoomHistory newHistory = new RoomHistory();

			newHistory.setVisitor(visitor);
			newHistory.setRoom(room);

			newHistory.setCheckInDate(checkInDate);
			newHistory.setCheckOutDate(checkOutDate);
			newHistory.setStatus(RoomHistoryStatus.CHECKIN);

			rhm.addHistory(newHistory);

			rm.updateRoom(room, newHistory);
			room.setStatus(RoomStatus.OCCUPIED);
			vm.updateVisitor(visitor, newHistory);

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
	public void checkOutVisitorFromARoom(Visitor visitor, Room room) {

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

	public void save() {
		rm.save();
		sm.save();
		vm.save();
	}

	public void load() {
		Printer.printStringArray(vm.load());
		Printer.printStringArray(rm.load());
		Printer.printStringArray(sm.load());
	}
}
