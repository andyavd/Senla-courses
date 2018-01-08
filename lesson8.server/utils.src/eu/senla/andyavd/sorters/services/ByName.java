package eu.senla.andyavd.sorters.services;

import java.util.Comparator;

import eu.senla.andyavd.Service;

public class ByName implements Comparator<Service>{

	@Override
	public int compare(Service s1, Service s2) {
		if(s1 != null && s2 != null) {
			return ((Service) s1).getName().compareTo(((Service) s2).getName());	
		} else if (s1 != null && s2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}
