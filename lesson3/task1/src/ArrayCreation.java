package task1;

public class ArrayCreation {

	private int[] array = { 2, 3, 4, 5, 6 };
	private int sum = 0;

	public int countSum() {
		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}
		return sum;
	}

	public int[] getArray() {
		return array;
	}

	public ArrayCreation() {

	}
}
