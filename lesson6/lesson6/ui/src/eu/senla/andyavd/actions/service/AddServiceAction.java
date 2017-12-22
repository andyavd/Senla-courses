package lesson6.ui.src.eu.senla.andyavd.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.entities.Service;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.ui.src.eu.senla.andyavd.api.IAction;
import lesson6.ui.src.eu.senla.andyavd.utils.InputReader;

public class AddServiceAction implements IAction {

	final static Logger logger = Logger.getLogger(AddServiceAction.class);

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);
		try {
			String name = InputReader.getStringInput(scanner, "Input the Service name...");
			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");

			HotelManager.getInstance().addService(new Service(name, dailyPrice));
		} catch (Exception e) {
			logger.error("Failed to add a Service. Input valid parameters!", e);
		}
	}
}