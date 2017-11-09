package eu.senla.andyavd.hoteladministrator.view;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.controller.RoomHistoryManager;
import eu.senla.andyavd.hoteladministrator.controller.RoomManager;
import eu.senla.andyavd.hoteladministrator.controller.ServiceManager;
import eu.senla.andyavd.hoteladministrator.controller.VisitorManager;
import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Room;
import eu.senla.andyavd.hoteladministrator.model.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;
import eu.senla.andyavd.hoteladministrator.model.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.NotEmptyRoomException;
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

	
	private static HotelManager hm;
	public static HotelManager getInstance() {
        if (hm == null) {
            hm = new HotelManager();
        }
        return hm;
    }
	
	/* ========================Rooms=========================== */

	public void addRoom(Room room) {
		rm.addRoom(room);
	}

	public List<Entity> printRooms() {
		return rm.getRooms();
	}

	public List<Entity> printEmptyRooms() {
		return rm.showEmptyRooms();
	}

	public Integer printEmptyRoomsNumber() {
		return rm.showEmptyRoomsNumber();
	}

	public void printDetailsOfRoom(Room room) {

		StringBuilder s = new StringBuilder();
		s.append("Room details: ");
		s.append(rm.getRoomDetails(room));

		Printer.print(s.toString());
	}

	public void sortRoomsByCapacity() {
		rm.sortRooms(new SortingRoomsByCapacity());
		Printer.printList(rm.getRooms());
	}

	public void sortRoomsByPrice() {
		rm.sortRooms(new SortingRoomsByPrice());
		Printer.printList(rm.getRooms());
	}

	public void sortRoomsByStars() {
		rm.sortRooms(new SortingRoomsByStars());
		Printer.printList(rm.getRooms());
	}

	public void sortEmptyRoomsByCapacity() {
		rm.sortEmptyRooms(new SortingRoomsByCapacity());
		Printer.printList(rm.showEmptyRooms());
	}

	public void sortEmptyRoomsByPrice() {
		rm.sortEmptyRooms(new SortingRoomsByPrice());
		Printer.printList(rm.showEmptyRooms());
	}

	public void sortEmptyRoomsByStars() {
		rm.sortEmptyRooms(new SortingRoomsByStars());
		Printer.printList(rm.showEmptyRooms());
	}

	public void billVisitor(Visitor visitor) throws NullPointerException {

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
			throw new NullPointerException("No such visitor to bill!");
		}
	}
	
	public List<Entity> showEmptyRoomsOnDate(LocalDate date) {

		List<Entity> emptyRoomsOnDate = new ArrayList<Entity>();

		for (int i = 0; i < rm.getRooms().size(); i++) {
			if (rm.getRooms().get(i) != null && ((Room) rm.getRooms().get(i)).getStatus() == RoomStatus.OCCUPIED) {

				int r = 0;
				for (int k = 0; k < ((Room) rm.getRooms().get(i)).getHistories().size(); k++) {

					if (((Room) rm.getRooms().get(i)).getHistories().get(k) != null && (date
							.isBefore(((Room) rm.getRooms().get(i)).getHistories().get(k).getCheckInDate())
							|| date.isAfter(((Room) rm.getRooms().get(i)).getHistories().get(k).getCheckOutDate()))) {
						r = 1;
						continue;
					}

					if (r == 1) {
						emptyRoomsOnDate.add((rm.getRooms().get(i)));
						break;
					}
				}
			}
		}

		for (int i = 0; i < rm.getRooms().size(); i++) {
			if (rm.getRooms().get(i) != null && ((Room) rm.getRooms().get(i)).getStatus() == RoomStatus.EMPTY) {
				emptyRoomsOnDate.add((rm.getRooms().get(i)));
			}
		}
		return emptyRoomsOnDate;
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

	public List<String> getLastVisitorsOfRoom(Room room) {

		List<String> lastVisitorsOfRoom = new ArrayList<String>();

		for (int i = 0; i < room.getHistories().size(); i++) {
			if (room.getHistories().get(i) != null && room.getHistories().size() <= 3) {
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

			} else if (room.getHistories().size() > 3) {

				StringBuilder s1 = new StringBuilder();
				s1.append("Visitors of a Room #");
				s1.append(room.getHistories().get(room.getHistories().size()-1).getRoom().getRoomNumber());
				s1.append(": ");
				s1.append(room.getHistories().get(room.getHistories().size()-1).getVisitor().getLastName());
				s1.append(", cheched-in ");
				s1.append(room.getHistories().get(room.getHistories().size()-1).getCheckInDate());
				s1.append(", checked-out ");
				s1.append(room.getHistories().get(room.getHistories().size()-1).getCheckOutDate());
				lastVisitorsOfRoom.add(s1.toString());

				StringBuilder s2 = new StringBuilder();
				s2.append("Visitors of a Room #");
				s2.append(room.getHistories().get(room.getHistories().size()-2).getRoom().getRoomNumber());
				s2.append(": ");
				s2.append(room.getHistories().get(room.getHistories().size()-2).getVisitor().getLastName());
				s2.append(", cheched-in ");
				s2.append(room.getHistories().get(room.getHistories().size()-2).getCheckInDate());
				s2.append(", checked-out ");
				s2.append(room.getHistories().get(room.getHistories().size()-2).getCheckOutDate());
				lastVisitorsOfRoom.add(s2.toString());

				StringBuilder s3 = new StringBuilder();
				s3.append("Visitors of a Room #");
				s3.append(room.getHistories().get(room.getHistories().size() - 3).getRoom().getRoomNumber());
				s3.append(": ");
				s3.append(room.getHistories().get(room.getHistories().size() - 3).getVisitor().getLastName());
				s3.append(", cheched-in ");
				s3.append(room.getHistories().get(room.getHistories().size() - 3).getCheckInDate());
				s3.append(", checked-out ");
				s3.append(room.getHistories().get(room.getHistories().size() - 3).getCheckOutDate());
				lastVisitorsOfRoom.add(s3.toString());
				
				break;
			}
		}
		return lastVisitorsOfRoom;
	}

	public void changePriceOnRoom(Room room, double dailyPrice) {
		room.setDailyPrice(dailyPrice);
	}

	/* ========================Visitors======================== */

	public void receiveVisitor(Visitor visitor) {
		vm.addVisitor(visitor);
	}

	public void printVisitors() {
		Printer.printList(vm.getVisitors());
	}

	public void deleteVisitor(Visitor visitor) {
		vm.deleteVisitor(visitor);
	}

	public void sortVisitorsByName() {
		vm.sortVisitors(new SortingVisitorsByName());
		Printer.printList(vm.getVisitors());
	}

	public void addServicesToVisitor(Visitor visitor, Service service) throws NullPointerException {
		vm.addServicesToVisitor(visitor, service);
	}

	public List<String> printVisitorServices(Visitor visitor) {

		List<String> visitorServices = new ArrayList<String>();

		for (int i = 0; i < vm.showVisitorServices(visitor).size(); i++) {
			if (vm.showVisitorServices(visitor).get(i) != null) {
				StringBuilder s = new StringBuilder();
				s.append("Visitor ");
				s.append(visitor.getLastName());
				s.append(" has used ");
				s.append(vm.showVisitorServices(visitor).get(i));

				visitorServices.add(s.toString());
			}
		}
		return visitorServices;
	}

	public void sortVisitorServicesByPrice(Visitor visitor) {
		vm.sortVisitorServicesByPrice(visitor, new SortingServicesByPrice());
		Printer.print("Sorted services for Visitor");
		Printer.printList(vm.showVisitorServices(visitor));
	}

	public String getTotalVisitorsOnDate(LocalDate date) {

		Integer count = 0;
		for (int i = 0; i < vm.getVisitors().size(); i++) {
			if (vm.getVisitors().get(i) != null && ((Visitor) vm.getVisitors().get(i)).getHistory() != null) {
				if ((((Visitor) vm.getVisitors().get(i)).getHistory().getCheckInDate().isBefore(date)
						|| ((Visitor) vm.getVisitors().get(i)).getHistory().getCheckInDate().isEqual(date))
						&& (((Visitor) vm.getVisitors().get(i)).getHistory().getCheckOutDate().isAfter(date)
								|| ((Visitor) vm.getVisitors().get(i)).getHistory().getCheckOutDate().isEqual(date))) {
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

	/* ========================Services======================== */

	public void createService(Service service) {
		sm.addService(service);
		;
	}

	public void printServices() {
		Printer.printList(sm.getServices());
	}

	public void sortServicesByName() {
		sm.sortServices(new SortingServicesByName());
		Printer.printList(sm.getServices());
	}

	public void sortServicesByPrice() {
		sm.sortServices(new SortingServicesByPrice());
		Printer.printList(sm.getServices());
	}

	public void changePriceOnService(Service service, double dailyPrice) {
		service.setDailyPrice(dailyPrice);
	}

	/* ========================Process========================= */

	public void checkInVisitorInRoom(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate) throws NotEmptyRoomException {

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
			s.append(" was checked-in. Room #");
			s.append(room.getRoomNumber());
			Printer.print(s.toString());

		} else{
			throw new NotEmptyRoomException("Room is not empty!");
		}
	}
	
	@SuppressWarnings("unused")
	public void checkOutVisitorFromRoom(Visitor visitor, Room room) throws EmptyRoomException {

		if (room.getStatus() == RoomStatus.OCCUPIED) {
			for (int i = 0; i < room.getHistories().size(); i++) {
				if (room.getHistories().get(i) != null && room.getHistories().get(i).getVisitor() == visitor
						&& room.getHistories().get(i).getStatus() == RoomHistoryStatus.CHECKIN) {

					StringBuilder s = new StringBuilder();
					s.append(visitor.getLastName());
					s.append(" has checked-out from Room #");
					s.append(room.getRoomNumber());
					Printer.print(s.toString());

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

	 public void saveToFile() {
	 rm.saveToFile();
	 sm.saveToFile();
	 vm.saveToFile();
	 }

	 public void loadFromFile() {
	 Printer.printStringArray(vm.loadFromFile());
	 Printer.printStringArray(rm.loadFromFile());
	 Printer.printStringArray(sm.loadFromFile());
	 }
}