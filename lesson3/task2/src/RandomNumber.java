import java.util.Arrays;
import java.util.Random;

import task4.Printer;

public class RandomNumber {

	Printer printer = new Printer();
	Random random = new Random();

	private int number;

	public void createRandomNumber() {
		number = 100 + random.nextInt(999 - 100);

		StringBuilder s = new StringBuilder();
		s.append("Randomly generated number is: ");
		s.append(number);
		Printer.print(s.toString());
	}

	private int extractOnes() {
		int ones = number % 10;
		return ones;
	}

	private int extractTens() {
		int tens = ((number - extractOnes()) / 10) % 10;
		return tens;
	}

	private int extractHundreds() {
		int hundreds = (int) (number / 100);
		return hundreds;
	}

	public void sortArray() {
		int[] digitsArray = { extractHundreds(), extractTens(), extractOnes() };
		Arrays.sort(digitsArray);

		StringBuilder s = new StringBuilder();
		s.append("Biggest digit in a randomly generated number is: ");
		s.append(digitsArray[2]);
		Printer.print(s.toString());
	}
}
