package eu.senla.andyavd.ui.actions.visitor;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Visitor;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.client.InputReader;

public class CheckOutAction implements IAction {

	private final static Logger logger = Logger.getLogger(CheckOutAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			Request request = new Request("getCheckedVisitors", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);
			Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id to check-out...");
			request = new Request("checkOutVisitor", visitorId);
			serverWorker.sendRequest(request);
			Printer.print("Visitor was checked out!");

		} catch (Exception e) {
			logger.error("Failed to chek-out the Visitor! Input valid parameters!", e);
		}
	}
}