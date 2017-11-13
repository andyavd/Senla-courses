package eu.senla.andyavd.ui.entityactions.visitor;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class BillVisitor implements IAction {

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input the Visitor id to bill...");
		
		Printer.print(HotelManager.getInstance().billVisitor(HotelManager.getInstance().getVisitorById(id)));

	}
}