package eu.senla.andyavd.actions.sorters.visitor;

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

public class ServicesByPrice implements IAction {

	final static Logger logger = Logger.getLogger(ServicesByPrice.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			
			Request request = new Request("getVisitors", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);
			
			Integer id = InputReader.getIntegerInput(scanner, "Input Visitor id ...");
			request = new Request("getVisitorById", id);
			
			Visitor visitor = (Visitor) serverWorker.sendRequest(request);
			
			request = new Request("sortVisitorServicesByPrice", visitor);
			
			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);

			if (services == null || services.size() == 0) {
				Printer.print("There are no Services for current Visitor!");
			} else {
				Printer.printList(services);
			}
			
		} catch (Exception e) {
			logger.error("Failed to print Visitors Services!", e);
		}
		

	}
}