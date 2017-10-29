package eu.senla.andyavd.hoteladministrator;
import java.text.ParseException;

import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.managers.HotelManager;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		HotelManager hm = new HotelManager();
		
		Room room1 = new Room(101, 1, 10, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room2 = new Room(102, 1, 10, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room3 = new Room(103, 2, 18, RoomStars.JUNIOR_SUITE, RoomStatus.EMPTY);
		Room room4 = new Room(104, 2, 18, RoomStars.JUNIOR_SUITE, RoomStatus.EMPTY);
		Room room5 = new Room(105, 3, 24, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room6 = new Room(106, 3, 50, RoomStars.PRESIDENT_LUX, RoomStatus.EMPTY);
		Room room7 = new Room(107, 3, 4, RoomStars.STANDARD, RoomStatus.EMPTY);
		Room room8 = new Room(108, 3, 25, RoomStars.LUX, RoomStatus.EMPTY);
		Room room9 = new Room(109, 3, 24, RoomStars.STANDARD, RoomStatus.SERVICED);
		Room room10 = new Room(110, 3, 4, RoomStars.STANDARD, RoomStatus.EMPTY);
//		Room room11 = new Room(111, 3, 4, RoomStars.STANDARD, RoomStatus.EMPTY);
		
		hm.createRoom(room1);
		hm.createRoom(room2);
		hm.createRoom(room3);
		hm.createRoom(room4);
		hm.createRoom(room5);
		hm.createRoom(room6);
		hm.createRoom(room7);
		hm.createRoom(room8);
		hm.createRoom(room9);
		hm.createRoom(room10);
//		hm.insertRoom(room11);
		
		System.out.println("/*Show all rooms*/");
		hm.printRooms();
		System.out.println("/*Show all empty rooms*/");
		hm.printEmptyRooms();
		System.out.println("/*Show empty rooms numbers*/");
		hm.printEmptyRoomsNumber();
		System.out.println("/*Sort rooms by parameter*/");
		hm.sortRoomsBy("price");
		System.out.println("/*Sort empty rooms by parameter*/");
		hm.sortEmptyRoomsBy("price");
		System.out.println("/*Show room datails*/");
		hm.printDetailsOfRoomNumber(106);
				
		Visitor visitor1 = new Visitor("Marsh");
		Visitor visitor2 = new Visitor("Broflovski");
		Visitor visitor3 = new Visitor("McCormick");
		Visitor visitor4 = new Visitor("Cartman");
		Visitor visitor5 = new Visitor("Stotch");
//		Visitor visitor6 = new Visitor("Garrison");
//		Visitor visitor7 = new Visitor("McKey");
		
//		System.out.println("/*Add visitors*/");
		hm.receiveVisitor(visitor1);
		hm.receiveVisitor(visitor2);
		hm.receiveVisitor(visitor3);
		hm.receiveVisitor(visitor4);
		hm.receiveVisitor(visitor5);
		System.out.println("/*Show visitors*/");
		hm.printVisitors();		
		
		Service service1 = new Service("Laundry", 8);
		Service service2 = new Service("Ironing", 2);
		Service service3 = new Service("Minibar", 10);
		Service service4 = new Service("Wake-up call", 1);
		
//		System.out.println("/*Add services */");
		hm.createService(service1);
		hm.createService(service2);
		hm.createService(service3);
		hm.createService(service4);
		
//		hm.printServices();
//		System.out.println();
//		hm.sortServicesBy("name");
//		sm.sortServicesByName();
//		System.out.println();
//		sm.sortServicesByPrice();

		System.out.println("/*Check-in visitors*/");
		hm.checkInVisitorInARoom(visitor1, room1, "2017-10-11", "2017-10-15");
		hm.checkInVisitorInARoom(visitor2, room2, "2017-10-11", "2017-10-13");
		hm.checkInVisitorInARoom(visitor3, room3, "2017-10-11", "2017-10-14");
		hm.checkInVisitorInARoom(visitor4, room4, "2017-10-11", "2017-10-15");
		hm.checkInVisitorInARoom(visitor5, room9, "2017-10-11", "2017-10-14");
		
//		System.out.println();
//		hm.printEmptyRooms();
//		System.out.println();
//		hm.printEmptyRoomsNumber();
//		hm.showAndSortVisitorsBy("lastName");
		System.out.println("/*Show empty rooms on a date*/");
		hm.showRoomsEmptyOnAParticularDate("2017-10-12");
		System.out.println("/*Show all visitors on a date*/");
		hm.getTotalVisitorsOnDate("2017-10-12");
		System.out.println("/*Bill visitor for a room*/");
		hm.billVisitor(visitor1);
		System.out.println("/*Set room as serviced*/");
		hm.repairRoom(room10);
//		hm.showRoomsEmptyOnAParticularDate("2017-10-12");
		
		System.out.println("/*Adding services to a visitor*/");		
		
		hm.addServicesToVisitor(visitor1, service1);
		hm.addServicesToVisitor(visitor1, service4);

		hm.printVisitorServices(visitor1);
		
		System.out.println("/*Sorting visitors rervices - CURRENTLY NOT WORKING*/");
		hm.sortVisitorServicesBy(visitor2, "price");

		System.out.println();

		hm.checkOutVisitorFromARoom(visitor1, room1);
		hm.checkOutVisitorFromARoom(visitor2, room2);
		hm.checkOutVisitorFromARoom(visitor3, room3);
		hm.checkOutVisitorFromARoom(visitor4, room4);
		
		hm.checkInVisitorInARoom(visitor2, room1, "2017-10-16", "2017-10-17");
//		hm.checkOutVisitorFromARoom(visitor2, room1);
//		hm.checkInVisitorInARoom(visitor1, room6, "2017-10-11", "2017-10-12");
//		hm.checkInVisitorInARoom(visitor1, room2, "2017-10-11", "2017-10-13");
//		vm.showAllVisitors("2017-10-12");
//		vm.showAllVisitors("2017-10-15");
//		rm.showRoomDetails(102);
//		hm.billVisitor(visitor1);
		System.out.println("/*Last visitors of a room*/");
		hm.getLastVisitorsOfARoom(room1);
//		System.out.println(room1.getHistories()[2].toString());

	}
}
