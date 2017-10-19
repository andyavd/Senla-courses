import task4.Printer;

public class Bouquet {

	Printer printer = new Printer();
	
	private Flower[] bouquet;

	public void addFlowerToBouquet(Flower flower) {
		if (bouquet[bouquet.length - 1] != null) {
			
			StringBuilder s = new StringBuilder();
			s.append("Too much flowers in the bouquet. You'll see the price of the first ");
			s.append(bouquet.length);
			s.append(" flowers.");
			
			Printer.print(s.toString());
		}
		for (int i = 0; i < bouquet.length; i++) {
			if (bouquet[i] == null) {
				bouquet[i] = flower;
				return;
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
		
		StringBuilder s = new StringBuilder();
		s.append("Total price of the bouquet is ");
		s.append(totalPrice);
		
		Printer.print(s.toString());
	}

	public Bouquet(Flower[] bouquet) {
		this.bouquet = bouquet;
	}
}
