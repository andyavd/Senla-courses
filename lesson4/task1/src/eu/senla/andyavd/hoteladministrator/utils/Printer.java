package eu.senla.andyavd.hoteladministrator.utils;

import eu.senla.andyavd.hoteladministrator.entities.Entity;

public class Printer {

	public static void print(String message) {
		System.out.println(message);
	}
	
	public static void printArray(Entity[] entities) {
		for (int i=0; i<entities.length; i++) {
			if(entities[i] != null) {
				System.out.println(entities[i].toString());
			}
		}
	}
	
	public static void printStringArray(String[] strings) {
		for (int i=0; i<strings.length; i++) {
			if(strings[i] != null) {
				System.out.println(strings[i]);
			}
		}
	}
}
