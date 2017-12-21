package eu.senla.andyavd.ui.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class DeleteVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(DeleteVisitorAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getVisitors());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Visitor id to delete...");
			HotelManager.getInstance().deleteVisitor(HotelManager.getInstance().getVisitorById(id));
		} catch (Exception e) {
			logger.error("No such Visitor to Delete!", e);
		}
	}
}