package eu.senla.andyavd.hoteladministrator.model.entities;

public class Visitor extends Entity {

	private int id = 0;
	private String lastName;
	private RoomHistory history;

	@Override
	protected int getId() {
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

	public Visitor(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" Visitor ").append(lastName));
	}
}