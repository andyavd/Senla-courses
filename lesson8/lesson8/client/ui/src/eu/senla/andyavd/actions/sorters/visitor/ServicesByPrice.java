package lesson8.client.ui.src.eu.senla.andyavd.actions.sorters.visitor;

import java.util.Scanner;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;
import lesson8.client.ui.src.eu.senla.andyavd.utils.InputReader;

public class ServicesByPrice implements IAction {

	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getVisitors());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input Visitor id ...");
		
		Printer.printList(HotelManager.getInstance().sortVisitorServicesByPrice(HotelManager.getInstance().getVisitorById(id)));

	}
}