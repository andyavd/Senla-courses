package eu.senla.andyavd.hoteladministrator.entities;

public class Service extends Entity{
	
	private int id=0;
	private String name;
	private double dailyPrice;
	
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
	public double getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	
	public Service() {
		
	}
	
	public Service(String name, double dailyPrice) {
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
}