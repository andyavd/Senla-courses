public class Chrysanthemum extends Flower {

	private int daysOfLife;

	public int getDaysOfLife() {
		return daysOfLife;
	}

	public void setDaysOfLife(int daysOfLife) {
		this.daysOfLife = daysOfLife;
	}

	public Chrysanthemum(String group, String sort, String color, double price, int daysOfLife) {
		super(group, sort, color, price);
		this.daysOfLife = daysOfLife;
	}
}
