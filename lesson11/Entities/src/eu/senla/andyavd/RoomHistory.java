package eu.senla.andyavd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;
import eu.senla.andyavd.enums.PropertyType;

@CsvEntity(filename = "data/roomHistories.csv")
public class RoomHistory extends AEntity implements Serializable{

	private static final long serialVersionUID = 8310876147491569183L;
	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;
	private int visitorId;
	private int roomId;
	private Date checkInDate;
	private Date checkOutDate;
	private List<Service> services = new ArrayList<Service>();
	private String status;

	public RoomHistory() {
	}
	public RoomHistory(int visitorId, int roomId, Date checkInDate, Date checkOutDate) {
		this.visitorId = visitorId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	@Override
	public int getId() {
		return id;
	}
	@Override
	public void setId(int id) {
		this.id = id;
	}
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
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
			s.append(roomId);
			s.append(", Visitor: ");
			s.append(visitorId);
			s.append(" has checked-in. Check-in date: ");
			s.append(checkInDate);
			s.append(". Check-out date: ");
			s.append(checkOutDate);
			s.append(status);
		} else {
			s.append("History #");
			s.append(id);
			s.append(" : Room ");
			s.append(roomId);
			s.append(", Visitor ");
			s.append(visitorId);
			s.append(" has checked-out.");
			s.append(status);
		}
		return s.toString();
	}
}