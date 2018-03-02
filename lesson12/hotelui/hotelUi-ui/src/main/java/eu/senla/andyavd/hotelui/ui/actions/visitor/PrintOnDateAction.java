package eu.senla.andyavd.hotelui.ui.actions.visitor;

import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.DateFormatter;
import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class PrintOnDateAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintOnDateAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);
		try {
			Date date = DateFormatter
					.dateFromString((InputReader.getStringInput(scanner, "Input the date like \"YYYY-MM-DD\"...")));

			Request request = new Request("getTotalVisitorsOnDate", date);
			Integer number = (Integer) serverWorker.sendRequest(request);

			Printer.print("There are " + number + " visitors on " + date);

		} catch (Exception e) {
			logger.error("Failed to print Visitors. Input a valid date!", e);
		}
	}
}
