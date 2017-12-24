package lesson6.ui.src.eu.senla.andyavd.actions.sorters.visitor;

import java.util.Scanner;

import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

public class ServicesByPrice implements IAction {

	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getVisitors());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input Visitor id ...");
		
		Printer.printList(HotelManager.getInstance().sortVisitorServicesByPrice(HotelManager.getInstance().getVisitorById(id)));

	}
}
