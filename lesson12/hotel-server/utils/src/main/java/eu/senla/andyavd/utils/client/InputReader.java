package eu.senla.andyavd.utils.client;

import java.time.LocalDate;
import java.util.Scanner;

import eu.senla.andyavd.utils.common.Printer;

public class InputReader {

	public static Integer getIntegerInput(Scanner scanner, String message) {
		Printer.print(message);
		return getIntegerInput(scanner);
	}

	public static Integer getIntegerInput(Scanner scanner) {
		boolean isValid = false;
		Integer input = null;

		while (!isValid) {
			if (scanner.hasNextInt()) {
				input = scanner.nextInt();
				isValid = true;
			} else {
				Printer.print("Wrong input. Please, input a number.");
				scanner.nextLine();
				continue;
			}
		}
		return input;
	}

	public static String getStringInput(Scanner scanner, String message) {
		Printer.print(message);
		return getStringInput(scanner);
	}

	public static String getStringInput(Scanner scanner) {

		String input = scanner.nextLine();

		return input;
	}

	public static Double getDoubleInput(Scanner scanner, String message) {
		Printer.print(message);
		return getDoubleInput(scanner);
	}

	public static Double getDoubleInput(Scanner scanner) {
		boolean isValid = false;
		Double input = null;

		while (!isValid) {
			if (scanner.hasNextDouble()) {
				input = scanner.nextDouble();
				isValid = true;

			} else {
				Printer.print("Wrong input. Please, input a double.");
				scanner.nextLine();
				continue;
			}
		}
		return input;
	}

	public static LocalDate getLocalDateInput(Scanner scanner, String message) {
		Printer.print(message);

		return getLocalDateInput(scanner);
	}

	private static LocalDate getLocalDateInput(Scanner scanner) {
		boolean isValid = false;
		String input;
		LocalDate date = null;
		while (!isValid) {
			if (scanner.hasNext()) {
				input = scanner.next();
				date = LocalDate.parse(input);
				isValid = true;
			} else {
				Printer.print("Wrong input. Please, input the date in \"YYYY-MM-DD\" format.");
				scanner.nextLine();
				continue;
			}
		}
		return date;
	}
}
