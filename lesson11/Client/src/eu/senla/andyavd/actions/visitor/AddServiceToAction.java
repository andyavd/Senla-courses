package eu.senla.andyavd.actions.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.InputReader;

public class AddServiceToAction implements IAction {

	final static Logger logger = Logger.getLogger(AddServiceToAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

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
