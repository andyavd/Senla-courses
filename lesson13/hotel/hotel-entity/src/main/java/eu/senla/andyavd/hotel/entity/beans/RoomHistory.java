package eu.senla.andyavd.hotel.entity.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import eu.senla.andyavd.hotel.annotations.annotations.CsvEntity;
import eu.senla.andyavd.hotel.annotations.annotations.CsvProperty;
import eu.senla.andyavd.hotel.annotations.enums.PropertyType;

@Entity
@Table(name = "history")
@CsvEntity(filename = "data/roomHistories.csv")
public class RoomHistory extends AEntity implements Serializable {

	private static final long serialVersionUID = 8310876147491569183L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;

	@Column(name = "visitor_id")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private int visitorId;

	@Column(name = "room_id")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private int roomId;

	@Column(name = "check_in")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private Date checkInDate;

	@Column(name = "check_out")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	private Date checkOutDate;

	@Column(name = "history_status")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
	private String status;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "visitor_service", joinColumns = { @JoinColumn(name = "history_id") }, inverseJoinColumns = {
			@JoinColumn(name = "service_id") })
	private List<Service> services;

	public RoomHistory() {
	}

	public RoomHistory(int visitorId, int roomId, Date checkInDate, Date checkOutDate) {
		this.visitorId = visitorId;
		this.roomId = roomId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		services = new ArrayList<Service>();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
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