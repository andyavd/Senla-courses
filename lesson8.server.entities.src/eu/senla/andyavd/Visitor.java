package eu.senla.andyavd;

import java.io.Serializable;

import eu.senla.andyavd.CsvEntity;
import eu.senla.andyavd.CsvProperty;
import eu.senla.andyavd.enums.PropertyType;

@CsvEntity(filename = "data/visitors.csv")
public class Visitor extends AEntity implements Serializable{

	private static final long serialVersionUID = -5868446232504296194L;
	
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String lastName;
	private RoomHistory history;

	public Visitor() {
	}

	public Visitor(String lastName) {
		this.lastName = lastName;
	}
	
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

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(lastName));
	}
}
