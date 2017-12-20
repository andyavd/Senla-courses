package eu.senla.andyavd.hoteladministrator.entities;

import java.io.Serializable;

import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;

@CsvEntity(filename = "data/services.cs")
public class Service extends AEntity implements Serializable{

	private static final long serialVersionUID = 7837575998552612234L;
	
	@CsvProperty(columnNumber = 0)
	private int id = 0;
	@CsvProperty(columnNumber = 1)
	private String name;
	@CsvProperty(columnNumber = 2)
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
