package eu.senla.andyavd.hoteladministrator.entities;

import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;

public class Room extends Entity{

	private int id;
	private int roomNumber;
	private Integer capacity;
	private Double dailyPrice;
	private RoomStars stars;
	private RoomStatus status;
	private RoomHistory[] histories = new RoomHistory[10];


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
	
	public RoomHistory[] getHistories() {
		return histories;
	}

	public void setHistories(RoomHistory[] histories) {
		this.histories = histories;
	}
		
	public Room(int roomNumber, Integer capacity, Double dailyPrice, RoomStars stars, RoomStatus status) {
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
		this.stars = stars;
		this.status = status;
	}
	
	public Room(int id, int roomNumber, Integer capacity, Double dailyPrice) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
	}

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
		} else if (status == RoomStatus.SERVICED){
			s.append("Room is being serviced.");
		} else {
			s.append("Room is empty.");
		}
		return s.toString();
	}
	
	public String getRoomParameters() {
		return String.valueOf(new StringBuilder().append(id)
				.append(" ").append(roomNumber)
				.append(" ").append(capacity)
				.append(" ").append(stars)
				.append(" ").append(dailyPrice));
	}
}
