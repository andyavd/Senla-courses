import java.util.Arrays;
import java.util.Random;

public class Test {

	public static void main(String[] args) {
		Random random = new Random();
		int number = 100 + random.nextInt(999 - 100);
		System.out.println("Randomly generated number is: " + number);
		
		int ones = number%10;
		int tens = ((number-ones)/10)%10;
		int hundreds = (int) (number/100);
		
		int[] array = {hundreds, tens, ones};
		Arrays.sort(array);
		
		System.out.println("Biggest digit in a randomly generated number is: " + array[2]);
	}
}
