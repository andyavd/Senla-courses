package eu.senla.andyavd.hoteladministrator.managers;

import java.time.LocalDate;
import java.util.Arrays;
import eu.senla.andyavd.hoteladministrator.actions.IRoomManager;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.sorters.roomsorters.SortingRoomsByCapacity;
import eu.senla.andyavd.hoteladministrator.utils.sorters.roomsorters.SortingRoomsByPrice;
import eu.senla.andyavd.hoteladministrator.utils.sorters.roomsorters.SortingRoomsByStars;

public class RoomManager implements IRoomManager {

	public RoomsStorage rs = new RoomsStorage();

	@Override
	public void addRoom(Room room) {
		rs.addRoom(room);
	}

	@Override
	public void showRooms() {
		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] != null) {
				Printer.print(rs.getRooms()[i].toString());
			}
		}
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		rs.updateRoom(room, history);
	}

	@Override
	public void showEmptyRooms() {

		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] != null) {
				if (rs.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
					Printer.print(rs.getRooms()[i].toString());
				}
			}
		}
	}

	@Override
	public Integer showEmptyRoomsNumber() {
		Integer emptyRoomsCount = 0;
		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] != null) {
				if (rs.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
					emptyRoomsCount++;
				}
			}
		}
		return emptyRoomsCount;
	}

	@Override
	public Room showRoomDetails(int roomNumber) {

		Room room = null;
		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i].getRoomNumber() == roomNumber) {
				room = rs.getRooms()[i];
			}
		}
		return room;
	}

	@Override
	public void showRoomsEmptyOnAParticularDate(String date) {

		LocalDate targetDate = LocalDate.parse(date);

		for (int i = 0; i < rs.getRooms().length; i++) {
			if ((rs.getRooms()[i].getStatus() != RoomStatus.SERVICED) && (rs.getRooms()[i] != null)) {

				if ((((rs.getRooms()[i].getCheckInDate() != null)
						&& (targetDate.isBefore(rs.getRooms()[i].getCheckInDate()))))
						|| (((rs.getRooms()[i].getCheckOutDate() != null)
								&& (targetDate.isAfter(rs.getRooms()[i].getCheckOutDate()))))
						|| (rs.getRooms()[i].getStatus() == RoomStatus.EMPTY)) {

					Printer.print(rs.getRooms()[i].toString());
				}
			}
		}
	}

	/* ======================sorting============================ */
	@Override
	public void sortEmptyRoomsByPrice() {
		Arrays.sort(rs.getRooms(), new SortingRoomsByPrice());
		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
				Printer.print(rs.getRooms()[i].toString());
			}
		}
	}

	@Override
	public void sortEmptyRoomsByCapacity() {
		Arrays.sort(rs.getRooms(), new SortingRoomsByCapacity());
		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
				Printer.print(rs.getRooms()[i].toString());
			}
		}
	}

	@Override
	public void sortEmptyRoomsByStars() {
		Arrays.sort(rs.getRooms(), new SortingRoomsByStars());
		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
				Printer.print(rs.getRooms()[i].toString());
			}
		}
	}

	@Override
	public void sortRoomsByPrice() {
		Arrays.sort(rs.getRooms(), new SortingRoomsByPrice());
		for (int i = 0; i < rs.getRooms().length; i++) {
			Printer.print(rs.getRooms()[i].toString());
		}
	}

	@Override
	public void sortRoomsByCapacity() {
		Arrays.sort(rs.getRooms(), new SortingRoomsByCapacity());
		for (int i = 0; i < rs.getRooms().length; i++) {
			Printer.print(rs.getRooms()[i].toString());
		}
	}

	@Override
	public void sortRoomsByStars() {
		Arrays.sort(rs.getRooms(), new SortingRoomsByStars());
		for (int i = 0; i < rs.getRooms().length; i++) {
			Printer.print(rs.getRooms()[i].toString());
		}
	}

}