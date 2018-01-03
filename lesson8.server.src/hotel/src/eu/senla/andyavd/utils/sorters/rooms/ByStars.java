package hotel.src.eu.senla.andyavd.utils.sorters.rooms;

import java.util.Comparator;

import hotel.src.eu.senla.andyavd.entities.Room;

public class ByStars implements Comparator<Room>{

	@Override
	public int compare(Room r1, Room r2) {
		if(r1 != null && r2 != null) {
			return Integer.valueOf(((Room) r1).getStars().ordinal()).compareTo(Integer.valueOf(((Room) r2).getStars().ordinal()));	
		} else if (r1 != null && r2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}
