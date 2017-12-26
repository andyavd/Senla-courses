package lesson8.server.hotel.src.eu.senla.andyavd.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.managers.HotelManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lesson8.server.dependencyinjection.src.eu.senla.andyavd.di.DependencyInjection;
import lesson8.server.hotel.src.eu.senla.andyavd.api.view.IHotelManager;

public class ServerThread extends Thread {

	private static final Logger logger = Logger.getLogger(ServerThread.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance(IHotelManager.class);
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;

	public ServerThread(Socket socket) throws IOException {
		objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
		objectInputStream = new ObjectInputStream(socket.getInputStream());
	}

	private Response executeMethod(Request message) {
		int length = message.getParameters().length;
		
		Class<?> [] parameterTypes = new Class[length];
		
		for(int i=0; i<length; i++) {
				parameterTypes[i] = message.getParameters()[i].getClass();
			}
		
		Response response;
		
		try {
			Method method = HotelManager.class.getMethod(message.getMethodName(), parameterTypes);
			response = new Response(method.invoke(hotelManager, message.getParameters()));
		} catch (NoSuchMethodException e) {
			logger.error("No such Method!", e);
			response = new Response("Error on Server");
		} catch (IllegalAccessException e) {
			logger.error("Illegal Access!", e);
			response = new Response("Error on Server");
		} catch (IllegalArgumentException e) {
			logger.error("Illegal Argument!", e);
			response = new Response("Error on Server");
		} catch (InvocationTargetException e) {
			logger.error("Invocation Target!", e);
			response = new Response("Error on Server");
		} catch (SecurityException e) {
			logger.error("Security problems!", e);
			response = new Response("Error on Server");
		}
		
		return response;
	}
	
	private void disconnect() {
		hotelManager.exit();
		try {
			objectOutputStream.close();
			objectInputStream.close();
		} catch (IOException e) {
			logger.error("Disconnect problems!", e);
		} finally {
			this.interrupt();
		}
	}
	
	public void run() {
		try {
			Request message;
			while (true) {
				message = (Request) objectInputStream.readObject();
				if (message != null) {
					Response response = executeMethod(message);
					objectOutputStream.writeObject(response);
					objectOutputStream.flush();
				}
			}
		} catch (IOException e) {
			logger.error("Input/Output errors!", e);
		} catch (ClassNotFoundException e) {
			logger.error("Class Not Found!", e);
		} finally {
			disconnect();
		}
	}
}