package lesson8.server.hotel.src.eu.senla.andyavd.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ServerWorker {
	
	private static final Logger logger = Logger.getLogger(ServerWorker.class);
	private static ServerWorker serverWorker;
	private Socket socket;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	private ServerWorker() {
	}
	
	public static ServerWorker getInstance() {
		if (serverWorker == null) {
			serverWorker = new ServerWorker();
		}
		return serverWorker;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
		try {
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			logger.error("Input/Output errors!", e);
		}  catch (NullPointerException e) {
			logger.error("Null Pointer!", e);
		}
	}
	
	public Response sendMessage(Request message) {
		try {
			objectOutputStream.writeObject(message);
			objectOutputStream.flush();
			return (Response) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			logger.error("Class not Found!", e);
			return null;
		} catch (IOException e) {
			logger.error("Input/Output errors!", e);
			return null;
		}
	}
	
	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			logger.error("Disconnect errors!", e);
		} 
	}
}
