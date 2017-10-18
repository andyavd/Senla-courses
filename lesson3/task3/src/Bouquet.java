package task3;

public class Bouquet {

	private Flower[] bouquet;

	public Flower[] getBouquet() {
		return bouquet;
	}

	public void addFlowerToBouquet(Flower flower) {
		for (int i = 0; i < bouquet.length; i++) {
			if (bouquet[i] == null) {
				bouquet[i] = flower;
				break;
			}
			if (bouquet[bouquet.length - 1] != null) {
				System.out.println("Too much flowers in the bouquet. You'll see the price of the first "
						+ bouquet.length + " flowers.");
				break;
			}
		}
	}

	public void bouquetCost() {
		double totalPrice = 0;
		for (int i = 0; i < bouquet.length; i++) {
			if (bouquet[i] != null) {
				totalPrice = totalPrice + bouquet[i].getPrice();
			}
		}
		System.out.println("Total price of the bouquet is " + totalPrice);
	}

	public Bouquet(Flower[] bouquet) {
		this.bouquet = bouquet;
	}
}