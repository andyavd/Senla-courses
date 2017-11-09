package eu.senla.andyavd.hoteladministrator.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.enums.RoomHistoryStatus;

public class RoomHistory extends Entity {

	private int id = 0;
	private Room room;
	private Visitor visitor;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private List<Entity> services = new ArrayList<Entity>();
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

	public List<Entity> getService() {
		return services;
	}

	public void setService(List<Entity> services) {
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
			s.append(status);
		} else {
			s.append("History #");
			s.append(id);
			s.append(" : Room ");
			s.append(room.getRoomNumber());
			s.append(", Visitor ");
			s.append(visitor.getLastName());
			s.append(" has checked-out.");
			s.append(status);
		}

		return s.toString();
	}
}
