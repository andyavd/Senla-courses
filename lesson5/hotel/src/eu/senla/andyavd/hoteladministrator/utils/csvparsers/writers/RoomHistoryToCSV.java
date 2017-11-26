package eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.FileParser;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.properties.Settings;

public class RoomHistoryToCSV {

	final static Logger logger = Logger.getLogger(RoomHistoryToCSV.class);
	private static final String HISTORY_CSV = Settings.getInstance().getProperty("roomhistories");
	
	public static void writeHistoriesToCSV() {
		try {
			PrintWriter pr = new PrintWriter(RoomHistoryToCSV.class.getClassLoader().getResource(HISTORY_CSV).getFile());
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
