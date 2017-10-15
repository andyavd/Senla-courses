import java.util.Arrays;

public class Test {

	public static void main(String args[]) {

		int[] array = { 2, 3, 4, 5, 6 };
		int sum = 0;

		String arrToString = Arrays.toString(array);

		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}

		String sumToString = Integer.toString(sum);

		System.out.println("Array as a String is: " + arrToString);
		System.out.println("Sum as a String is: " + sumToString);
		
	}
}
