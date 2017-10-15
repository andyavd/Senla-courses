public class Bouquet {

	private Flower[] flowers;

	public Flower[] getFlowers() {
		return flowers;
	}

	public void setFlowers(Flower[] flowers) {
		this.flowers = flowers;
	}
	
	public Bouquet(Flower[] flowers) {
		this.flowers = flowers;
	}

	public void bouquetCost() {
		double totalPrice = 0;
		for(int i=0; i<flowers.length; i++) {
			totalPrice = totalPrice + flowers[i].getPrice();
		}
		System.out.println("Total price of the bouquet is " + totalPrice);
	}
}
