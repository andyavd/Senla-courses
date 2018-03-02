package eu.senla.andyavd.hotelui.ui.actions.room;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.utils.common.Printer;
import eu.senla.andyavd.hotel.entity.beans.Room;
import eu.senla.andyavd.hotelui.ui.api.IAction;
import eu.senla.andyavd.hotel.utils.client.InputReader;
import eu.senla.andyavd.hotel.utils.client.Request;
import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public class ChangeStatusAction implements IAction {

	private final static Logger logger = Logger.getLogger(ChangeStatusAction.class);

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

			request = new Request("changeRoomStatus", room);
			serverWorker.sendRequest(request);

			Printer.print("The Status of the Room was successfully changed!");

		} catch (Exception e) {
			logger.error("No such Room to change the Status!", e);
		}
	}
}