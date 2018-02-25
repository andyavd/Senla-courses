package eu.senla.andyavd.ui.actions.room;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.client.InputReader;
import eu.senla.andyavd.utils.common.Printer;

public class PrintDetailsAction implements IAction {

	private final static Logger logger = Logger.getLogger(PrintDetailsAction.class);

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

			StringBuilder s = new StringBuilder();
			s.append("Room details: ");
			s.append(room.toString());

			Printer.print(s.toString());
		} catch (Exception e) {
			logger.error("No such room to show the details!", e);
		}
	}
}
