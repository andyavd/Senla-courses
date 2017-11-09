package eu.senla.andyavd.hoteladministrator.model.entities;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class Room extends Entity {

	private int id;
	private int roomNumber;
	private Integer capacity;
	private Double dailyPrice;
	private RoomStars stars;
	private RoomStatus status;
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

	public Room(int roomNumber, Integer capacity, Double dailyPrice, RoomStars stars, RoomStatus status) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.stars = stars;
		this.status = status;
	}

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" Room#").append(roomNumber).append(", Capacity ").append(capacity)
				.append(", Rate ").append(stars).append(", DailyPrice ").append(dailyPrice).append(", Status ").append(status));
	}
}