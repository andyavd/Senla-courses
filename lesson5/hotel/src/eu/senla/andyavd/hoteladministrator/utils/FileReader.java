package eu.senla.andyavd.hoteladministrator.utils;

import com.danco.training.TextFileWorker;

public class FileReader extends ClassLoaderContainsClass {

	public String[] readFromFile(String path) {

		setPathToFile(path);
		TextFileWorker tfw = new TextFileWorker(url.getPath());
		return tfw.readFromFile();
	}
}
