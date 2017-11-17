package eu.senla.andyavd.ui.menu;

import eu.senla.andyavd.ui.actions.room.*;
import eu.senla.andyavd.ui.actions.service.*;
import eu.senla.andyavd.ui.actions.sorters.room.*;
import eu.senla.andyavd.ui.actions.sorters.service.*;
import eu.senla.andyavd.ui.actions.sorters.visitor.*;
import eu.senla.andyavd.ui.actions.visitor.*;
import eu.senla.andyavd.ui.utils.MenuTypes;

public class Builder {

	private static final String action = MenuTypes.ACTION.getConstant();
	private static final String main = MenuTypes.MAIN_MENU.getConstant();

	private Menu roomMenu = new Menu(action);
	private Menu serviceMenu = new Menu(action);
	private Menu visitorMenu = new Menu(action);
	private Menu mainMenu = new Menu(main);
	
	public Menu buildMenu() {
		
		//====================Room==================
		
		roomMenu.addMenuItem(new MenuItem("Add a Room", roomMenu, new AddRoomAction()));
		roomMenu.addMenuItem(new MenuItem("Print the Rooms", roomMenu, new PrintRoomsAction()));
		roomMenu.addMenuItem(new MenuItem("Print empty Rooms", roomMenu, new PrintEmptyRoomsAction()));
		roomMenu.addMenuItem(new MenuItem("Print the quantity of the empty Rooms", roomMenu, new PrintEmptyRoomsNumberAction()));
		roomMenu.addMenuItem(new MenuItem("Print the Room details", roomMenu, new PrintRoomDetailsAction()));
		roomMenu.addMenuItem(new MenuItem("Print empty Rooms on a date", roomMenu, new PrintEmptyRoomsOnDateAction()));
		roomMenu.addMenuItem(new MenuItem("Print the last visitors of the Room", roomMenu, new PrintLastVisitorsOfRoomAction()));
		roomMenu.addMenuItem(new MenuItem("Change the Room's price", roomMenu, new ChangeRoomPriceAction()));
		roomMenu.addMenuItem(new MenuItem("Change the Room's status", roomMenu, new ChangeRoomStatusAction()));
		
		roomMenu.addMenuItem(new MenuItem("Sort the Rooms by capacity", roomMenu, new SortRoomsByCapacity()));
		roomMenu.addMenuItem(new MenuItem("Sort the Rooms by price", roomMenu, new SortRoomsByPrice()));
		roomMenu.addMenuItem(new MenuItem("Sort the Rooms by rate", roomMenu, new SortRoomsByStars()));
		roomMenu.addMenuItem(new MenuItem("Sort the empty Rooms by capacity", roomMenu, new SortEmptyRoomsByCapacity()));
		roomMenu.addMenuItem(new MenuItem("Sort the empty Rooms by price", roomMenu, new SortEmptyRoomsByPrice()));
		roomMenu.addMenuItem(new MenuItem("Sort the empty Rooms by rate", roomMenu, new SortEmptyRoomsByStars()));
		
		roomMenu.addMenuItem(new MenuItem("Back to Main menu", mainMenu));
		
		//====================Service===============
		
		serviceMenu.addMenuItem(new MenuItem("Add a Service", serviceMenu, new AddServiceAction()));
		serviceMenu.addMenuItem(new MenuItem("Print the Services", serviceMenu, new PrintServicesAction()));
		serviceMenu.addMenuItem(new MenuItem("Change the Service's price", serviceMenu, new ChangeServicePriceAction()));
		
		serviceMenu.addMenuItem(new MenuItem("Sort the Services by name", serviceMenu, new SortServicesByName()));
		serviceMenu.addMenuItem(new MenuItem("Sort the Services by price", serviceMenu, new SortServicesByPrice()));
		
		serviceMenu.addMenuItem(new MenuItem("Back to Main menu", mainMenu));
		
		//====================Visitor===============
		
		visitorMenu.addMenuItem(new MenuItem("Add a Visitor", visitorMenu, new AddVisitorAction()));
		visitorMenu.addMenuItem(new MenuItem("Print the Visitors", visitorMenu, new PrintVisitorsAction()));
		visitorMenu.addMenuItem(new MenuItem("Check-in the Visitor", visitorMenu, new CheckInVisitorAction()));
		visitorMenu.addMenuItem(new MenuItem("Add a Service to the Visitor", visitorMenu, new AddServiceToVisitorAction()));
		visitorMenu.addMenuItem(new MenuItem("Print the Visitor's Services", visitorMenu, new PrintVisitorServicesAction()));
		visitorMenu.addMenuItem(new MenuItem("Bill the Visitor", visitorMenu, new BillVisitorAction()));
		visitorMenu.addMenuItem(new MenuItem("Check-out the Visitor", visitorMenu, new CheckOutVisitorAction()));
		visitorMenu.addMenuItem(new MenuItem("Print the quantity of the Visitors on a date", visitorMenu, new PrintAllVisitorsOnDateAction()));
		visitorMenu.addMenuItem(new MenuItem("Delete the Visitor", visitorMenu, new DeleteVisitorAction()));
		
		visitorMenu.addMenuItem(new MenuItem("Sort the Visitors by name", visitorMenu, new SortVisitorsByName()));
		visitorMenu.addMenuItem(new MenuItem("Sort the Visitor's Services by price", visitorMenu, new SortVisitorServicesByPrice()));
		
		visitorMenu.addMenuItem(new MenuItem("Back to Main menu", mainMenu));
		
		//====================Main==================
		
		mainMenu.addMenuItem(new MenuItem("Room Menu", roomMenu));
		mainMenu.addMenuItem(new MenuItem("Service Menu", serviceMenu));
		mainMenu.addMenuItem(new MenuItem("Visitor Menu", visitorMenu));
		mainMenu.addMenuItem(new MenuItem("Exit Programm"));
		
		return mainMenu;
	}
	
	public Menu getMenu() {
		return this.buildMenu();
		
	}
}
