package eu.senla.andyavd.ui.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class BillVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(BillVisitorAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Visitor id to bill...");

			StringBuilder s = new StringBuilder();
			s.append(HotelManager.getInstance().getVisitorById(id).getLastName());
			s.append(" needs to pay ");
			s.append(HotelManager.getInstance().billVisitor(HotelManager.getInstance().getVisitorById(id)));
			s.append(" USD for the room #");
			s.append(HotelManager.getInstance().getVisitorById(id).getHistory().getRoom().getRoomNumber());

			Printer.print(s.toString());
		} catch (Exception e) {
			logger.error("No such Visitor to bill!", e);
		}
	}
}
