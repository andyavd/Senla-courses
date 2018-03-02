package eu.senla.andyavd.hotelui.ui.actions.visitor;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class PrintServicesOfAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintServicesOfAction.class);

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