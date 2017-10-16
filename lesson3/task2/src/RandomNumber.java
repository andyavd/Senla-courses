import java.util.Arrays;
import java.util.Random;

public class RandomNumber {
	
	private int number;
	
	Random random = new Random();
	
	public void createRandomNumber() {
		number = 100 + random.nextInt(999 - 100);
		System.out.println("Randomly generated number is: " + number);
	}
	
	public int extractOnes() {
		int ones = number%10;
		return ones;
	}
	
	public int extractTens() {
		int tens = ((number-extractOnes())/10)%10;
		return tens;
	}
	
	public int extractHundreds() {
		int hundreds = (int) (number/100);
		return hundreds;
	}
	
	public void sortArray() {
		int[] digitsArray = {extractHundreds(), extractTens(), extractOnes()};
		Arrays.sort(digitsArray);
		System.out.println("Biggest digit in a randomly generated number is: " + digitsArray[2]);
	}

	public RandomNumber() {
	}
}
