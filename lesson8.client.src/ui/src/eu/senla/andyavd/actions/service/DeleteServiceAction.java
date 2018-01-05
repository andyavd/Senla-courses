package ui.src.eu.senla.andyavd.actions.service;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Service;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class DeleteServiceAction implements IAction {

	final static Logger logger = Logger.getLogger(DeleteServiceAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {

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
