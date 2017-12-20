package eu.senla.andyavd.ui.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

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