package task2;

public class Printer {
	RandomNumber rn = new RandomNumber();
	
	public void randomlyGeneratedNumber() {
		System.out.println("Randomly generated number is: " + rn.createRandomNumber());
	}
	public void biggestDigitInRandomlyGeneratedNumber() {
		System.out.println("Biggest digit in a randomly generated number is: " + rn.sortArray());
	}
}
