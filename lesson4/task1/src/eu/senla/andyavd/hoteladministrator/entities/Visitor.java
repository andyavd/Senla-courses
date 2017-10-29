package eu.senla.andyavd.hoteladministrator.entities;

import java.time.LocalDate;

public class Visitor extends Entity{

	private int id=0;
	private String lastName;
	private Room room;
//	private Service[] services;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private RoomHistory[] histories = new RoomHistory[10];
//	private int days;
	private int lastHistoryWithCheckInStatusId;
	private int lastHistoryWithCheckInStatusIdIndex;

	@Override
	protected int getId() {
		return id;
	}
	
	@Override	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getLastHistoryWithCheckInStatusId() {
		return lastHistoryWithCheckInStatusId;
	}

	public void setLastHistoryWithCheckInStatusId(int lastHistoryWithCheckInStatusId) {
		this.lastHistoryWithCheckInStatusId = lastHistoryWithCheckInStatusId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
//	public int getDays() {
//		return days;
//	}
//
//	public void setDays(int days) {
//		this.days = days;
//	}

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
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

//	public Service[] getService() {
//		return services;
//	}
//
//	public void setService(Service[] services) {
//		this.services = services;
//	}

	public Visitor(String lastName) {
		this.lastName = lastName;
	}
	
//	public Visitor(String lastName, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
//		this.lastName = lastName;
//		this.room = room;
//		this.checkInDate = checkInDate;
//		this.checkOutDate = checkOutDate;
//	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Visitor ");
		s.append(lastName);
//		s.append(" checked in Room #");
//		s.append(room.getRoomNumber());
//		s.append(" on ");
		
		if(room != null) {
			s.append(" stayed in a Room #");
			s.append(room.getRoomNumber());
			s.append(". Checkout date is ");
			s.append(checkOutDate);
		}
		
//		
//		s.append(checkOutDate);
//		s.append(". Room occupied for ");
//		s.append(days);
//		s.append(" days.");
		
//		if(services != null) {
//			s.append(". Services are: ");
//			for(int i=0; i<services.length; i++) {
//				s.append(services[i].toString());
//				s.append(", ");
//			}	
//		}
		return s.toString();
	}

	public int getLastHistoryWithCheckInStatusIdIndex() {
		return lastHistoryWithCheckInStatusIdIndex;
	}

	public void setLastHistoryWithCheckInStatusIdIndex(int lastHistoryWithCheckInStatusIdIndex) {
		this.lastHistoryWithCheckInStatusIdIndex = lastHistoryWithCheckInStatusIdIndex;
	}
}