package ui.src.eu.senla.andyavd.menu;

import java.util.List;

<<<<<<< HEAD:lesson8.client.src/ui/src/eu/senla/andyavd/menu/Navigator.java
import hotel.src.eu.senla.andyavd.utils.Printer;
=======
import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
>>>>>>> 8b05dbb95ab2ea88fee51e08c4527577aa14a336:lesson8/lesson8/client/ui/src/eu/senla/andyavd/menu/Navigator.java

public class Navigator {

	private Menu currentMenu;

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public void printMenu() {

		Printer.print(currentMenu.getName());
		List<MenuItem> items = currentMenu.getMenuItems();
		for (int i = 0; i < items.size(); i++) {
			MenuItem item = items.get(i);
			Printer.print((i + 1) + ". " + item.getName());
		}
	}

	public void navigate(Integer index) {
		if (currentMenu.getMenuItems().get(index).getAction() != null) {
			currentMenu.getMenuItems().get(index).act();
		} else {
			currentMenu.getMenuItems().get(index).getNextMenu();
		}
	}
}
