package lesson6.hotel.src.eu.senla.andyavd.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.utils.FileParser;
import lesson6.hotel.src.eu.senla.andyavd.utils.Printer;
import lesson6.hotel.src.eu.senla.andyavd.view.HotelManager;
import lesson6.properties.src.eu.senla.andyavd.properties.Settings;

public class RoomToCSV {

	private static final Logger logger = Logger.getLogger(RoomToCSV.class);
	private static final String ROOM_CSV = Settings.getInstance().getProperty("rooms");

	public static void writeRoomsToCSV() {
		try {
			PrintWriter pr = new PrintWriter(ROOM_CSV);
			String[] array = FileParser.roomsToString(HotelManager.getInstance().getRooms());
			pr.println("Room_ID,Room_Number,Room_Capacity,Room_Price,Room_Stars,Room_Status,Room_LasHistory_ID");
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
