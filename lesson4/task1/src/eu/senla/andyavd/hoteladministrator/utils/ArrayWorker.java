package eu.senla.andyavd.hoteladministrator.utils;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class ArrayWorker {

	public static Room[] expandRoom(Room[] entities) {

		Room[] newEntity = new Room[entities.length * 2];
		System.arraycopy(entities, 0, newEntity, 0, entities.length);

		return newEntity;
	}

	public static Visitor[] expandVisitor(Visitor[] entities) {

		Visitor[] newEntity = new Visitor[entities.length * 2];
		System.arraycopy(entities, 0, newEntity, 0, entities.length);

		return newEntity;
	}

	public static Service[] expandService(Service[] entities) {

		Service[] newEntity = new Service[entities.length * 2];
		System.arraycopy(entities, 0, newEntity, 0, entities.length);

		return newEntity;
	}

	public static RoomHistory[] expandRoomHistory(RoomHistory[] entities) {

		RoomHistory[] newEntity = new RoomHistory[entities.length * 2];
		System.arraycopy(entities, 0, newEntity, 0, entities.length);

		return newEntity;
	}

	public Room[] getNotNullRooms(Room[] entities) {

		Integer newCount = 0;

		Room[] newEntity;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null)
				newCount++;
		}
		newEntity = new Room[newCount];
		System.arraycopy(entities, 0, newEntity, 0, newCount);

		return newEntity;
	}

	public Room[] getNotNullEmptyRooms(Room[] entities) {

		Integer newCount = 0;

		// Room[] newEntity;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].getStatus() == RoomStatus.EMPTY)
				newCount++;
		}
		Room[] newEntity = new Room[newCount];

		System.arraycopy(entities, 0, newEntity, 0, newCount);
		return newEntity;
	}

	public Visitor[] getNotNullVisitors(Visitor[] entities) {

		Integer newCount = 0;

		Visitor[] newEntity;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null) {
				newCount++;
			}
		}
		newEntity = new Visitor[newCount];

		System.arraycopy(entities, 0, newEntity, 0, newCount);
		return newEntity;
	}

	public Service[] getNotNullServices(Service[] entities) {

		Integer newCount = 0;

		Service[] newEntity;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null)
				newCount++;
		}
		newEntity = new Service[newCount];

		System.arraycopy(entities, 0, newEntity, 0, newCount);
		return newEntity;
	}

	public RoomHistory[] getNotNullRoomHistories(RoomHistory[] entities) {

		Integer newCount = 0;

		RoomHistory[] newEntity;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null)
				newCount++;
		}
		newEntity = new RoomHistory[newCount];

		System.arraycopy(entities, 0, newEntity, 0, newCount);
		return newEntity;
	}

	public static int getArraySize(Room[] array) {
		int counter = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == null)
				break;
			else
				counter++;
		}
		return counter;
	}

	public static String[] arrayToString(Room[] entities) {
		int size = getArraySize(entities);
		if (size != 0) {
			String[] stringArray = new String[size];

			for (int i = 0; i < size; i++) {
				stringArray[i] = entities[i].getRoomParameters();
			}
			return stringArray;
		} else {
			return null;
		}
	}
}
