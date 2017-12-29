package eu.senla.andyavd.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;
import eu.senla.andyavd.enums.PropertyType;
import eu.senla.andyavd.enums.RoomStars;
import eu.senla.andyavd.enums.RoomStatus;

@CsvEntity(filename = "data/rooms.csv")
public class Room extends AEntity implements Serializable, Cloneable{

	private static final long serialVersionUID = -7832618903079077100L;
	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private int roomNumber;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private Integer capacity;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private Double dailyPrice;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	private RoomStars stars;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
	private RoomStatus status;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 7)
	private List <RoomHistory> histories = new ArrayList<RoomHistory>();

	@Override
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

	public Integer getCapasity() {
		return capacity;
	}

	public void setCapasity(Integer capacity) {
		this.capacity = capacity;
	}

	public Double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(Double dailyPrice) {
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

	public List<RoomHistory> getHistories() {
		return histories;
	}

	public void setHistories(List <RoomHistory> histories) {
		this.histories = histories;
	}

	public Room() {

	}
	
	public Room(int roomNumber, Integer capacity, Double dailyPrice, RoomStars stars, RoomStatus status) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.stars = stars;
		this.status = status;
	}
	
	public Room clone() throws CloneNotSupportedException {
        Room cloneRoom = (Room)super.clone();
        cloneRoom.setRoomNumber(0);
        cloneRoom.setId(0);
		return cloneRoom;
  }

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(roomNumber).append(" ").append(capacity)
				.append(" ").append(dailyPrice).append(" ").append(stars).append(" ").append(status));
	}
}
