package ui.src.eu.senla.andyavd.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import org.apache.log4j.Logger;

import ui.src.eu.senla.andyavd.menu.MenuController;
import hotel.src.eu.senla.andyavd.server.ServerWorker;

public class Client {
	
	private static final Logger logger = Logger.getLogger(Client.class);
	private final static int PORT = 8195;

	private Socket socket;

	public Client() {

		try {
			socket = new Socket(InetAddress.getLocalHost(), PORT);
		} catch (IOException e) {
			logger.error("Connection problems!", e);
		}
	}

	public void run() throws ReflectiveOperationException {
//		ServerWorker.getInstance().setSocket(socket);
		MenuController contr = new MenuController();
		contr.run();
	}
}
