package eu.senla.andyavd.hoteladministrator.utils.sorters.visitorsorters;

import java.util.Comparator;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class SortingVisitorsByName implements Comparator<Object>{

	@Override
	public int compare(Object obj1, Object obj2) {
		
		Visitor s1 = (Visitor)obj1;
		Visitor s2 = (Visitor)obj2;
		
		return s1.getLastName().compareTo(s2.getLastName());
	}
}