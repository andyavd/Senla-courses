package eu.senla.andyavd.hoteladministrator.utils;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;

public class ArrayWorker {

	public static String[] arrayToString(List<? extends AEntity> entities) {

		String[] stringArray = new String[entities.size()];
		             
		for(int i =0; i<entities.size(); i++){
			stringArray[i] = entities.get(i).toString();
		}

		return stringArray;
	}
}
