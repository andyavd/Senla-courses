package ui.src.eu.senla.andyavd.actions.visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.entities.Visitor;
import hotel.src.eu.senla.andyavd.server.Request;
import hotel.src.eu.senla.andyavd.server.ServerWorker;
import hotel.src.eu.senla.andyavd.utils.Printer;
import ui.src.eu.senla.andyavd.api.IAction;
import ui.src.eu.senla.andyavd.utils.InputReader;

public class CheckOutAction implements IAction {

	final static Logger logger = Logger.getLogger(CheckOutAction.class);
	
	@Override
	public void execute(ServerWorker serverWorker) {
		
		Scanner scanner = new Scanner(System.in);
		
		try {

			Request request = new Request("getVisitors", null);
			@SuppressWarnings("unchecked")
			List<Visitor> visitors = (List<Visitor>) serverWorker.sendRequest(request);
			Printer.printList(visitors);
			
			request = new Request("getRooms", null);
			@SuppressWarnings("unchecked")
			List<Room> rooms = (List<Room>) serverWorker.sendRequest(request);
			Printer.printList(rooms);
			
			Integer visitorId = InputReader.getIntegerInput(scanner, "Input the Visitor id to check-out...");
			Integer roomId = InputReader.getIntegerInput(scanner, "Input the Room id...");
			
			request = new Request("getVisitorById", visitorId);
			Visitor visitor = (Visitor) serverWorker.sendRequest(request);
			
			request = new Request("getRoomById", roomId);
			Room room = (Room) serverWorker.sendRequest(request);
			
			List<Object> parametersList = new ArrayList<>();
			parametersList.add(visitor);
			parametersList.add(room);
			
			request = new Request("checkOutVisitor", parametersList);
			serverWorker.sendRequest(request);

			Printer.print("Visitor was checked in!");
			
		} catch (Exception e) {
			logger.error("Failed to chek-out the Visitor! Input valid parameters!", e);
		}
	}
}
