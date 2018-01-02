package eu.senla.andyavd.actions.sorters.visitor;

import java.util.Scanner;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

public class ServicesByPrice implements IAction {

	@Override
	public void execute() {
		
		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getVisitors());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input Visitor id ...");
		
		Printer.printList(HotelManager.getInstance().sortVisitorServicesByPrice(HotelManager.getInstance().getVisitorById(id)));

	}
}
