package ui.src.eu.senla.andyavd.menu;

import java.util.Scanner;

import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import hotel.src.eu.senla.andyavd.view.HotelManager;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class MenuController {

	private Builder builder = new Builder();
	private Navigator navigator = new Navigator();

	private ServerWorker serverWorker;
	
	public MenuController(ServerWorker serverWorker) {
		this.serverWorker = serverWorker;
	}
	
	public void run() {

		Scanner scanner = new Scanner(System.in);

		boolean exit = false;

		navigator.setCurrentMenu(builder.getMenu());
		navigator.printMenu();
		
		while (!exit) {

			Integer choice = InputReader.getIntegerInput(scanner) - 1;

			if (choice >= navigator.getCurrentMenu().getMenuItems().size()) {
				Printer.print("Incorrect choice. Try again");
				continue;
			} else {
				navigator.navigate(choice);
			}

			if (navigator.getCurrentMenu().getMenuItems().get(choice).getNextMenu() == null) {
				exit = true;
				continue;
			}

			navigator.setCurrentMenu(navigator.getCurrentMenu().getMenuItems().get(choice).getNextMenu());
			navigator.printMenu();
		}
		HotelManager.getInstance().exit();
		scanner.close();
		Printer.print("Goodbye! Your changes have been saved!");
		
		serverWorker.disconnect();
	}
}
