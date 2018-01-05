package ui.src.eu.senla.andyavd.menu;

import hotel.src.eu.senla.andyavd.server.ServerWorker;
import ui.src.eu.senla.andyavd.api.IAction;

public class MenuItem {
	private String name;
	private IAction action;
	private Menu nextMenu;
	private ServerWorker serverWorker;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

	public MenuItem(String name) {
		this.name = name;
	}

	public MenuItem(String name, Menu nextMenu) {
		this.name = name;
		this.nextMenu = nextMenu;
	}

	public MenuItem(String name, Menu nextMenu, IAction action) {
		this.name = name;
		this.nextMenu = nextMenu;
		this.action = action;
	}

	void act() {
		action.execute(serverWorker);
	}
}
