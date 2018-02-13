package eu.senla.andyavd.actions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.InputReader;

public class ChangeServicePriceAction implements IAction {

	final static Logger logger = Logger.getLogger(ChangeServicePriceAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			
			Request request = new Request("getServices", null);
			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);
			Printer.printList(services);
			
			Integer id = InputReader.getIntegerInput(scanner, "Input Service id...");
			request = new Request("getServiceById", id);
			Service service = (Service) serverWorker.sendRequest(request);
			
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input new Service daily price...");

			List<Object> parametersList = new ArrayList<>();
			parametersList.add(service);
			parametersList.add(dailyPrice);
			
			request = new Request("changePriceOnService", parametersList);
			serverWorker.sendRequest(request);
			
			Printer.print("The price was successfully changed!");
			
		} catch (Exception e) {
			logger.error("No such service to change the Price!", e);
		}
	}
}