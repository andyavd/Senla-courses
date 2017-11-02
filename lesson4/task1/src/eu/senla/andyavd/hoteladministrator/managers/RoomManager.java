package eu.senla.andyavd.hoteladministrator.managers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.api.IRoomManager;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.Printer;

public class RoomManager implements IRoomManager {

	public RoomsStorage rs = new RoomsStorage();
	ArrayWorker aw = new ArrayWorker();

	@Override
	public void addRoom(Room room) {
		rs.addRoom(room);
	}

	@Override
	public Room[] showRooms() {
		return rs.getRooms();
	}

	@Override
	public void updateRoom(Room room, RoomHistory history) {
		rs.updateRoom(room, history);
	}

	@Override
	public Room[] showEmptyRooms() {
		return aw.getNotNullEmptyRooms(rs.getRooms());
	}

	@Override
	public Integer showEmptyRoomsNumber() {
		return aw.getNotNullEmptyRooms(rs.getRooms()).length;
	}

	@Override
	public Room showRoomDetails(Room room) {

		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] == room) {
				room = rs.getRooms()[i];
			}
		}
		return room;
	}

	@Override
	public void showRoomsEmptyOnADate(LocalDate date) {

		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] != null && rs.getRooms()[i].getStatus() == RoomStatus.OCCUPIED) {

				int r = 0;
				for (int k = 0; k < rs.getRooms()[i].getHistories().length; k++) {

					if (rs.getRooms()[i].getHistories()[k] != null
							&& (date.isBefore(rs.getRooms()[i].getHistories()[k].getCheckInDate())
									|| date.isAfter(rs.getRooms()[i].getHistories()[k].getCheckOutDate()))) {
						r = 1;
						continue;
					}

					if (r == 1) {
						Printer.print(rs.getRooms()[i].toString());
						break;
					}
				}
			}
		}

		for (int i = 0; i < rs.getRooms().length; i++) {
			if (rs.getRooms()[i] != null && rs.getRooms()[i].getStatus() == RoomStatus.EMPTY) {
				Printer.print(rs.getRooms()[i].toString());
			}
		}
	}

	/* ======================sorting============================ */

	@Override
	public void sortEmptyRooms(Comparator comparator) {
		Arrays.sort(aw.getNotNullEmptyRooms(rs.getRooms()), comparator);
	}

	@Override
	public void sortRooms(Comparator comparator) {
		Arrays.sort(rs.getRooms(), comparator);
	}

	public void save() {
		rs.save();
	}

	public void load() {
		rs.load();
	}
}
