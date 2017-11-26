package eu.senla.andyavd.hoteladministrator.utils.csvparsers.writers;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.utils.FileParser;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.properties.Settings;

public class RoomToCSV {

	final static Logger logger = Logger.getLogger(RoomToCSV.class);
	private static final String ROOM_CSV = Settings.getInstance().getProperty("rooms");

	public static void writeRoomsToCSV() {
		try {
			PrintWriter pr = new PrintWriter(RoomToCSV.class.getClassLoader().getResource(ROOM_CSV).getFile());
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
