package eu.senla.andyavd.hotel.web.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Room;

public class RoomServlet extends HttpServlet {

	private static final long serialVersionUID = 170036607083504658L;
	private static final String JSON = "application/json;charset=utf-8";
	private static final String NULL = "{null}";
	private static final Logger logger = LogManager.getLogger(RoomServlet.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType(JSON);
			Room room = hotelManager.getRoomById(id);
			writer.write(mapper.writeValueAsString(room));
			request.getSession().setAttribute("method","GET");
		} catch (Exception e) {
			logger.info("No such Room!", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		Integer capacity = Integer.parseInt(request.getParameter("capacity"));
		Double dailyPrice = Double.parseDouble(request.getParameter("dailyPrice"));
		String stars = request.getParameter("stars");
		try {
			response.setContentType(JSON);
			Room room = new Room(roomNumber, capacity, dailyPrice, stars);
			hotelManager.addRoom(room);
			writer.println("Room was successfully added!");
			request.getSession().setAttribute("method","PUT");
		} catch (Exception e) {
			logger.info("Failed to add the Room", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
		Integer capacity = Integer.parseInt(request.getParameter("capacity"));
		Double dailyPrice = Double.parseDouble(request.getParameter("dailyPrice"));
		String stars = request.getParameter("stars");
		try {
			response.setContentType(JSON);
			Room room = hotelManager.getRoomById(id);
			room.setRoomNumber(roomNumber);
			if ((!request.getParameter(capacity.toString()).equals("")) || (request.getParameter(capacity.toString()) != null)) {
				room.setCapasity(capacity);
			}
			if ((!request.getParameter(dailyPrice.toString()).equals("")) || (request.getParameter(dailyPrice.toString()) != null)) {
				room.setDailyPrice(dailyPrice);
			}
			if ((!request.getParameter(stars).equals("")) || (request.getParameter(stars) != null)) {
				room.setStars(stars);
			}
			writer.println("Room was successfully updated!");
			request.getSession().setAttribute("method","POST");
		} catch (Exception e) {
			logger.info("Failed to update the Room", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			response.setContentType(JSON);
			Room room = hotelManager.getRoomById(id);
			hotelManager.deleteRoom(room);
			writer.println("Room was successfully deleted!");
			request.getSession().setAttribute("method","DELETE");
		} catch (Exception e) {
			logger.info("Failed to delete the Room!", e);
		}
	}
	
	public RoomServlet() {
		super();
	}
}