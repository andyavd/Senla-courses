package lesson8.client.ui.src.eu.senla.andyavd.actions.service;

import java.util.Scanner;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.client.ui.src.eu.senla.andyavd.api.IAction;
import lesson8.client.ui.src.eu.senla.andyavd.utils.InputReader;

public class DeleteServiceAction implements IAction {

	final static Logger logger = Logger.getLogger(DeleteServiceAction.class);
	
	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);

		Printer.printList(HotelManager.getInstance().getServices());
		try {
			Integer id = InputReader.getIntegerInput(scanner, "Input the Service id to delete...");
			HotelManager.getInstance().deleteService(HotelManager.getInstance().getServiceById(id));
		} catch (Exception e) {
			logger.error("No such Visitor to Delete!", e);
		}

	}

}
