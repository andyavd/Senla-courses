package hotel.src.eu.senla.andyavd.server;

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

	// private IHotelManager hotelManager = (IHotelManager)
	// DependencyInjection.getInstance().getInstance(IHotelManager.class);
	// private ObjectOutputStream objectOutputStream;
	// private ObjectInputStream objectInputStream;

	// public ServerThread(Socket socket) throws IOException {
	// objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
	// objectInputStream = new ObjectInputStream(socket.getInputStream());
	// }
	//
	// private Response executeMethod(Request request) {
	// int length = request.getParameters().length;
	//
	// Class<?> [] parameterTypes = new Class[length];
	//
	// for(int i=0; i<length; i++) {
	// parameterTypes[i] = request.getParameters()[i].getClass();
	// }
	//
	// Response response;
	//
	// try {
	// Method method = HotelManager.class.getMethod(request.getMethodName(),
	// parameterTypes);
	// response = new Response(method.invoke(hotelManager,
	// request.getParameters()));
	// } catch (NoSuchMethodException e) {
	// logger.error("No such Method!", e);
	// response = new Response("Error on Server");
	// } catch (IllegalAccessException e) {
	// logger.error("Illegal Access!", e);
	// response = new Response("Error on Server");
	// } catch (IllegalArgumentException e) {
	// logger.error("Illegal Argument!", e);
	// response = new Response("Error on Server");
	// } catch (InvocationTargetException e) {
	// logger.error("Invocation Target!", e);
	// response = new Response("Error on Server");
	// } catch (SecurityException e) {
	// logger.error("Security problems!", e);
	// response = new Response("Error on Server");
	// }
	//
	// return response;
	// }
	//
	// private void disconnect() {
	// hotelManager.exit();
	// try {
	// objectOutputStream.close();
	// objectInputStream.close();
	// } catch (IOException e) {
	// logger.error("Disconnect problems!", e);
	// } finally {
	// this.interrupt();
	// }
	// }
	//
	// public void run() {
	// try {
	// Request request;
	// while (true) {
	// request = (Request) objectInputStream.readObject();
	// if (request != null) {
	// Response response = executeMethod(request);
	// objectOutputStream.writeObject(response);
	// objectOutputStream.flush();
	// }
	// }
	// } catch (IOException e) {
	// logger.error("Input/Output errors!", e);
	// } catch (ClassNotFoundException e) {
	// logger.error("Class Not Found!", e);
	// } finally {
	// disconnect();
	// }
	// }
}
