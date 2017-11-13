package eu.senla.andyavd.hoteladministrator.utils.sorters.rooms;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;


public class SortingRoomsByPrice implements Comparator<AEntity>{

	@Override
	public int compare(AEntity r1, AEntity r2) {
		if(r1 != null && r2 != null) {
			return ((Room) r1).getDailyPrice().compareTo(((Room) r2).getDailyPrice());	
		} else if (r1 != null && r2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}