package eu.senla.andyavd.utils.common;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.beans.RoomHistory;
import eu.senla.andyavd.beans.Service;
import eu.senla.andyavd.beans.Visitor;

public class FileParser {

	private static final String SEPARATOR = ",";

	public static String[] servicesToString(List<Service> serviceList) {
		String[] serviceStrings = new String[serviceList.size()];
		for (int i = 0; i < serviceList.size(); i++) {

			Service newService = serviceList.get(i);

			StringBuilder s = new StringBuilder();
			s.append(String.valueOf(newService.getId()));
			s.append(SEPARATOR);
			s.append(newService.getName());
			s.append(SEPARATOR);
			s.append(String.valueOf(newService.getDailyPrice()));
			s.append(SEPARATOR);

			serviceStrings[i] = s.toString();
		}
		return serviceStrings;
	}

	public static String[] roomHistoriesToString(List<RoomHistory> historyList) {
		String[] historyStrings = new String[historyList.size()];

		for (int i = 0; i < historyList.size(); i++) {

			RoomHistory history = historyList.get(i);

			StringBuilder s = new StringBuilder();
			s.append(String.valueOf(history.getId()));
			s.append(SEPARATOR);
			s.append(String.valueOf(history.getRoomId()));
			s.append(SEPARATOR);
			s.append(String.valueOf(history.getVisitorId()));
			s.append(SEPARATOR);
			s.append(String.valueOf(history.getCheckInDate()));
			s.append(SEPARATOR);
			s.append(String.valueOf(history.getCheckOutDate()));
			s.append(SEPARATOR);
			s.append(String.valueOf(history.getStatus()));
			s.append(SEPARATOR);

			historyStrings[i] = s.toString();
		}
		return historyStrings;
	}

	public static String[] visitorsToString(List<Visitor> visitorList) {
		String[] serviceStrings = new String[visitorList.size()];
		for (int i = 0; i < visitorList.size(); i++) {

			Visitor newVisitor = visitorList.get(i);

			StringBuilder s = new StringBuilder();
			s.append(String.valueOf(newVisitor.getId()));
			s.append(SEPARATOR);
			s.append(newVisitor.getLastName());
			s.append(SEPARATOR);
			serviceStrings[i] = s.toString();
		}
		return serviceStrings;
	}

	public static String[] roomsToString(List<Room> roomList) {

		String[] roomStrings = new String[roomList.size()];
		for (int i = 0; i < roomList.size(); i++) {

			Room newRoom = roomList.get(i);

			StringBuilder s = new StringBuilder();
			s.append(String.valueOf(newRoom.getId()));
			s.append(SEPARATOR);
			s.append(String.valueOf(newRoom.getRoomNumber()));
			s.append(SEPARATOR);
			s.append(String.valueOf(newRoom.getCapasity()));
			s.append(SEPARATOR);
			s.append(String.valueOf(newRoom.getDailyPrice()));
			s.append(SEPARATOR);
			s.append(String.valueOf(newRoom.getStars()));
			s.append(SEPARATOR);
			s.append(String.valueOf(newRoom.getStatus()));
			s.append(SEPARATOR);
			roomStrings[i] = s.toString();
		}
		return roomStrings;
	}

	public static List<Room> stringToRooms(String[] array) {

		List<Room> loadedRooms = new ArrayList<Room>();

		for (int i = 1; i < array.length; i++) {

			String[] parsedString = array[i].split(",");

			Room newRoom = new Room();

			((Room) newRoom).setId(Integer.parseInt(parsedString[0]));
			((Room) newRoom).setRoomNumber(Integer.parseInt(parsedString[1]));
			((Room) newRoom).setCapasity(Integer.parseInt(parsedString[2]));
			((Room) newRoom).setDailyPrice(Double.parseDouble(parsedString[3]));
			((Room) newRoom).setStars(parsedString[4]);
			((Room) newRoom).setStatus(parsedString[5]);

			loadedRooms.add(newRoom);
		}
		return loadedRooms;
	}

	public static List<Service> stringToServices(String[] array) {

		List<Service> loadedServices = new ArrayList<Service>();

		for (int i = 1; i < array.length; i++) {

			String[] parsedString = array[i].split(",");

			Service newService = new Service();

			((Service) newService).setId(Integer.parseInt(parsedString[0]));
			((Service) newService).setName(parsedString[1]);
			((Service) newService).setDailyPrice(Double.parseDouble(parsedString[2]));

			loadedServices.add(newService);
		}
		return loadedServices;
	}

	public static List<Visitor> stringToVisitors(String[] array) {

		List<Visitor> loadedVisitors = new ArrayList<Visitor>();

		for (int i = 1; i < array.length; i++) {

			String[] parsedString = array[i].split(",");

			Visitor newVisitor = new Visitor();

			((Visitor) newVisitor).setId(Integer.parseInt(parsedString[0]));
			((Visitor) newVisitor).setLastName(parsedString[1]);

			loadedVisitors.add(newVisitor);
		}
		return loadedVisitors;
	}
}
