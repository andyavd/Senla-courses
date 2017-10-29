package eu.senla.andyavd.hoteladministrator.entities;

import java.time.LocalDate;

import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class Room extends Entity{

	private int id;
	private int roomNumber;
	private int capacity;
	private double dailyPrice;
	private RoomStars stars;
	private RoomStatus status;
	private RoomHistory[] histories = new RoomHistory[10];
//	private int totalRoomVisitors = 0;
//	private Visitor[] latestVisitors;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;

//	@Override
	public int getId() {
		return id;
	}
	
	@Override	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getCapasity() {
		return capacity;
	}

	public void setCapasity(int capacity) {
		this.capacity = capacity;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public RoomStars getStars() {
		return stars;
	}

	public void setStars(RoomStars stars) {
		this.stars = stars;
	}

	public RoomStatus getStatus() {
		return status;
	}

	public void setStatus(RoomStatus status) {
		this.status = status;
	}
	
	public RoomHistory[] getHistories() {
		return histories;
	}

	public void setHistories(RoomHistory[] histories) {
		this.histories = histories;
	}
	
	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
//	public int getTotalRoomVisitors() {
//		return totalRoomVisitors;
//	}
//
//	public void setTotalRoomVisitors(int totalRoomVisitors) {
//		this.totalRoomVisitors = totalRoomVisitors;
//	}
//	
//	public Visitor[] getVisitors() {
//		return visitors;
//	}
//
//	public void setVisitors(Visitor[] visitors) {
//		this.visitors = visitors;
//	}
//	
//	public Visitor[] getLatestVisitors() {
//		return latestVisitors;
//	}
//
//	public void setLatestVisitors(Visitor[] latestVisitors) {
//		this.latestVisitors = latestVisitors;
//	}
	

	// Constructor without visitors
	public Room(int roomNumber, int capacity, double dailyPrice, RoomStars stars, RoomStatus status) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.stars = stars;
		this.status = status;
	}

	// Constructor with visitors
//	public Room(int roomNumber, int capacity, double dailyPrice, RoomStars stars, RoomStatus status, Visitor[] visitors) {
//		this.roomNumber = roomNumber;
//		this.capacity = capacity;
//		this.dailyPrice = dailyPrice;
//		this.stars = stars;
//		this.status = status;
//		this.visitors = visitors;
//	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Room #");
		s.append(roomNumber);
		s.append(" can hold up to ");
		s.append(capacity);
		s.append(" visitors. Rate: ");
		s.append(stars);
		s.append(". Daily price is ");
		s.append(dailyPrice);
		s.append(" USD. ");
		
		if (status == RoomStatus.OCCUPIED) {
			s.append("Room is occupied. ");
//			s.append(visitors[0].toString());
		} else if (status == RoomStatus.SERVICED){
			s.append("Room is being serviced.");
		} else {
			s.append("Room is empty.");
		}
		return s.toString();
	}
}