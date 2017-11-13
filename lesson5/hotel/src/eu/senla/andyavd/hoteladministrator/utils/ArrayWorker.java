package eu.senla.andyavd.hoteladministrator.utils;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class ArrayWorker {

	public static List<AEntity> getNotNullEmptyRooms(List<AEntity> entities) {

		List<AEntity> newEntity = new ArrayList<AEntity>();

		for (int i = 0; i < entities.size(); i++) {
			if (((Room) entities.get(i)).getStatus() == RoomStatus.EMPTY)
				newEntity.add(entities.get(i));
		}
		return newEntity;
	}

	public static String[] arrayToString(List<AEntity> entities) {

		String[] stringArray = new String[entities.size()];
		             
		for(int i =0; i<entities.size(); i++){
			stringArray[i] = entities.get(i).toString();
		}

		return stringArray;
	}
}