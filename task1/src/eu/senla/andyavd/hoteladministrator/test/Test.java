package eu.senla.andyavd.hoteladministrator.test;
import java.text.ParseException;
import java.time.LocalDate;

import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.model.entities.Room;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;
import eu.senla.andyavd.hoteladministrator.model.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.utils.ExceptionsLogger;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.NotEmptyRoomException;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;

public class Test {

	public static void main(String[] args) throws ParseException, NotEmptyRoomException {
		
		HotelManager hm = new HotelManager();
		ExceptionsLogger el = new ExceptionsLogger();
		
		Room room1 = new Room(101, 1, 10.4, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room2 = new Room(102, 1, 10.4, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room3 = new Room(103, 2, 18.5, RoomStars.JUNIOR_SUITE, RoomStatus.EMPTY);
		Room room4 = new Room(104, 2, 18.5, RoomStars.JUNIOR_SUITE, RoomStatus.EMPTY);
		Room room5 = new Room(105, 3, 24.8, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room6 = new Room(106, 3, 50.0, RoomStars.PRESIDENT_LUX, RoomStatus.EMPTY);
		Room room7 = new Room(107, 3, 4.1, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room8 = new Room(108, 3, 25.7, RoomStars.LUX, RoomStatus.EMPTY);
		Room room9 = new Room(109, 3, 24.8, RoomStars.STANDARD, RoomStatus.SERVICED);
		Room room10 = new Room(110, 3, 4.1, RoomStars.STANDARD, RoomStatus.EMPTY);
//		Room room11 = new Room(111, 3, 4.4, RoomStars.STANDARD, RoomStatus.EMPTY);
		
		hm.addRoom(room1);
		hm.addRoom(room2);
		hm.addRoom(room3);
		hm.addRoom(room4);
		hm.addRoom(room5);
		hm.addRoom(room6);
		hm.addRoom(room7);
		hm.addRoom(room8);
		hm.addRoom(room9);
		hm.addRoom(room10);
//		hm.createRoom(room11);
		
		System.out.println("/*Show all rooms*/");
		hm.printRooms();
		System.out.println("/*Show all empty rooms*/");
		hm.printEmptyRooms();
		System.out.println("/*Show empty rooms numbers*/");
		hm.printEmptyRoomsNumber();
		System.out.println("/*Sort rooms*/");
		hm.sortRoomsByCapacity();
		System.out.println("price");
		hm.sortRoomsByPrice();
		System.out.println("stars");
		hm.sortRoomsByStars();
		
		System.out.println("/*Sort empty rooms*/");
		hm.sortEmptyRoomsByCapacity();
		System.out.println("/*price*/");
		hm.sortEmptyRoomsByPrice();
		System.out.println("/*stars*/");
		hm.sortEmptyRoomsByStars();
		System.out.println("/*Show room datails*/");
		hm.printDetailsOfRoom(room6);
				
		Visitor visitor1 = new Visitor("Marsh");
		Visitor visitor2 = new Visitor("Broflovski");
		Visitor visitor3 = new Visitor("McCormick");
		Visitor visitor4 = new Visitor("Cartman");
		Visitor visitor5 = new Visitor("Stotch");
//		Visitor visitor6 = new Visitor("Garrison");
//		Visitor visitor7 = new Visitor("McKey");
		
		System.out.println("/*Add visitors*/");
		hm.receiveVisitor(visitor1);
		hm.receiveVisitor(visitor2);
		hm.receiveVisitor(visitor3);
		hm.receiveVisitor(visitor4);
		hm.receiveVisitor(visitor5);
		
		System.out.println("/*Show visitors*/");
		hm.printVisitors();
		System.out.println();
		hm.sortVisitorsByName();
		
		Service service1 = new Service("Laundry", 8.6);
		Service service2 = new Service("Ironing", 2.3);
		Service service3 = new Service("Minibar", 10.4);
		Service service4 = new Service("Wake-up call", 1.4);
		
		System.out.println("/*Add services */");
		hm.createService(service1);
		hm.createService(service2);
		hm.createService(service3);
		hm.createService(service4);
		
		System.out.println("/*Show services */");
		hm.printServices();
		System.out.println("/*Sort services */");
		hm.sortServicesByName();
		System.out.println();
		hm.sortServicesByPrice();
		System.out.println();

		System.out.println("/*Check-in visitors*/");
		try {
			hm.checkInVisitorInRoom(visitor1, room1, LocalDate.of(2017,10,11), LocalDate.of(2017,10,15));
			hm.checkInVisitorInRoom(visitor2, room2, LocalDate.of(2017,10,11), LocalDate.of(2017,10,13));
			hm.checkInVisitorInRoom(visitor3, room3, LocalDate.of(2017,10,11), LocalDate.of(2017,10,14));
			hm.checkInVisitorInRoom(visitor4, room4, LocalDate.of(2017,10,11), LocalDate.of(2017,10,15));
			hm.checkInVisitorInRoom(visitor5, room9, LocalDate.of(2017,10,11), LocalDate.of(2017,10,14));
		} 
		catch(NotEmptyRoomException e){
			
			
			System.out.println(e.getMessage());
			e.printStackTrace();
			el.callMeInAppInfo(e.getMessage());
		}
		

		
		System.out.println("/*Show empty rooms on a date*/");
		Printer.printList(hm.showEmptyRoomsOnDate(LocalDate.of(2017, 10, 12)));
		
		System.out.println("/*Show all visitors on a date*/");
		Printer.print(hm.getTotalVisitorsOnDate(LocalDate.of(2017, 10, 12)));
							
		System.out.println("/*Bill visitor for a room*/");
		
		try {
			hm.billVisitor(visitor5);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			el.callMeInAppInfo(e.getMessage());
		}
		
		
		
		System.out.println("/*Set room as serviced*/");
		hm.changeRoomStatus(room8);
		
		System.out.println("/*Adding services to a visitor*/");		
		try {
			hm.addServicesToVisitor(visitor1, service1);
			hm.addServicesToVisitor(visitor1, service4);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			el.callMeInAppInfo(e.getMessage());
		}

		Printer.printStringList(hm.printVisitorServices(visitor1));
		
		System.out.println("/*Sorting visitors rervices*/");
		hm.sortVisitorServicesByPrice(visitor1);

		System.out.println();
		try {
			hm.checkOutVisitorFromRoom(visitor1, room1);
			hm.checkOutVisitorFromRoom(visitor2, room2);
			hm.checkOutVisitorFromRoom(visitor3, room3);
			hm.checkOutVisitorFromRoom(visitor4, room4);
			hm.checkInVisitorInRoom(visitor2, room1, LocalDate.of(2017,10,16), LocalDate.of(2017,10,17));
			hm.checkOutVisitorFromRoom(visitor2, room1);
			hm.checkInVisitorInRoom(visitor3, room1, LocalDate.of(2017,10,18), LocalDate.of(2017,10,20));
			hm.checkOutVisitorFromRoom(visitor3, room1);
			hm.checkInVisitorInRoom(visitor5, room1, LocalDate.of(2017,10,21), LocalDate.of(2017,10,23));
			hm.checkOutVisitorFromRoom(visitor5, room1);
			
			hm.checkOutVisitorFromRoom(visitor5, room1);
		} catch (EmptyRoomException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			el.callMeInAppInfo(e.getMessage());
		}
		
		System.out.println("/*Last visitors of a room*/");
		Printer.printStringList(hm.getLastVisitorsOfRoom(room1));


		hm.saveToFile();
		hm.loadFromFile();
	}
}
