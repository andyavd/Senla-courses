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
import eu.senla.andyavd.hotel.utils.jwt.TokenGenerator;
import eu.senla.andyavd.hotel.utils.jwt.TokenStorage;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 8053253089828516728L;
	private static final String JSON = "application/json;charset=utf-8";
	private static final String NULL = "{null}";
	private static final Logger logger = LogManager.getLogger(LoginServlet.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = PasswordEncrypter.encryptPassword(request.getParameter("password"));
		try {
			response.setContentType(JSON);
			PrintWriter writer = response.getWriter();
			User user = hotelManager.getUserByUsername(username);
			if (PasswordEncrypter.encryptPassword(password).equals(user.getPassword())) {
				String token = TokenGenerator.generateToken(username, password);
				TokenStorage.getInstance().storeToken(username, token);
			} else {
				writer.write("Wrong password!");
			}
			writer.write("Login succeeded!");
		} catch (Exception e) {
			logger.info("No such Room!", e);
			response.getWriter().print(NULL);
		}
	}

	public LoginServlet() {
		super();
	}
}
