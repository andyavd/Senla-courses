package eu.senla.andyavd.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import eu.senla.andyavd.ui.menu.MenuController;
import eu.senla.andyavd.server.TransactionWorker;

public class Client {

	private static final Logger logger = Logger.getLogger(Client.class);
	private final static int PORT = 8195;

	public static void main(String[] args) {
		BasicConfigurator.configure();
		try {

			Socket clientSocket = new Socket(InetAddress.getLocalHost(), PORT);
			TransactionWorker serverWorker = new TransactionWorker(clientSocket);
			MenuController menuController = new MenuController(serverWorker);
			menuController.run();

		} catch (IOException e) {
			logger.error("Connection problems!", e);
		}
	}
}
