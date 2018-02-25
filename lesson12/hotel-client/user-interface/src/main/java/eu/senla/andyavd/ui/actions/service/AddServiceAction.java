package eu.senla.andyavd.ui.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Service;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.client.InputReader;

public class AddServiceAction implements IAction {

	private final static Logger logger = Logger.getLogger(AddServiceAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);
		try {
			String name = InputReader.getStringInput(scanner, "Input the Service name...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");

			Service service = new Service(name, dailyPrice);
			
			Request request = new Request("addService", service);
			serverWorker.sendRequest(request);
			
			Printer.print("Service was successfully added!");
			
		} catch (Exception e) {
			logger.error("Failed to add a Service!", e);
		}
	}
}