package eu.senla.andyavd.hoteladministrator.utils.sorters.rooms;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.model.entities.Room;

public class SortingRoomsByCapacity implements Comparator<Room>{

	@Override
	public int compare(Room r1, Room r2) {
		if(r1 != null && r2 != null) {
			return r1.getCapasity().compareTo(r2.getCapasity());	
		} else if (r1 != null && r2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}