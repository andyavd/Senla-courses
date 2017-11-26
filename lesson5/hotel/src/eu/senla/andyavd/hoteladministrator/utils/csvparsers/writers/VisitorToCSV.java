package eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.FileParser;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.properties.Settings;

public class VisitorToCSV {

	final static Logger logger = Logger.getLogger(VisitorToCSV.class);
	private static final String VISITOR_CSV = Settings.getInstance().getProperty("visitors");

	public static void writeVisitorsToCSV() {
		try {
			PrintWriter pr = new PrintWriter(VisitorToCSV.class.getClassLoader().getResource(VISITOR_CSV).getFile());
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
