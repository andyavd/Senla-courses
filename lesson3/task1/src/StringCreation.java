import java.util.Arrays;

public class StringCreation {

	ArrayCreation ac = new ArrayCreation();

	public void getArrayAsString() {
		String createStringFromArray = Arrays.toString(ac.getArray());
		System.out.println("Array as a String is: " + createStringFromArray);
	}

	public void getSumAsString() {
		String createStringFromSum = Integer.toString(ac.countSum());
		System.out.println("Sum as a String is: " + createStringFromSum);
	}

	public StringCreation() {

	}
}
