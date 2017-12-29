package eu.senla.andyavd.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import eu.senla.andyavd.utils.FileParser;
import eu.senla.andyavd.utils.Printer;
import eu.senla.andyavd.view.HotelManager;
import eu.senla.andyavd.properties.Settings;

public class VisitorToCSV {

	private static final Logger logger = Logger.getLogger(VisitorToCSV.class);
	private static final String VISITOR_CSV = Settings.getInstance().getProperty("visitors");

	public static void writeVisitorsToCSV() {
		try {
			PrintWriter pr = new PrintWriter(VISITOR_CSV);
			String[] array = FileParser.visitorsToString(HotelManager.getInstance().getVisitors());
			pr.println("Visitor_ID,Visitor_LastName,Room_ID,CheckIn_Date,CheckOut_Date");
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
