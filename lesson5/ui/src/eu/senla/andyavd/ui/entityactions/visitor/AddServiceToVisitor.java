package eu.senla.andyavd.ui.entityactions.visitor;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class AddServiceToVisitor implements IAction {

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		Printer.printList(HotelManager.getInstance().getServices());
		
		Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id...");
		Integer serviceId = InputReader.getIntegerInput(scanner, "Input the Service id...");

		HotelManager.getInstance().addServicesToVisitor(HotelManager.getInstance().getVisitorById(visitorId), 
				HotelManager.getInstance().getServiceById(serviceId));

	}

}
