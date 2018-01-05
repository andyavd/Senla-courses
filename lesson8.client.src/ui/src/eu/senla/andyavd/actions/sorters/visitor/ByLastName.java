package ui.src.eu.senla.andyavd.actions.sorters.visitor;

import java.util.List;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Visitor;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;

public class ByLastName implements IAction {

	final static Logger logger = Logger.getLogger(ByLastName.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {

		try {

			Request request = new Request("sortVisitorsByName", null);

			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);

			if (visitors == null || visitors.size() == 0) {
				Printer.print("There are no Visitors registered!");
			} else {
				Printer.printList(visitors);
			}

		} catch (Exception e) {
			logger.error("Failed print Visitors!", e);
		}

	}
}