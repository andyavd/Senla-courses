package eu.senla.andyavd.actions.room;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.Room;
import eu.senla.andyavd.RoomHistory;
import eu.senla.andyavd.TransactionWorker;
import eu.senla.andyavd.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.utils.InputReader;

public class PrintLastVisitorsAction implements IAction {

	final static Logger logger = Logger.getLogger(PrintLastVisitorsAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {
			
			Request request = new Request("getRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);
			
			Integer id = InputReader.getIntegerInput(scanner, "Input the Room id...");
			
			request = new Request("getRoomById", id);
			Room room = (Room) serverWorker.sendRequest(request);
			
			request = new Request("getLastVisitorsOfRoom", room);
			@SuppressWarnings("unchecked")
			List<RoomHistory> histories = (List<RoomHistory>) serverWorker.sendRequest(request);
			Printer.printList(histories);
			
			if (rooms == null || histories.size() == 0) {
				Printer.print("There are no Visitors for this Rooms!");
			} else {
				Printer.printList(histories);
			}
			
		} catch (Exception e) {
			logger.error("Failed to print Visitors! Input existing Room!", e);
		}
	}
}
