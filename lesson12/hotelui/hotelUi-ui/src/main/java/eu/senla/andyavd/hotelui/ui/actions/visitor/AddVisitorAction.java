package eu.senla.andyavd.hotelui.ui.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class AddVisitorAction implements IAction {

	private final static Logger logger = Logger.getLogger(AddVisitorAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			String lastName = InputReader.getStringInput(scanner, "Input the Visitor name to add...");
			Visitor visitor = new Visitor(lastName);

			Request request = new Request("addVisitor", visitor);
			serverWorker.sendRequest(request);

			Printer.print("Visitor was successfully added!");

		} catch (Exception e) {
			logger.error("Failed to add a Visitor!", e);
		}
	}
}