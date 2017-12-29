package eu.senla.andyavd.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.FileParser;
import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.properties.Settings;

public class RoomHistoryToCSV {

	private static final Logger logger = Logger.getLogger(RoomHistoryToCSV.class);
	private static final String HISTORY_CSV = Settings.getInstance().getProperty("roomhistories");
	
	public static void writeHistoriesToCSV() {
		try {
			PrintWriter pr = new PrintWriter(HISTORY_CSV);
			String[] array = FileParser.roomHistoriesToString(HotelManager.getInstance().getHistories());
			pr.println("History_ID,Room_ID,Visitor_ID,CheckIn_Date,CheckOut_Date,History_Status");
			for (int i = 0; i < array.length; i++) {
				pr.println(array[i]);
			}
			Printer.print("ok");
			pr.close();
		} catch (Exception e) {
			logger.error("No such file!", e);
		}
	}
}
