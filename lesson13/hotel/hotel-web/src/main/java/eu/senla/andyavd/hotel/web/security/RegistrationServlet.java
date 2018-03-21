package eu.senla.andyavd.hotel.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.utils.jwt.PasswordEncrypter;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = -3564832913277343072L;
	private static final String JSON = "application/json;charset=utf-8";
	private static final String NULL = "{null}";
	private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String password = PasswordEncrypter.encryptPassword(request.getParameter("password"));
		try {
			response.setContentType(JSON);
			User user = new User(username, password);
			hotelManager.registerUser(user);
			writer.println("User was successfully registered!");
		} catch (Exception e) {
			logger.info("Failed to register the User", e);
			response.getWriter().print(NULL);
		}
	}
	
	public RegistrationServlet() {
		super();
	}
}
