package task3;

public abstract class Flower {
	private String name;
	private String color;
	private double price;

	public String getGroup() {
		return name;
	}

	public void setGroup(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Flower(String name, String color, double price) {
		this.name = name;
		this.color = color;
		this.price = price;
	}
}
