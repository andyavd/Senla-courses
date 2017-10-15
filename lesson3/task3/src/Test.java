package task3;

public class Test {

	public static void main(String[] args) {
		
		Rose rose1 = new Rose("Rose", "Naomi", "Red", 3.1, 14);
		Rose rose2 = new Rose("Rose", "Avalanche", "White", 3.3, 14);
		Chrysanthemum chrysanthemum = new Chrysanthemum("Chrysanthemum", "Zembla", "Peach", 2.5, 16);
		Alstroemeria alstroemeria = new Alstroemeria("Alstroemeria", "Emotion", "Purple", 2.9, 12);
		Lilly lilly = new Lilly("Lilly", "Altari", "Cream", 4.1, 12);
		
		Flower[] flowers = {rose1, rose1, rose1, rose2, rose2, rose2, chrysanthemum, chrysanthemum, alstroemeria, alstroemeria, lilly};
		
		Bouquet bouquet = new Bouquet(flowers);
		
		bouquet.bouquetCost();
	}
}
