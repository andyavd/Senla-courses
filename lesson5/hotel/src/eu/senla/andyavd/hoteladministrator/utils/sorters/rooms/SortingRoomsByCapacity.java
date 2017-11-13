package eu.senla.andyavd.hoteladministrator.utils.sorters.rooms;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;

public class SortingRoomsByCapacity implements Comparator<AEntity>{

	@Override
	public int compare(AEntity r1, AEntity r2) {
		if(r1 != null && r2 != null) {
			return ((Room) r1).getCapasity().compareTo(((Room) r2).getCapasity());	
		} else if (r1 != null && r2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}