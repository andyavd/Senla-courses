package eu.senla.andyavd.hoteladministrator;
import java.text.ParseException;
import java.time.LocalDate;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.managers.HotelManager;

public class Test {

	public static void main(String[] args) throws ParseException {
		
		HotelManager hotelManager = new HotelManager();
		
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
		
		hotelManager.createRoom(room1);
		hotelManager.createRoom(room2);
		hotelManager.createRoom(room3);
		hotelManager.createRoom(room4);
		hotelManager.createRoom(room5);
		hotelManager.createRoom(room6);
		hotelManager.createRoom(room7);
		hotelManager.createRoom(room8);
		hotelManager.createRoom(room9);
		hotelManager.createRoom(room10);
		
		System.out.println("/*Show all rooms*/");
		hotelManager.printRooms();
		System.out.println("/*Show all empty rooms*/");
		hotelManager.printEmptyRooms();
		System.out.println("/*Show empty rooms numbers*/");
		hotelManager.printEmptyRoomsNumber();
		System.out.println("/*Sort rooms*/");
		hotelManager.sortRoomsByCapacity();
		System.out.println("price");
		hotelManager.sortRoomsByPrice();
		System.out.println("stars");
		hotelManager.sortRoomsByStars();
		
		System.out.println("/*Sort empty rooms*/");
		hotelManager.sortEmptyRoomsByCapacity();
		System.out.println("/*price*/");
		hotelManager.sortEmptyRoomsByPrice();
		System.out.println("/*stars*/");
		hotelManager.sortEmptyRoomsByStars();
		System.out.println("/*Show room datails*/");
		hotelManager.printDetailsOfRoom(room6);
				
		Visitor visitor1 = new Visitor("Marsh");
		Visitor visitor2 = new Visitor("Broflovski");
		Visitor visitor3 = new Visitor("McCormick");
		Visitor visitor4 = new Visitor("Cartman");
		Visitor visitor5 = new Visitor("Stotch");
		
		System.out.println("/*Add visitors*/");
		hotelManager.receiveVisitor(visitor1);
		hotelManager.receiveVisitor(visitor2);
		hotelManager.receiveVisitor(visitor3);
		hotelManager.receiveVisitor(visitor4);
		hotelManager.receiveVisitor(visitor5);
		
		System.out.println("/*Show visitors*/");
		hotelManager.printVisitors();
		System.out.println();
		hotelManager.sortVisitorsByName();
		
		Service service1 = new Service("Laundry", 8.6);
		Service service2 = new Service("Ironing", 2.3);
		Service service3 = new Service("Minibar", 10.4);
		Service service4 = new Service("Wake-up call", 1.4);
		
		System.out.println("/*Add services */");
		hotelManager.createService(service1);
		hotelManager.createService(service2);
		hotelManager.createService(service3);
		hotelManager.createService(service4);
		
		System.out.println("/*Show services */");
		hotelManager.printServices();
		System.out.println("/*Sort services */");
		hotelManager.sortServicesByName();
		System.out.println();
		hotelManager.sortServicesByPrice();
		System.out.println();

		System.out.println("/*Check-in visitors*/");
		hotelManager.checkInVisitorInRoom(visitor1, room1, LocalDate.of(2017,10,11), LocalDate.of(2017,10,15));
		hotelManager.checkInVisitorInRoom(visitor2, room2, LocalDate.of(2017,10,11), LocalDate.of(2017,10,13));
		hotelManager.checkInVisitorInRoom(visitor3, room3, LocalDate.of(2017,10,11), LocalDate.of(2017,10,14));
		hotelManager.checkInVisitorInRoom(visitor4, room4, LocalDate.of(2017,10,11), LocalDate.of(2017,10,15));
		hotelManager.checkInVisitorInRoom(visitor5, room9, LocalDate.of(2017,10,11), LocalDate.of(2017,10,14));
		
		System.out.println("/*Show empty rooms on a date*/");
		hotelManager.showEmptyRoomsOnDate(LocalDate.of(2017, 10, 12));
		
		System.out.println("/*Show all visitors on a date*/");
		hotelManager.printTotalVisitorsOnDate(LocalDate.of(2017, 10, 12));
							
		System.out.println("/*Bill visitor for a room*/");
		hotelManager.billVisitor(visitor1);
		System.out.println("/*Set room as serviced*/");
		hotelManager.changeRoomStatus(room8);
		
		System.out.println("/*Adding services to a visitor*/");		
		
		hotelManager.addServicesToVisitor(visitor1, service1);
		hotelManager.addServicesToVisitor(visitor1, service4);

		hotelManager.printVisitorServices(visitor1);
		
		System.out.println("/*Sorting visitors rervices*/");
		hotelManager.sortVisitorServicesByPrice(visitor1);

		System.out.println();

		hotelManager.checkOutVisitorFromRoom(visitor1, room1);
		hotelManager.checkOutVisitorFromRoom(visitor2, room2);
		hotelManager.checkOutVisitorFromRoom(visitor3, room3);
		hotelManager.checkOutVisitorFromRoom(visitor4, room4);
		
		hotelManager.checkInVisitorInRoom(visitor2, room1, LocalDate.of(2017,10,16), LocalDate.of(2017,10,17));
		hotelManager.checkOutVisitorFromRoom(visitor2, room1);
		hotelManager.checkInVisitorInRoom(visitor3, room1, LocalDate.of(2017,10,18), LocalDate.of(2017,10,20));
		hotelManager.checkOutVisitorFromRoom(visitor3, room1);
		hotelManager.checkInVisitorInRoom(visitor5, room1, LocalDate.of(2017,10,21), LocalDate.of(2017,10,23));
		hotelManager.checkOutVisitorFromRoom(visitor5, room1);

		System.out.println("/*Last visitors of a room*/");
		hotelManager.getLastVisitorsOfRoom(room1);


		hotelManager.saveToFile();
		hotelManager.loadFromFile();
	}
}
