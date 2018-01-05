package ui.src.eu.senla.andyavd.actions.sorters.service;

import java.util.List;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Service;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ByDailyPrice implements IAction {

	final static Logger logger = Logger.getLogger(ByDailyPrice.class);

	@Override
	public void execute(ServerWorker serverWorker) {

		try {

			Request request = new Request("sortServicesByPrice", null);

			@SuppressWarnings("unchecked")
			List<Service> services = (List<Service>) serverWorker.sendRequest(request);

			if (services == null || services.size() == 0) {
				Printer.print("There are no Services available!");
			} else {
				Printer.printList(services);
			}

		} catch (Exception e) {
			logger.error("Failed print Services!", e);
		}

	}

}
