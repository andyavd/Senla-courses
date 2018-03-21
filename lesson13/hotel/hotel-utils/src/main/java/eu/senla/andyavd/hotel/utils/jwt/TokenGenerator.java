package eu.senla.andyavd.hotel.utils.jwt;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class TokenGenerator {
	private static final Logger logger = Logger.getLogger(TokenGenerator.class);
	private static final String SALT = "secretkey";

	public static String generateToken(String username, String password) {
		String input = (username + password + SALT);
		String generatedToken = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(input.getBytes());
			byte[] bytes = messageDigest.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedToken = sb.toString();
		} catch (Exception e) {
			logger.error("Failed to generate token!", e);
		}
		return generatedToken;
	}
}
