package eu.senla.andyavd.hotel.web.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.Visitor;

public class GetVisitorsServlet extends HttpServlet {

	private static final long serialVersionUID = 7672653335559382193L;
	private static final String JSON = "application/json;charset=utf-8";
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType(JSON);
		PrintWriter writer = response.getWriter();

		List<Visitor> visitors = hotelManager.getVisitors();
		writer.write(mapper.writeValueAsString(visitors));
		request.getSession().setAttribute("method","GET");
	}

	public GetVisitorsServlet() {
		super();
	}
}