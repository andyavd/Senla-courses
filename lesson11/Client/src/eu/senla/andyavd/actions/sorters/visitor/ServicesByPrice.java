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

	private final static Logger logger = Logger.getLogger(ServicesByPrice.class);
	
	@Override
	public void execute(TransactionWorker serverWorker) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {
			Request request = new Request("getCheckedVisitorsWithServices", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);
			
			Integer id = InputReader.getIntegerInput(scanner, "Input Visitor id ...");
			request = new Request("getVisitorServices", id);
			
			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);
			Printer.printList(services);	
		} catch (Exception e) {
			logger.error("No such Visitor to show services!", e);
		}
	}
}