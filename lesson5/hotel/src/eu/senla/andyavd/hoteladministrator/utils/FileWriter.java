package eu.senla.andyavd.hoteladministrator.utils;

import com.danco.training.TextFileWorker;

public class FileWriter extends ClassLoaderContainsClass{
	
	public void writeToFile(String[] values, String path) {
	
		setPathToFile(path);
		TextFileWorker tfw = new TextFileWorker(url.getPath());
		tfw.writeToFile(values);
	}
}
