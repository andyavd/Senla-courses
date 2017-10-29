package eu.senla.andyavd.hoteladministrator.utils.sorters.servicesorters;

import java.util.Comparator;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public class SortingServicesByName implements Comparator<Object>{

	@Override
	public int compare(Object obj1, Object obj2) {
		
		Service s1 = (Service)obj1;
		Service s2 = (Service)obj2;
		
		return s1.getName().compareTo(s2.getName());
	}
}