package eu.senla.andyavd.actions.visitor;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;

public class ImportVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportVisitorsAction.class);

	@Override
	public void execute() {
		try {
			HotelManager.getInstance().importVisitorsFromCSV();
			Printer.print("Visitors were successfully imported!");
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}
}