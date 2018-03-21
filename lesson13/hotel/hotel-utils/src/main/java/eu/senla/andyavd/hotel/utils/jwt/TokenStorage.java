package eu.senla.andyavd.hotel.utils.jwt;

import java.util.HashMap;
import java.util.Map;

import eu.senla.andyavd.hotel.utils.common.Printer;

public class TokenStorage {

	private static TokenStorage tokenStorage;

	private TokenStorage() {
	}

	public static TokenStorage getInstance() {
		if (tokenStorage == null) {
			tokenStorage = new TokenStorage();
		}
		return tokenStorage;
	}

	private Map<String, String> tokenData = new HashMap<>();

	public void storeToken(String token, String username) {
		tokenData.put(username, token);
	}

	public String getToken(String username) throws Exception {
		if (username == null) {
			return null;
		} else {
			return tokenData.get(username);
		}
	}
	public void deleteToken(String username) throws Exception {
		if (username != null) {
			tokenData.put(username, null);
		} else {
			Printer.print("No such active User!");
		}
	}
}
