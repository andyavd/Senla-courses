package eu.senla.andyavd.hoteladministrator.utils;

import eu.senla.andyavd.hoteladministrator.entities.Entity;

public class ArrayWorker {
	public static Entity[] expand (Entity[] array) {

        Entity[] newArray = new Entity[array.length*2];

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null)
                break;
            else
                newArray[i] = array[i];
        }
        return newArray;
    }
}
