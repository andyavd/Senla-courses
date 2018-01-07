package eu.senla.andyavd.actions.sorters.service;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.TransactionWorker;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;

public class ByDailyPrice implements IAction {

	final static Logger logger = Logger.getLogger(ByDailyPrice.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

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
