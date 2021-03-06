package eu.senla.andyavd.hoteladministrator.entities;

public class Service extends Entity {

	private int id = 0;
	private String name;
	private Double dailyPrice;

	@Override
	protected int getId() {
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
		StringBuilder s = new StringBuilder();
		s.append("Servise ");
		s.append(name);
		s.append(" (");
		s.append(dailyPrice);
		s.append(" USD per day.)");
		return s.toString();
	}

	@Override
	public String getEntityParameters() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(name).append(" ").append(dailyPrice));
	}
}
