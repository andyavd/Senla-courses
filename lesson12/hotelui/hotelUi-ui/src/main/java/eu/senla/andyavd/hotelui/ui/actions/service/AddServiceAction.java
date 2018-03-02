package eu.senla.andyavd.hotelui.ui.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

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