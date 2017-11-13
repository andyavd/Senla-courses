package eu.senla.andyavd.hoteladministrator.utils.sorters.visitors;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;

public class SortingVisitorsByName implements Comparator<AEntity>{

	@Override
	public int compare(AEntity v1, AEntity v2) {
		if(v1 != null && v2 != null) {
			return ((Visitor) v1).getLastName().compareTo(((Visitor) v2).getLastName());	
		} else if (v1 != null && v2 == null) {
			return 1;
		} else {
			return -1;
		}		
	}
}