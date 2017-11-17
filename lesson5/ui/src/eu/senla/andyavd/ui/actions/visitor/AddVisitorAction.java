package eu.senla.andyavd.ui.actions.visitor;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

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