package eu.senla.andyavd.actions.visitor;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DateFormatter;
import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.Visitor;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.InputReader;

public class CheckInAction implements IAction {

	private final static Logger logger = Logger.getLogger(CheckInAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);
		
		try {

			Request request = new Request("getVisitors", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);
			
			request = new Request("getEmptyRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);
			
			int visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id to check-in...");
			int roomId = InputReader.getIntegerInput(scanner, "Input the Room id...");
			Date inDate = DateFormatter.dateFromString(InputReader.getStringInput(scanner, "Input the in-date like \"yyyy-MM-dd\"..."));
			Date outDate = DateFormatter.dateFromString(InputReader.getStringInput(scanner, "Input the out-date like \"yyyy-MM-dd\"..."));

			RoomHistory history = new RoomHistory(visitorId, roomId, inDate, outDate);
			request = new Request("checkInVisitor", history);
			serverWorker.sendRequest(request);
			Printer.print("Visitor was checked in!");
			
		} catch (Exception e) {
			logger.error("Failed to chek-in the Visitor! Input valid parameters!", e);
		}
	}
}
