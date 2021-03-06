package eu.senla.andyavd.hoteladministrator.entities;

import java.io.Serializable;

import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;

@CsvEntity(filename = "data/visitors.csv")
public class Visitor extends AEntity implements Serializable{

	private static final long serialVersionUID = -5868446232504296194L;

	@CsvProperty(columnNumber = 0)
	private int id = 0;
	@CsvProperty(columnNumber = 1)
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
