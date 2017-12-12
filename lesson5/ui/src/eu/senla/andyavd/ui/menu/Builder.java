package eu.senla.andyavd.ui.menu;

import eu.senla.andyavd.hoteladministrator.view.HotelManager;
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
	
	public Builder() {
		HotelManager.getInstance();
	}
	
	public Menu buildMenu() {
		
		//====================Room==================
		
		roomMenu.addMenuItem(new MenuItem("Add a Room", roomMenu, new AddRoomAction()));
		roomMenu.addMenuItem(new MenuItem("Print the Rooms", roomMenu, new PrintRoomsAction()));
		/////////////
		roomMenu.addMenuItem(new MenuItem("Clone the Room", roomMenu, new CloneAction()));
		/////////////
		roomMenu.addMenuItem(new MenuItem("Print empty Rooms", roomMenu, new PrintEmptyAction()));
		roomMenu.addMenuItem(new MenuItem("Print the quantity of the empty Rooms", roomMenu, new PrintEmptyNumberAction()));
		roomMenu.addMenuItem(new MenuItem("Print the Room details", roomMenu, new PrintDetailsAction()));
		roomMenu.addMenuItem(new MenuItem("Print empty Rooms on a date", roomMenu, new PrintEmptyOnDateAction()));
		roomMenu.addMenuItem(new MenuItem("Print the last visitors of the Room", roomMenu, new PrintLastVisitorsAction()));
		roomMenu.addMenuItem(new MenuItem("Change the Room's price", roomMenu, new ChangeRoomPriceAction()));
		
		if(HotelManager.getInstance().isRoomStatus()) {
		roomMenu.addMenuItem(new MenuItem("Change the Room's status", roomMenu, new ChangeStatusAction()));
		}
		roomMenu.addMenuItem(new MenuItem("Sort the Rooms by capacity", roomMenu, new ByCapacity()));
		roomMenu.addMenuItem(new MenuItem("Sort the Rooms by price", roomMenu, new ByPrice()));
		roomMenu.addMenuItem(new MenuItem("Sort the Rooms by rate", roomMenu, new ByStars()));
		roomMenu.addMenuItem(new MenuItem("Sort the empty Rooms by capacity", roomMenu, new EmptyByCapacity()));
		roomMenu.addMenuItem(new MenuItem("Sort the empty Rooms by price", roomMenu, new EmptyByPrice()));
		roomMenu.addMenuItem(new MenuItem("Sort the empty Rooms by rate", roomMenu, new EmptyByStars()));
		roomMenu.addMenuItem(new MenuItem("Export Rooms information to file", roomMenu, new ExportRoomsAction()));
		roomMenu.addMenuItem(new MenuItem("Import Rooms information from file", roomMenu, new ImportRoomsAction()));
		
		roomMenu.addMenuItem(new MenuItem("Back to Main menu", mainMenu));
		
		//====================Service===============
		
		serviceMenu.addMenuItem(new MenuItem("Add a Service", serviceMenu, new AddServiceAction()));
		serviceMenu.addMenuItem(new MenuItem("Print the Services", serviceMenu, new PrintServicesAction()));
		serviceMenu.addMenuItem(new MenuItem("Change the Service's price", serviceMenu, new ChangeRoomPriceAction()));
		serviceMenu.addMenuItem(new MenuItem("Delete a Service", serviceMenu, new DeleteServiceAction()));
		
		serviceMenu.addMenuItem(new MenuItem("Sort the Services by name", serviceMenu, new ByName()));
		serviceMenu.addMenuItem(new MenuItem("Sort the Services by price", serviceMenu, new ByDailyPrice()));
		serviceMenu.addMenuItem(new MenuItem("Export Services information to file", serviceMenu, new ExportServicesAction()));
		serviceMenu.addMenuItem(new MenuItem("Import Services information from file", serviceMenu, new ImportServicesAction()));
		
		serviceMenu.addMenuItem(new MenuItem("Back to Main menu", mainMenu));
		
		//====================Visitor===============
		
		visitorMenu.addMenuItem(new MenuItem("Add a Visitor", visitorMenu, new AddVisitorAction()));
		visitorMenu.addMenuItem(new MenuItem("Print the Visitors", visitorMenu, new PrintVisitorsAction()));
		visitorMenu.addMenuItem(new MenuItem("Check-in the Visitor", visitorMenu, new CheckInAction()));
		visitorMenu.addMenuItem(new MenuItem("Add a Service to the Visitor", visitorMenu, new AddServiceToAction()));
		visitorMenu.addMenuItem(new MenuItem("Print the Visitor's Services", visitorMenu, new PrintServicesOfAction()));
		visitorMenu.addMenuItem(new MenuItem("Bill the Visitor", visitorMenu, new BillAction()));
		visitorMenu.addMenuItem(new MenuItem("Check-out the Visitor", visitorMenu, new CheckOutAction()));
		visitorMenu.addMenuItem(new MenuItem("Print the quantity of the Visitors on a date", visitorMenu, new PrintOnDateAction()));
		visitorMenu.addMenuItem(new MenuItem("Delete the Visitor", visitorMenu, new DeleteVisitorAction()));
		
		visitorMenu.addMenuItem(new MenuItem("Sort the Visitors by name", visitorMenu, new ByLastName()));
		visitorMenu.addMenuItem(new MenuItem("Sort the Visitor's Services by price", visitorMenu, new ServicesByPrice()));
		visitorMenu.addMenuItem(new MenuItem("Export Visitors information to file", visitorMenu, new ExportVisitorsAction()));
		visitorMenu.addMenuItem(new MenuItem("Import Visitors information from file", visitorMenu, new ImportVisitorsAction()));
		
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
