package eu.senla.andyavd.hotelui.ui.actions.service;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotelui.ui.api.IAction;

public class PrintServicesAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintServicesAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		try {

			Request request = new Request("getServices", null);

			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);

			if (services == null || services.size() == 0) {
				Printer.print("There are no Services available!");
			} else {
				Printer.printList(services);
			}

		} catch (Exception e) {
			logger.error("Failed to print Services!", e);
		}

	}
}