package eu.senla.andyavd.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

public class AddServiceToAction implements IAction {

	final static Logger logger = Logger.getLogger(AddServiceToAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		Printer.printList(HotelManager.getInstance().getServices());
		try {
			Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id...");
			Integer serviceId = InputReader.getIntegerInput(scanner, "Input the Service id...");

			HotelManager.getInstance().addServicesToVisitor(HotelManager.getInstance().getVisitorById(visitorId),
					HotelManager.getInstance().getServiceById(serviceId));
		} catch (Exception e) {
			logger.error("No such Visitor or Service!", e);
		}
	}

}
