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

public class DeleteVisitorAction implements IAction {

	private final static Logger logger = Logger.getLogger(DeleteVisitorAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {

			Request request = new Request("getVisitors", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);

			Integer id = InputReader.getIntegerInput(scanner, "Input the Visitor id to delete...");

			request = new Request("getVisitorById", id);
			Visitor visitor = (Visitor) serverWorker.sendRequest(request);

			request = new Request("deleteVisitor", visitor);
			serverWorker.sendRequest(request);

			Printer.print("Visitor was successfully deleted!");

		} catch (Exception e) {
			logger.error("No such Service to Delete!", e);
		}
	}
}