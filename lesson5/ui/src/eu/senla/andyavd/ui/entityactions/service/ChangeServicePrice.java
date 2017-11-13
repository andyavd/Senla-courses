package eu.senla.andyavd.ui.entityactions.service;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class ChangeServicePrice implements IAction {

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);
		
		Printer.printList(HotelManager.getInstance().getServices());
		
		Integer id = InputReader.getIntegerInput(scanner, "Input Service id...");
		Double dailyPrice = InputReader.getDoubleInput(scanner, "Input new Service daily price...");
		
		HotelManager.getInstance().changePriceOnService(HotelManager.getInstance().getServiceById(id), dailyPrice);

	}
}