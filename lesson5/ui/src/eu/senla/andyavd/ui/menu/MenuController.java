package eu.senla.andyavd.ui.menu;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.utils.InputReader;

public class MenuController {

	private Builder builder = new Builder();
	private Navigator navigator = new Navigator();

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
	}
}
