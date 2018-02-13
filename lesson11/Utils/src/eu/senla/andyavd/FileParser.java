package eu.senla.andyavd;

import java.util.ArrayList;
import java.util.List;

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
			s.append(String.valueOf(history.getRoom().getId()));
			s.append(SEPARATOR);
			s.append(String.valueOf(history.getVisitor().getId()));
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
			if (newVisitor.getHistory() == null) {
				s.append(" ");
				s.append(SEPARATOR);
				s.append(" ");
				s.append(SEPARATOR);
				s.append(" ");
				s.append(SEPARATOR);
			} else {

				s.append(String.valueOf(newVisitor.getHistory().getRoom().getId()));
				s.append(SEPARATOR);
				s.append(String.valueOf(newVisitor.getHistory().getCheckInDate()));
				s.append(SEPARATOR);
				s.append(String.valueOf(newVisitor.getHistory().getCheckOutDate()));
				s.append(SEPARATOR);
			}
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
			if (newRoom.getHistories() == null) {
				s.append(" ");
			} else {
				List<RoomHistory> roomHistories = newRoom.getHistories();
				for (int k = 0; k < roomHistories.size(); k++) {
					s.append(roomHistories.get(roomHistories.size() - 1).getId());
				}
			}
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
