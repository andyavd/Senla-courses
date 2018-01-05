package hotel.src.eu.senla.andyavd.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ServerWorker {
	
	private static final Logger logger = Logger.getLogger(ServerWorker.class);
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	
	private void saveData() {
		Request request = new Request("exit", null);
		sendRequest(request);
	}
	
	public ServerWorker(Socket socket) {
		try {
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			logger.error("Errors with ServerWorker!", e);
		}
	}
	
	public Object sendRequest(Request request) {
		Object response = null;
        try {
        	objectOutputStream.writeObject(request);
        	objectOutputStream.flush();
        
        	response = (Object) objectInputStream.readObject();
        	
        	return response;
        	
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
			saveData();
			objectOutputStream.close();
			objectInputStream.close();
		} catch (IOException e) {
			logger.error("Disconnect failed!", e);
		} 
	}
}
