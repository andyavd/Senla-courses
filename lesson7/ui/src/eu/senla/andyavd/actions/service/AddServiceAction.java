package eu.senla.andyavd.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.entities.Service;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.utils.InputReader;

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