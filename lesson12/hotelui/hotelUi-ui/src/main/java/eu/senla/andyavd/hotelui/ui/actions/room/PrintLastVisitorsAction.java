package eu.senla.andyavd.hotelui.ui.actions.room;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotel.entity.beans.Visitor;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class PrintLastVisitorsAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintLastVisitorsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {

			Request request = new Request("getUsedRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);

			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");

			request = new Request("getLastVisitorsOfRoom", id);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);

		} catch (Exception e) {
			logger.error("Failed to print Visitors! Input existing Room!", e);
		}
	}
}
