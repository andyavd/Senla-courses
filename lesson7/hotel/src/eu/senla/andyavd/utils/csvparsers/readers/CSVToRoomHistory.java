package eu.senla.andyavd.utils.csvparsers.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import eu.senla.andyavd.properties.Settings;

public class CSVToRoomHistory {
	private static final Logger logger = Logger.getLogger(CSVToRoomHistory.class);
	private static final String HISTORY_CSV = Settings.getInstance().getProperty("roomhistories");
	String line = "";
	String cvsSplitBy = ",";
	static String[] array;
	
	public static String[] readHistories() {
		try {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(new File(HISTORY_CSV));
			List<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) {
			  lines.add(sc.nextLine().replaceAll("\"", ""));
			  
			}
			array = lines.toArray(new String[0]);

        } catch (IOException e) {
        		logger.error("Failed to read from the file!", e);
        }
		
		return array;

	}
}
