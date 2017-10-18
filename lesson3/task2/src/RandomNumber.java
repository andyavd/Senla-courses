import java.util.Arrays;
import java.util.Random;

public class RandomNumber {
	
	private int number;
	
	Random random = new Random();
	
	public int createRandomNumber() {
		number = 100 + random.nextInt(999 - 100);
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
		return digitsArray[2];
	}
}
