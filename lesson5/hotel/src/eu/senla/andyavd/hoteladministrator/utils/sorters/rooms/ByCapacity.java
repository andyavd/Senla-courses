package eu.senla.andyavd.hoteladministrator.utils.sorters.rooms;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.Room;

public class ByCapacity implements Comparator<Room>{

	@Override
	public int compare(Room r1, Room r2) {
		if(r1 != null && r2 != null) {
			return ((Room) r1).getCapasity().compareTo(((Room) r2).getCapasity());	
		} else if (r1 != null && r2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}
