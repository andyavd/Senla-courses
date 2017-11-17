package eu.senla.andyavd.ui.actions.visitor;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class PrintAllVisitorsOnDateAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintAllVisitorsOnDateAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);
		try {
			LocalDate localDate = InputReader.getLocalDateInput(scanner, "Input the date like \"YYYY-MM-DD\"...");

			StringBuilder s = new StringBuilder();
			s.append("There are ");
			s.append(HotelManager.getInstance().getTotalVisitorsOnDate(localDate));
			s.append(" visitors on ");
			s.append(localDate);

			Printer.print(s.toString());
		} catch (Exception e) {
			logger.error("Failed to print Visitors. Input a valid date!", e);
		}
	}
}