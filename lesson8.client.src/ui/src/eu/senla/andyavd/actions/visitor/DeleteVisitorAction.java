package ui.src.eu.senla.andyavd.actions.visitor;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Visitor;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class DeleteVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(DeleteVisitorAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {

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