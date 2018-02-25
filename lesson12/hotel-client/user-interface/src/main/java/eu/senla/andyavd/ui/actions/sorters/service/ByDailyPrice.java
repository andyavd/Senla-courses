package eu.senla.andyavd.ui.actions.sorters.service;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Service;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class ByDailyPrice implements IAction {

	private final static Logger logger = Logger.getLogger(ByDailyPrice.class);

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
