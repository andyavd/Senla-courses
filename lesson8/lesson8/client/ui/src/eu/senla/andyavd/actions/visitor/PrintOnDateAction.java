package lesson8.client.ui.src.eu.senla.andyavd.actions.visitor;

import java.time.LocalDate;
import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;
import lesson8.client.ui.src.eu.senla.andyavd.utils.InputReader;

public class PrintOnDateAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintOnDateAction.class);

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
