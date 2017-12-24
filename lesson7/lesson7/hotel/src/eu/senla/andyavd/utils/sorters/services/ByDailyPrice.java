package lesson7.hotel.src.eu.senla.andyavd.utils.sorters.services;

import java.util.Comparator;

import lesson7.hotel.src.eu.senla.andyavd.entities.Service;

public class ByDailyPrice implements Comparator<Service>{

	@Override
	public int compare(Service s1, Service s2) {
		if(s1 != null && s2 != null) {
			return ((Service) s1).getDailyPrice().compareTo(((Service) s2).getDailyPrice());	
		} else if (s1 != null && s2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}
