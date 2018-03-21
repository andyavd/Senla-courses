package eu.senla.andyavd.hotel.web.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import eu.senla.andyavd.hotel.api.controller.IHotelManager;
import eu.senla.andyavd.hotel.di.DependencyInjection;
import eu.senla.andyavd.hotel.entity.beans.User;
import eu.senla.andyavd.hotel.utils.jwt.TokenStorage;

public class AuthentificationFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(AuthentificationFilter.class);
	private List<String> pathFilter = Arrays.asList(new String[] { "GetHistoriesServlet", "GetRoomsServlet",
			"GetServicesServlet", "GetVisitorsServlet", "RoomServlet", "ServiceServlet", "VisitorServlet", "LoguotServlet"});
	private IHotelManager hotelManager = (IHotelManager) DependencyInjection.getInstance()
			.getClassInstance(IHotelManager.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String url = ((HttpServletRequest) request).getRequestURI();
		String path = StringUtils.substringAfterLast(url, "/");
		String username = request.getParameter("username");
		if (!pathFilter.contains(path)) {
			filterChain.doFilter(request, response);
			return;
		}
		HttpServletRequest req = (HttpServletRequest) request;
		User user = (User) req.getSession().getAttribute("User");
		if (user != null) {
			String token;
			try {
				token = TokenStorage.getInstance().getToken(username);
				if (token != null) {
					filterChain.doFilter(request, response);
					String action = url + " " + (String) req.getSession().getAttribute("method");
					hotelManager.addAudit(user, action);
				}
			} catch (Exception e) {
				logger.info(e.getMessage());
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
		logger.info("AuthentificationFilter was destroyed!");
	}

}
