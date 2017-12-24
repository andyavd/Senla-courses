package eu.senla.andyavd.hoteladministrator.utils.sorters.services;

import java.util.Comparator;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public class SortingServicesByPrice implements Comparator<Service>{

	@Override
	public int compare(Service s1, Service s2) {
		if(s1 != null && s2 != null) {
			return s1.getDailyPrice().compareTo(s2.getDailyPrice());	
		} else if (s1 != null && s2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}