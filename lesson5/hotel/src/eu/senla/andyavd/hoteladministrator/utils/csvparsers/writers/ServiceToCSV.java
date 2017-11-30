package eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.FileParser;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.properties.Settings;

public class ServiceToCSV {

	private static final Logger logger = Logger.getLogger(ServiceToCSV.class);
	private static final String SERVICE_CSV = Settings.getInstance().getProperty("services");
	
	public static void writeServicesToCSV() {
		try {
			PrintWriter pr = new PrintWriter(ServiceToCSV.class.getClassLoader().getResource(SERVICE_CSV).getFile());
			String[] array = FileParser.servicesToString(HotelManager.getInstance().getServices());
			pr.println("Service_ID,Service_name,Service_Price");
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
