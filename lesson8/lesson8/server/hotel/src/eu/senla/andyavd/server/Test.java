package lesson8.server.hotel.src.eu.senla.andyavd.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import lesson8.server.dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import lesson8.server.hotel.src.eu.senla.andyavd.api.view.IHotelManager;

public class Test {

	private static final int PORT = 8195;
	private static final Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		
		IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance(IHotelManager.class);
		hotelManager.loadFromFile();
		
		try (ServerSocket server = new ServerSocket(PORT)) {
			while (true) {
				Socket socket = server.accept();
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
			}
		} catch (IOException e) {
			logger.error("Connection errors!", e);
		} 
	}
}