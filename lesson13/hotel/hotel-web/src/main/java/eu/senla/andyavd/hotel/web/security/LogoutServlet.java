package eu.senla.andyavd.hotel.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.utils.jwt.TokenStorage;

public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 3348070929488784750L;
	private final static Logger logger = Logger.getLogger(LogoutServlet.class);
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		hotelManager.addAudit(user, "LOGOUT");
		try {
			TokenStorage.getInstance().deleteToken(user.getUserName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		session.invalidate();
		response.getWriter().println("Logout succeeded!");
	}
	
	public LogoutServlet(){
		super();
	}
}
