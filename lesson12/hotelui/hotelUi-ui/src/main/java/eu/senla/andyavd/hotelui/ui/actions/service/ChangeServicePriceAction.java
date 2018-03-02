package eu.senla.andyavd.hotelui.ui.actions.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class ChangeServicePriceAction implements IAction {

	private final static Logger logger = Logger.getLogger(ChangeServicePriceAction.class);

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