package eu.senla.andyavd.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import eu.senla.andyavd.Printer;
import eu.senla.andyavd.server.Invoker;
import eu.senla.andyavd.server.ServerThread;

public class Test {

	private static final int PORT = 8195;
	private static final Logger logger = Logger.getLogger(Test.class);
	
	public static void main(String[] args) {
		
		Invoker.invokeHotelManager("loadFromFile", null);
		
		ServerSocket serverSocket = null;

		try {
			
			ServerThread serverThread;
			serverSocket = new ServerSocket(PORT);
			
			while (true) {
				Printer.print("Waiting for a connection on port " + PORT + "...");
				Socket socket = serverSocket.accept();
				serverThread = new ServerThread(socket);
				Printer.print("Connected: " + serverThread.getName());
				serverThread.start();
			}
		} catch (IOException e) {
			logger.error("Connection errors!", e);
		} finally {
            try {
                if(serverSocket != null) serverSocket.close();
            } catch (IOException e) {
            		logger.error("Failed to close the connection!", e);
            }
        }
	}
}