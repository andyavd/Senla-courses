package eu.senla.andyavd.hoteladministrator.utils;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class FileReaderParser {

	public static List<Room> loadedRoomsToRooms(String[] array) {

		List<Room> loadedRooms = new ArrayList<Room>();

		for (int i = 0; i < array.length; i++) {

			String[] parsedString = array[i].split(" ");

			Room newRoom = new Room();

			((Room) newRoom).setId(Integer.parseInt(parsedString[0]));
			((Room) newRoom).setRoomNumber(Integer.parseInt(parsedString[1]));
			((Room) newRoom).setCapasity(Integer.parseInt(parsedString[2]));
			((Room) newRoom).setDailyPrice(Double.parseDouble(parsedString[3]));

			if (parsedString[4].equals("STANDARD")) {
				((Room) newRoom).setStars(RoomStars.STANDARD);
			} else if (parsedString[4].equals("JUNIOR_SUITE")) {
				((Room) newRoom).setStars(RoomStars.JUNIOR_SUITE);
			} else if (parsedString[4].equals("LUX")) {
				((Room) newRoom).setStars(RoomStars.LUX);
			} else if (parsedString[4].equals("PRESIDENT_LUX")) {
				((Room) newRoom).setStars(RoomStars.PRESIDENT_LUX);
			}
			if (parsedString[5].equals("EMPTY")) {
				((Room) newRoom).setStatus(RoomStatus.EMPTY);
			} else if (parsedString[5].equals("OCCUPIED")) {
				((Room) newRoom).setStatus(RoomStatus.OCCUPIED);
			} else if (parsedString[5].equals("SERVICED")) {
				((Room) newRoom).setStatus(RoomStatus.SERVICED);
			}

			loadedRooms.add(newRoom);
		}
		return loadedRooms;
	}

	public static List<Service> loadedServicesToServices(String[] array) {

		List<Service> loadedServices = new ArrayList<Service>();

		for (int i = 0; i < array.length; i++) {

			String[] parsedString = array[i].split(" ");

			Service newService = new Service();

			((Service) newService).setId(Integer.parseInt(parsedString[0]));
			((Service) newService).setName(parsedString[1]);
			((Service) newService).setDailyPrice(Double.parseDouble(parsedString[2]));

			loadedServices.add(newService);
		}
		return loadedServices;
	}

	public static List<Visitor> loadedVisitorsToVisitors(String[] array) {

		List<Visitor> loadedVisitors = new ArrayList<Visitor>();

		for (int i = 0; i < array.length; i++) {

			String[] parsedString = array[i].split(" ");

			Visitor newVisitor = new Visitor();

			((Visitor) newVisitor).setId(Integer.parseInt(parsedString[0]));
			((Visitor) newVisitor).setLastName(parsedString[1]);

			loadedVisitors.add(newVisitor);
		}
		return loadedVisitors;
	}
}
