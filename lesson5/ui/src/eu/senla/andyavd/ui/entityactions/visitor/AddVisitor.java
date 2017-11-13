package eu.senla.andyavd.ui.entityactions.visitor;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class AddVisitor implements IAction {

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		String lastName = InputReader.getStringInput(scanner, "Input the Visitor name to add...");

		HotelManager.getInstance().addVisitor(new Visitor(lastName));

	}
}