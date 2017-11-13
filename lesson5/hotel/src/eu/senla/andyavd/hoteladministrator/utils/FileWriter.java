package eu.senla.andyavd.hoteladministrator.utils;

import com.danco.training.TextFileWorker;

public class FileWriter {
	public static void writeToFile(String[] values, String path) {
		TextFileWorker tfw = new TextFileWorker(path);
		tfw.writeToFile(values);
	}
}
