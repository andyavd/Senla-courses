package eu.senla.andyavd.hoteladministrator.utils;

import com.danco.training.TextFileWorker;

public class FileReader {

	public static String[] readFromFile(String path) {
		TextFileWorker tfw = new TextFileWorker(path);
		return tfw.readFromFile();
	}
}