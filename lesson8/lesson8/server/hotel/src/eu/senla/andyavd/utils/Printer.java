package lesson8.server.hotel.src.eu.senla.andyavd.utils;

import java.util.List;

import lesson8.server.hotel.src.eu.senla.andyavd.entities.AEntity;

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
	
	public static void printList(List<? extends AEntity> entities) {
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