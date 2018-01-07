package eu.senla.andyavd.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.TransactionWorker;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.utils.InputReader;

public class AddVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(AddVisitorAction.class);

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