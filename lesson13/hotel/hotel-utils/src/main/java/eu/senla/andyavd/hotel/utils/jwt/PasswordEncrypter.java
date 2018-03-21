package eu.senla.andyavd.hotel.utils.jwt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class PasswordEncrypter {
	
	private static final Logger logger = Logger.getLogger(PasswordEncrypter.class);

	public static String encryptPassword(String password) {
		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.getBytes());
			String encryptedString = new String(messageDigest.digest());
			return encryptedString;
		} catch (NoSuchAlgorithmException e) {
			logger.error("Failed to encrypt the password!", e);
			return null;
		}
	}
}