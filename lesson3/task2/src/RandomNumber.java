package task2;

import java.util.Arrays;
import java.util.Random;

public class RandomNumber {
	
	private int number;
	
	Random random = new Random();
	
	public int createRandomNumber() {
		number = 100 + random.nextInt(999 - 100);
//		System.out.println("Randomly generated number is: " + number);
		return number;
	}
	
	private int extractOnes() {
		int ones = number%10;
		return ones;
	}
	
	private int extractTens() {
		int tens = ((number-extractOnes())/10)%10;
		return tens;
	}
	
	private int extractHundreds() {
		int hundreds = (int) (number/100);
		return hundreds;
	}
	
	public int sortArray() {
		int[] digitsArray = {extractHundreds(), extractTens(), extractOnes()};
		Arrays.sort(digitsArray);
//		System.out.println("Biggest digit in a randomly generated number is: " + digitsArray[2]);
		return digitsArray[2];
	}
}
