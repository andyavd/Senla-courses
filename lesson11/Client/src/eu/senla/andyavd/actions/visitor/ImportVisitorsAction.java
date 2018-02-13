package eu.senla.andyavd.actions.visitor;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;

public class ImportVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportVisitorsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {
		try {
			
			Request request = new Request("importVisitorsFromCSV", null);
			serverWorker.sendRequest(request);
			
			Printer.print("Visitors were successfully imported!");
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}
}