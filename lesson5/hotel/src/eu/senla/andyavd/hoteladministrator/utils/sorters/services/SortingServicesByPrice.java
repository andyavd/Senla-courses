package eu.senla.andyavd.hoteladministrator.utils.sorters.services;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public class SortingServicesByPrice implements Comparator<AEntity>{

	@Override
	public int compare(AEntity s1, AEntity s2) {
		if(s1 != null && s2 != null) {
			return ((Service) s1).getDailyPrice().compareTo(((Service) s2).getDailyPrice());	
		} else if (s1 != null && s2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}