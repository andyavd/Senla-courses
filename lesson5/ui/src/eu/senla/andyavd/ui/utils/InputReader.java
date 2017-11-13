package eu.senla.andyavd.ui.utils;

import java.time.LocalDate;
import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.enums.RoomStars;
import eu.senla.andyavd.hoteladministrator.enums.RoomStatus;
import eu.senla.andyavd.hoteladministrator.utils.Printer;

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

	@SuppressWarnings("unused")
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
	
	public static RoomStars getStarsInput(Scanner scanner, String message) {
		Printer.print(message);

		return getStarsInput(scanner);
	}

	private static RoomStars getStarsInput(Scanner scanner) {
		boolean isValid = false;
		Integer input;
		RoomStars star = null;
		while (!isValid) {
			if (scanner.hasNextInt()) {
				input = scanner.nextInt();
	
				switch (input) {
				case 1:
					star = RoomStars.STANDARD;
					break;
				case 2:
					star = RoomStars.JUNIOR_SUITE;
					break;
				case 3:
					star = RoomStars.LUX;
					break;
				case 4:
					star = RoomStars.PRESIDENT_LUX;
					break;
				default:
					Printer.print("Wrong input. Please, input a number from 1 to 4.");
					scanner.nextLine();
					continue;
				}
				isValid = true;
			} else {
				Printer.print("Wrong input. Please, input a number from 1 to 4.");
				scanner.nextLine();
				continue;
			}
		}
		return star;
	}
	
	public static RoomStatus getStatusInput(Scanner scanner, String message) {
		Printer.print(message);

		return getStatusInput(scanner);
	}

	private static RoomStatus getStatusInput(Scanner scanner) {
		boolean isValid = false;
		String input;
		RoomStatus status = null;
		while (!isValid) {
			if (scanner.hasNext()) {
				input = scanner.next();
	
				switch (input) {
				case "empty":
					status = RoomStatus.EMPTY;
					break;
				case "occupied":
					status = RoomStatus.OCCUPIED;
					break;
				case "serviced":
					status = RoomStatus.SERVICED;
					break;
				default:Printer.print("Wrong input. Please, input a \"empty\", \"occupied\" or \"serviced\".");
				scanner.nextLine();
				continue;
				}
				isValid = true;
			}
		}
		return status;
	}
}