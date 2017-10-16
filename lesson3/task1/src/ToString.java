package task1;

import java.util.Arrays;

public class ToString {

	ArrayCreation ac = new ArrayCreation();

	void getArrayAsString() {
		String arrToString = Arrays.toString(ac.getArray());
		System.out.println("Array as a String is: " + arrToString);
	}

	void getSumAsString() {
		String sumToString = Integer.toString(ac.countSum());
		System.out.println("Sum as a String is: " + sumToString);
	}

	public ToString() {

	}
}
