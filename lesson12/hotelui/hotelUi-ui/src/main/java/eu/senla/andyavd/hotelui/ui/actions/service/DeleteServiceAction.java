package eu.senla.andyavd.hotelui.ui.actions.service;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class DeleteServiceAction implements IAction {

	private final static Logger logger = Logger.getLogger(DeleteServiceAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {

			Request request = new Request("getServices", null);
			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);
			Printer.printList(services);

			Integer id = InputReader.getIntegerInput(scanner, "Input the Service id to delete...");

			request = new Request("getServiceById", id);
			Service service = (Service) serverWorker.sendRequest(request);

			request = new Request("deleteService", service);
			serverWorker.sendRequest(request);

			Printer.print("Service was successfully deleted!");

		} catch (Exception e) {
			logger.error("No such Service to Delete!", e);
		}

	}

}
