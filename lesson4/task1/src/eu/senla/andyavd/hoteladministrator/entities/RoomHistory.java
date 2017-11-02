package eu.senla.andyavd.hoteladministrator.entities;

import java.time.LocalDate;

import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;


public class RoomHistory extends Entity {

	private int id = 0;
	private Room room;
	private Visitor visitor;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Service[] services = new Service[10];
	private RoomHistoryStatus status;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
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

	public Service[] getService() {
		return services;
	}

	public void setService(Service[] services) {
		this.services = services;
	}

	public RoomHistoryStatus getStatus() {
		return status;
	}

	public void setStatus(RoomHistoryStatus status) {
		this.status = status;
	}

	public RoomHistory() {

	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (status == RoomHistoryStatus.CHECKIN) {
			s.append("History #");
			s.append(id);
			s.append(" : Room ");
			s.append(room.getRoomNumber());
			s.append(", Visitor: ");
			s.append(visitor.getLastName());
			s.append(" has checked-in. Check-in date: ");
			s.append(checkInDate);
			s.append(". Check-out date: ");
			s.append(checkOutDate);
		} else {
			s.append("History #");
			s.append(id);
			s.append(" : Room ");
			s.append(room.getRoomNumber());
			s.append(", Visitor: ");
			s.append(visitor.getLastName());
			s.append(" has checked-out.");
		}
		
		return s.toString();
	}
}
