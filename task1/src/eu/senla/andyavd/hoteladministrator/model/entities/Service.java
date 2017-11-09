package eu.senla.andyavd.hoteladministrator.model.entities;

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
		return String.valueOf(new StringBuilder().append(id).append(" Service ").append(name).append(", DailyPrice ").append(dailyPrice));
	}
}