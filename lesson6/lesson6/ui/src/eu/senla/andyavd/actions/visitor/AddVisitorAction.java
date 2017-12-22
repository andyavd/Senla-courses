package lesson6.ui.src.eu.senla.andyavd.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.entities.Visitor;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

public class AddVisitorAction implements IAction {

	final static Logger logger = Logger.getLogger(AddVisitorAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);
		try {
			String lastName = InputReader.getStringInput(scanner, "Input the Visitor name to add...");
			HotelManager.getInstance().addVisitor(new Visitor(lastName));
		} catch (Exception e) {
			logger.error("Failed to add a Visitor. Input valid parameters!", e);
		}
	}
}