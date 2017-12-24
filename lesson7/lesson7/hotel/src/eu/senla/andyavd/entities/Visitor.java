package lesson7.hotel.src.eu.senla.andyavd.entities;

import java.io.Serializable;

import lesson7.annotations.src.eu.senla.andyavd.annotations.CsvEntity;
import lesson7.annotations.src.eu.senla.andyavd.annotations.CsvProperty;
import lesson7.hotel.src.eu.senla.andyavd.enums.PropertyType;

@CsvEntity(filename = "data/visitors.csv")
public class Visitor extends AEntity implements Serializable{

	private static final long serialVersionUID = -5868446232504296194L;
	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String lastName;
	private RoomHistory history;

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public RoomHistory getHistory() {
		return history;
	}

	public void setHistory(RoomHistory history) {
		this.history = history;
	}

	public Visitor() {

	}

	public Visitor(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(lastName));
	}
}
