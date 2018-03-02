package eu.senla.andyavd.hotelui.ui.menu;

import java.util.Scanner;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.controller.HotelManager;

public class MenuController {

	private Builder builder = new Builder();
	private Navigator navigator = new Navigator();

	private TransactionWorker serverWorker;

	public MenuController(TransactionWorker serverWorker) {
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
