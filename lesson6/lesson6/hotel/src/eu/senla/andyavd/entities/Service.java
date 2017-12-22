package lesson6.hotel.src.eu.senla.andyavd.entities;

import java.io.Serializable;

public class Service extends AEntity implements Serializable{

	private static final long serialVersionUID = 7837575998552612234L;

	private int id = 0;
	private String name;
	private Double dailyPrice;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(Double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public Service() {

	}

	public Service(String name, Double dailyPrice) {
		this.name = name;
		this.dailyPrice = dailyPrice;
	}

	public Service(Integer id, String name, double dailyPrice) {
		this.id = id;
		this.name = name;
		this.dailyPrice = dailyPrice;
	}

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(name).append(" ").append(dailyPrice));
	}
}
