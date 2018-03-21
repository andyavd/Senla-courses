package eu.senla.andyavd.hotel.entity.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import eu.senla.andyavd.hotel.annotations.annotations.CsvEntity;
import eu.senla.andyavd.hotel.annotations.annotations.CsvProperty;
import eu.senla.andyavd.hotel.annotations.enums.PropertyType;

@Entity
@Table(name = "room")
@CsvEntity(filename = "data/rooms.csv")
public class Room extends AEntity implements Serializable, Cloneable {

	private static final long serialVersionUID = -7832618903079077100L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id;

	@Column(name = "room_number")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private int roomNumber;

	@Column(name = "capacity")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private Integer capacity;

	@Column(name = "daily_price")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 4)
	private Double dailyPrice;

	@Column(name = "stars")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 5)
	private String stars;

	@Column(name = "room_status")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 6)
	private String status;

	public Room() {
	}

	public Room(int id, int roomNumber, Integer capacity, Double dailyPrice, String stars, String status) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.stars = stars;
		this.status = status;
	}

	public Room(int roomNumber, Integer capacity, Double dailyPrice, String stars) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.stars = stars;
	}

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

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(roomNumber).append(" ").append(capacity)
				.append(" ").append(dailyPrice).append(" ").append(stars).append(" ").append(status));
	}
}
