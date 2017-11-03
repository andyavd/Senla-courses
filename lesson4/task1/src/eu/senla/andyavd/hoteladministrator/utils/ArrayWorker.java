package eu.senla.andyavd.hoteladministrator.utils;

import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class ArrayWorker {

	public static Entity[] expandArray(Entity[] entities) {

		Entity[] newEntity = new Entity[entities.length * 2];
		System.arraycopy(entities, 0, newEntity, 0, entities.length);

		return newEntity;
	}

	public static Entity[] getNotNullArray(Entity[] entities) {

		Integer newCount = 0;

		Entity[] newEntity;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null)
				newCount++;
		}
		newEntity = new Entity[newCount];
		System.arraycopy(entities, 0, newEntity, 0, newCount);

		return newEntity;
	}

	public Room[] getNotNullEmptyRooms(Room[] entities) {

		Integer newCount = 0;

		for (int i = 0; i < entities.length; i++) {
			if (entities[i] != null && entities[i].getStatus() == RoomStatus.EMPTY)
				newCount++;
		}
		Room[] newEntity = new Room[newCount];

		System.arraycopy(entities, 0, newEntity, 0, newCount);
		return newEntity;
	}

	public static int getArraySize(Entity[] array) {
		int counter = 0;

		for (int i = 0; i < array.length; i++) {
			if (array[i] == null)
				break;
			else
				counter++;
		}
		return counter;
	}

	public static String[] arrayToString(Entity[] entities) {
		int size = getArraySize(entities);
		if (size != 0) {
			String[] stringArray = new String[size];

			for (int i = 0; i < size; i++) {
				stringArray[i] = entities[i].getEntityParameters();
			}
			return stringArray;
		} else {
			return null;
		}
	}	
}
