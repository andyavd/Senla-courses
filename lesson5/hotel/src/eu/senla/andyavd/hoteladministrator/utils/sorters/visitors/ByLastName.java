package eu.senla.andyavd.hoteladministrator.utils.sorters.visitors;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class ByLastName implements Comparator<Visitor>{

	@Override
	public int compare(Visitor v1, Visitor v2) {
		if(v1 != null && v2 != null) {
			return ((Visitor) v1).getLastName().compareTo(((Visitor) v2).getLastName());	
		} else if (v1 != null && v2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}
