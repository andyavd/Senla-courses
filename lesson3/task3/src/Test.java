public class Test {

	public static void main(String[] args) {
		
		Flower rose1 = new Rose("Rose", 3.1);
		Flower rose2 = new Rose("Rose", 3.3);
		Flower chrysanthemum = new Chrysanthemum("Chrysanthemum", 2.5);
		Flower alstroemeria = new Alstroemeria("Alstroemeria", 2.9);
		Flower lilly = new Lilly("Lilly", 4.1);
		
		Flower[] bouquet = new Flower[7];
		
		Bouquet b = new Bouquet(bouquet);
		
		b.addFlowerToBouquet(rose1);
		b.addFlowerToBouquet(rose2);
		b.addFlowerToBouquet(chrysanthemum);
		b.addFlowerToBouquet(chrysanthemum);
		b.addFlowerToBouquet(lilly);
		b.addFlowerToBouquet(chrysanthemum);
		b.addFlowerToBouquet(alstroemeria);
		b.addFlowerToBouquet(alstroemeria);
		
		b.bouquetCost();
	}
}
