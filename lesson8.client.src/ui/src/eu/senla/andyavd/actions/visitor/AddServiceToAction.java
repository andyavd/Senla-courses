package ui.src.eu.senla.andyavd.actions.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Service;
import hotel.src.eu.senla.andyavd.entities.Visitor;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class AddServiceToAction implements IAction {

	final static Logger logger = Logger.getLogger(AddServiceToAction.class);

	@Override
	public void execute(ServerWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			
			Request request = new Request("getVisitors", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);
			
			request = new Request("getServices", null);
			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);
			Printer.printList(services);
			
			Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id...");
			Integer serviceId = InputReader.getIntegerInput(scanner, "Input the Service id...");

			request = new Request("getVisitorById", visitorId);
			Visitor visitor = (Visitor) serverWorker.sendRequest(request);
			
			request = new Request("getServiceById", serviceId);
			Service service = (Service) serverWorker.sendRequest(request);
			
			List<Object> parametersList = new ArrayList<>();
			parametersList.add(visitor);
			parametersList.add(service);
			
			request = new Request("addServicesToVisitor", parametersList);
			serverWorker.sendRequest(request);
			
			Printer.print("Service was successfully added!");
			
		} catch (Exception e) {
			logger.error("No such Visitor or Service!", e);
		}
	}

}
