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
import eu.senla.andyavd.hotel.entity.beans.Service;

public class ServiceServlet extends HttpServlet{

	private static final long serialVersionUID = -2836608977970185298L;
	private static final String JSON = "application/json;charset=utf-8";
	private static final String NULL = "{null}";
	private static final Logger logger = LogManager.getLogger(ServiceServlet.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType(JSON);
			PrintWriter writer = response.getWriter();
			Service service = hotelManager.getServiceById(id);
			writer.write(mapper.writeValueAsString(service));
			request.getSession().setAttribute("method","GET");
		} catch (Exception e) {
			logger.info("No such Service!", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Double dailyPrice = Double.parseDouble(request.getParameter("dailyPrice"));
		String name = request.getParameter("name");
		try {
			response.setContentType(JSON);
			Service service = new Service(name, dailyPrice);
			hotelManager.addService(service);
			writer.println("Service was successfully added!");
			request.getSession().setAttribute("method","PUT");
		} catch (Exception e) {
			logger.info("Failed to add the Service", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		Double dailyPrice = Double.parseDouble(request.getParameter("dailyPrice"));
		String name = request.getParameter("name");
		try {
			response.setContentType(JSON);
			Service service = hotelManager.getServiceById(id);
			if ((!request.getParameter(dailyPrice.toString()).equals("")) || (request.getParameter(dailyPrice.toString()) != null)) {
				service.setDailyPrice(dailyPrice);
			}
			if ((!request.getParameter(name).equals("")) || (request.getParameter(name) != null)) {
				service.setName(name);
			}
			writer.println("Service was successfully updated!");
			request.getSession().setAttribute("method","POST");
		} catch (Exception e) {
			logger.info("Failed to update the Service", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			response.setContentType(JSON);
			Service service = hotelManager.getServiceById(id);
			hotelManager.deleteService(service);
			writer.println("Service was successfully deleted!");
			request.getSession().setAttribute("method","DELETE");
		} catch (Exception e) {
			logger.info("Failed to delete the Service!", e);
		}
	}
	
	public ServiceServlet() {
		super();
	}
}
