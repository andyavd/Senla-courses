package lesson8.server.hotel.src.eu.senla.andyavd.utils;

import java.util.List;

import lesson8.server.hotel.src.eu.senla.andyavd.entities.AEntity;

public class ArrayWorker {

	public static String[] arrayToString(List<? extends AEntity> entities) {

		String[] stringArray = new String[entities.size()];
		             
		for(int i =0; i<entities.size(); i++){
			stringArray[i] = entities.get(i).toString();
		}

		return stringArray;
	}
}
