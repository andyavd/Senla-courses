package lesson8.server.hotel.src.eu.senla.andyavd.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import lesson8.server.hotel.src.eu.senla.andyavd.utils.FileParser;
import lesson8.server.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson8.server.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson8.server.properties.src.eu.senla.andyavd.properties.Settings;

public class ServiceToCSV {

	private static final Logger logger = Logger.getLogger(ServiceToCSV.class);
	private static final String SERVICE_CSV = Settings.getInstance().getProperty("services");
	
	public static void writeServicesToCSV() {
		try {
			PrintWriter pr = new PrintWriter(SERVICE_CSV);
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
