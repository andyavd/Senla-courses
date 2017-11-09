package eu.senla.andyavd.hoteladministrator.utils;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;

public class Printer {

	public static void print(String message) {
		System.out.println(message);
	}
	
	public static void printStringList(List<String> strings) {
		for (int i=0; i<strings.size(); i++) {
			if(strings.get(i) != null) {
				System.out.println(strings.get(i));
			}
		}
	}
	
	public static void printList(List<Entity> entities) {
		for (int i=0; i<entities.size(); i++) {
			if(entities.get(i) != null) {
				System.out.println(entities.get(i).toString());
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