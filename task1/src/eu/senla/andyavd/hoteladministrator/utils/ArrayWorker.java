package eu.senla.andyavd.hoteladministrator.utils;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Room;

public class ArrayWorker {

	public static List<Entity> getNotNullEmptyRooms(List<Entity> entities) {

		List<Entity> newEntity = new ArrayList<Entity>();

		for (int i = 0; i < entities.size(); i++) {
			if (((Room) entities.get(i)).getStatus() == RoomStatus.EMPTY)
				newEntity.add(entities.get(i));
		}
		return newEntity;
	}

	public static String[] arrayToString(List<Entity> entities) {

		String[] stringArray = new String[entities.size()];
		             
		for(int i =0; i<entities.size(); i++){
			stringArray[i] = entities.get(i).toString();
		}

		return stringArray;
	}
}