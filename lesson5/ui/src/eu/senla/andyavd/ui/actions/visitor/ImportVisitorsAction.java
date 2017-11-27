package eu.senla.andyavd.ui.actions.visitor;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.FileParser;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.csvparsers.readers.CSVToVisitor;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;

public class ImportVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(ImportVisitorsAction.class);

	@Override
	public void execute() {
		try {
			HotelManager.getInstance().setVisitors(FileParser.stringToVisitors(CSVToVisitor.readVisitors()));
			Printer.print("Visitors were successfully imported!");
		} catch (Exception e) {
			logger.error("Visitors were not imported!", e);
		}
	}
}