package eu.senla.andyavd;

import java.io.Serializable;

import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;
import eu.senla.andyavd.enums.PropertyType;

@CsvEntity(filename = "data/services.cs")
public class Service extends AEntity implements Serializable{

	private static final long serialVersionUID = 7837575998552612234L;

	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String name;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 3)
	private Double dailyPrice;

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

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(name).append(" ").append(dailyPrice));
	}
}
