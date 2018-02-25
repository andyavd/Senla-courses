package eu.senla.andyavd.ui.actions.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.common.Printer;
import eu.senla.andyavd.beans.Room;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.server.Request;
import eu.senla.andyavd.server.TransactionWorker;
import eu.senla.andyavd.utils.client.InputReader;

public class ChangeRoomPriceAction implements IAction {

	private final static Logger logger = Logger.getLogger(ChangeRoomPriceAction.class);

	@Override
	public void execute(TransactionWorker serverWorker) {

		Scanner scanner = new Scanner(System.in);

		try {

			Request request = new Request("getRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);

			Integer id = InputReader.getIntegerInput(scanner, "Input Room id...");
			request = new Request("getRoomById", id);
			Room room = (Room) serverWorker.sendRequest(request);

			Double dailyPrice = InputReader.getDoubleInput(scanner, "Input new Room daily price...");

			List<Object> parametersList = new ArrayList<>();
			parametersList.add(room);
			parametersList.add(dailyPrice);

			request = new Request("changePriceOnRoom", parametersList);
			serverWorker.sendRequest(request);

			Printer.print("The price was successfully changed!");

		} catch (Exception e) {
			logger.error("No such Room to change the Price!", e);
		}
	}
}