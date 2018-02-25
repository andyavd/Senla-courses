package eu.senla.andyavd.ui.actions.room;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.beans.Visitor;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.client.InputReader;

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
