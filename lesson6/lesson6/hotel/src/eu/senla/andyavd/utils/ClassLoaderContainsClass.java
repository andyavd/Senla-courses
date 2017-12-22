package lesson6.hotel.src.eu.senla.andyavd.utils;

import java.net.URL;

public abstract class ClassLoaderContainsClass {
	
	ClassLoader cl = this.getClass().getClassLoader();
	URL url;
	 
	public void setPathToFile(String path) {
		url = cl.getResource(path);
	}
}