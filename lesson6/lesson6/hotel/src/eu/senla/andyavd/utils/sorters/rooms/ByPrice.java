package lesson6.hotel.src.eu.senla.andyavd.utils.sorters.rooms;

import java.util.Comparator;

import lesson6.hotel.src.eu.senla.andyavd.entities.Room;

public class ByPrice implements Comparator<Room>{

	@Override
	public int compare(Room r1, Room r2) {
		if(r1 != null && r2 != null) {
			return ((Room) r1).getDailyPrice().compareTo(((Room) r2).getDailyPrice());	
		} else if (r1 != null && r2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}
