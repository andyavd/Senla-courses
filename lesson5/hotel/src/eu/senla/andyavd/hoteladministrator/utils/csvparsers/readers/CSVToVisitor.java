package eu.senla.andyavd.hoteladministrator.utils.csvparsers.readers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import eu.senla.andyavd.properties.Settings;

public class CSVToVisitor {
	private static final String VISITOR_CSV = Settings.getInstance().getProperty("visitors");
	String line = "";
	String cvsSplitBy = ",";
	static String[] array;
	
	public static String[] readVisitors() {
		try (Scanner sc = new Scanner(new File(CSVToVisitor.class.getClassLoader().getResource(VISITOR_CSV).getFile()))) {
			
			List<String> lines = new ArrayList<String>();
			while (sc.hasNextLine()) {
			  lines.add(sc.nextLine().replaceAll("\"", ""));
			  
			}
			array = lines.toArray(new String[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return array;

	}
}
