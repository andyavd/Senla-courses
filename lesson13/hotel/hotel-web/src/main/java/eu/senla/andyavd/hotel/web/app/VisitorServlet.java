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
import eu.senla.andyavd.hotel.entity.beans.Visitor;

public class VisitorServlet extends HttpServlet {

	private static final long serialVersionUID = -4589085725786493775L;
	private static final String JSON = "application/json;charset=utf-8";
	private static final String NULL = "{null}";
	private static final Logger logger = LogManager.getLogger(VisitorServlet.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			ObjectMapper mapper = new ObjectMapper();
			response.setContentType(JSON);
			PrintWriter writer = response.getWriter();
			Visitor visitor = hotelManager.getVisitorById(id);
			writer.write(mapper.writeValueAsString(visitor));
			request.getSession().setAttribute("method","GET");
		} catch (Exception e) {
			logger.info("No such Visitor!", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String lastName = request.getParameter("lastName");
		try {
			response.setContentType(JSON);
			Visitor visitor = new Visitor(lastName);
			hotelManager.addVisitor(visitor);
			writer.println("Visitor was successfully added!");
			request.getSession().setAttribute("method","PUT");
		} catch (Exception e) {
			logger.info("Failed to add the Visitor", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		String lastName = request.getParameter("lastName");
		try {
			response.setContentType(JSON);
			Visitor visitor = hotelManager.getVisitorById(id);
			if ((!request.getParameter(lastName).equals("")) || (request.getParameter(lastName) != null)) {
				hotelManager.updateVisitor(visitor);
			}
			writer.println("Visitor was successfully updated!");
			request.getSession().setAttribute("method","POST");
		} catch (Exception e) {
			logger.info("Failed to update the Visitor", e);
			response.getWriter().print(NULL);
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		Integer id = Integer.parseInt(request.getParameter("id"));
		try {
			response.setContentType(JSON);
			Visitor visitor = hotelManager.getVisitorById(id);
			hotelManager.deleteVisitor(visitor);
			writer.println("Visitor was successfully deleted!");
			request.getSession().setAttribute("method","DELETE");
		} catch (Exception e) {
			logger.info("Failed to delete the Visitor!", e);
		}
	}

	public VisitorServlet() {
		super();
	}
}
