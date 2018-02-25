package eu.senla.andyavd.ui.actions.visitor;

import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Visitor;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class PrintVisitorsAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintVisitorsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		try {

			Request request = new Request("getVisitors", null);

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
