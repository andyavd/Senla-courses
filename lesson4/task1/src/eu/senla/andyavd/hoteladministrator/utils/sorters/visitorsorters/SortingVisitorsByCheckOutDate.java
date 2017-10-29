package eu.senla.andyavd.hoteladministrator.utils.sorters.visitorsorters;

import java.util.Comparator;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class SortingVisitorsByCheckOutDate implements Comparator<Object>{

	@Override
	public int compare(Object obj1, Object obj2) {
		
		Visitor s1 = (Visitor)obj1;
		Visitor s2 = (Visitor)obj2;
		
		if (s1.getCheckOutDate().isBefore(s2.getCheckOutDate())) {
			return -1;
		} else {
			return 1;
		} 
	}
}
