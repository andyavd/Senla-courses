package lesson8.client.ui.src.eu.senla.andyavd.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;
import lesson8.client.ui.src.eu.senla.andyavd.utils.InputReader;

public class DeleteVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(DeleteVisitorAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Visitor id to delete...");
			HotelManager.getInstance().deleteVisitor(HotelManager.getInstance().getVisitorById(id));
		} catch (Exception e) {
			logger.error("No such Visitor to Delete!", e);
		}
	}
}