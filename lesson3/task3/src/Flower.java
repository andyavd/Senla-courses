public abstract class Flower {
	private String name;
	private double price;

	public String getGroup() {
		return name;
	}

	public void setGroup(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Flower(String name, double price) {
		this.name = name;
		this.price = price;
	}
}
