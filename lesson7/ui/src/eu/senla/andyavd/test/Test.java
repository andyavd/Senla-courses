package eu.senla.andyavd.test;

import org.apache.log4j.PropertyConfigurator;

import eu.senla.andyavd.menu.MenuController;

public class Test {
	
	public static void main(String[] args) {
		
		String log4jConfPath = "resources/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		MenuController menuController = new MenuController();
		menuController.run();

	}
}
