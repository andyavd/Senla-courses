package eu.senla.andyavd.hoteladministrator.utils.sorters.roomsorters;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.Room;

public class SortingRoomsByStars implements Comparator<Object>{

	@Override
	public int compare(Object obj1, Object obj2) {

		Room s1 = (Room)obj1;
		Room s2 = (Room)obj2;

		if (s1.getStars().ordinal() > s2.getStars().ordinal()) {
			return 1;
		} else if (s1.getStars().ordinal() < s2.getStars().ordinal()) {
			return -1;
		} else {
			return 0;
		}
	}
}