package task3;

public class Rose extends Flower {

	private int daysOfLife;

	public int getDaysOfLife() {
		return daysOfLife;
	}

	public void setDaysOfLife(int daysOfLife) {
		this.daysOfLife = daysOfLife;
	}

	public Rose(String group, String sort, String color, double price, int daysOfLife) {
		super(group, sort, color, price);
		this.daysOfLife = daysOfLife;
	}
}
