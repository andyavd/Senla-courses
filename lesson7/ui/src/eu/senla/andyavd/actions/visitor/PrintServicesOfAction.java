package eu.senla.andyavd.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

public class PrintServicesOfAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintServicesOfAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());

		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Visitor id...");

			StringBuilder s = new StringBuilder();
			s.append("Visitor ");
			s.append(HotelManager.getInstance().getVisitorById(id).getLastName());
			s.append(" has used services:");

			Printer.print(s.toString());
			Printer.printList(
					HotelManager.getInstance().getVisitorServices(HotelManager.getInstance().getVisitorById(id)));
		} catch (Exception e) {
			logger.error("No such Visitor to show services!", e);
		}
	}
}
