package ui.src.eu.senla.andyavd.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.utils.Printer;
import hotel.src.eu.senla.andyavd.view.HotelManager;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class ChangeServicePriceAction implements IAction {

	final static Logger logger = Logger.getLogger(ChangeServicePriceAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getServices());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input Service id...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input new Service daily price...");

			HotelManager.getInstance().changePriceOnService(HotelManager.getInstance().getServiceById(id), dailyPrice);
		} catch (Exception e) {
			logger.error("No such service to change the Price!", e);
		}
	}
}