import java.util.Arrays;

public class StringCreation {

	public void getArrayAsString(int[] array) {
		String createStringFromArray = Arrays.toString(array);
		System.out.println("Array as a String is: " + createStringFromArray);
	}

	public void getSumAsString(int[] array) {

		int sum = 0;

		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}

		String createStringFromSum = Integer.toString(sum);
		System.out.println("Sum as a String is: " + createStringFromSum);
	}

}
