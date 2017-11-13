package eu.senla.andyavd.ui.entityactions.room;

import java.time.LocalDate;
import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class PrintEmptyRoomsOnDate implements IAction {

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);

		LocalDate localDate = InputReader.getLocalDateInput(scanner, "Input the date like \"YYYY-MM-DD\"...");

		Printer.printList(HotelManager.getInstance().getEmptyRoomsOnDate(localDate));

	}
}