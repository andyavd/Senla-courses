package eu.senla.andyavd;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;
import eu.senla.andyavd.enums.PropertyType;

@CsvEntity(filename = "data/roomHistories.csv")
public class RoomHistory extends AEntity implements Serializable{

	private static final long serialVersionUID = 8310876147491569183L;
	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;
	private Room room;
	private Visitor visitor;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private List<Service> services = new ArrayList<Service>();
	private String status;

	public RoomHistory() {
	}
	
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
	public List<Service> getService() {
		return services;
	}
	public void setService(List<Service> services) {
		this.services = services;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (status == "CheckIn") {
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
