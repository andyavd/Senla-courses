package eu.senla.andyavd.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ServerThread extends Thread {

	private static final Logger logger = Logger.getLogger(ServerThread.class);
	private Socket clientSocket;

	public ServerThread(Socket socket) {
		this.clientSocket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream objectInputStream;
		ObjectOutputStream objectOutputStream;

		try {
			objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
			objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

			while (true) {
				Request request = (Request) objectInputStream.readObject();

				if (request != null) {
					objectOutputStream
							.writeObject(Invoker.invokeHotelManager(request.getMethodName(), request.getParameters()));
					objectOutputStream.flush();
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error("Class Not Found!", e);
		} catch (IOException e) {
			logger.error("Input/Output errors!", e);
		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				logger.error("Failed to close!", e);
			}
		}
	}
}
