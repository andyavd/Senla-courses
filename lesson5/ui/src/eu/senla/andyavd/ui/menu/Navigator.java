package eu.senla.andyavd.ui.menu;

import eu.senla.andyavd.hoteladministrator.utils.Printer;

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

        for (int i = 0; i < currentMenu.getMenuItems().size(); i++) {
        	Printer.print((i + 1) + ". " + currentMenu.getMenuItems().get(i).getName());
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