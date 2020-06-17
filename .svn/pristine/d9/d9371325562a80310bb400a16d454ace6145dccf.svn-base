/**
 * Copyright(C) 2020  Luvina Software
 *Common.java, 3:56:16 AM Phan Văn Hiệp
 */
package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Random;

import encodepass.User;

/**
 * Description
 * @author Phan Văn Hiệp
 */
public class Common {

	/**
	 * Tạo ra chuỗi salt
	 * 
	 * @return trả về chuỗi salt vừa random ra
	 * @throws SQLException
	 */
	public static String generateSalt() throws SQLException {
		String saltFinal = "";
			Random random = new SecureRandom();
			byte[] salt = new byte[32];
			random.nextBytes(salt);
			saltFinal = Base64.getEncoder().encodeToString(salt);
		return saltFinal;
	}

	/**
	 * encode cho password từ chuỗi salt
	 * 
	 * @param salt     chuỗi salt
	 * @param password password truyền vào để encode
	 * @return trả về chuỗi là mật khẩu đã được encode
	 * @throws RuntimeException
	 */
	public static String encodePassword(String salt, String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			String saltPassword = salt + password;
			byte[] messageDigest = md.digest(saltPassword.getBytes());
			return Base64.getEncoder().encodeToString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 *Chuyển các kí tự đặc biệt của HTML thành các kí tự được mã hóa để tránh lỗi XSS
	 *@param character kí tự cần mã hóa
	 *@return kí tự sau khi đã mã hóa
	 */
	public static String encodeHTML(String character) {
		String encodeStr = "";
		switch (character) {
		case "\"":
			encodeStr = "&quot;";
			break;
		case "&":
			encodeStr = "&amp;";
			break;
		case "\'":
			encodeStr = "&#x27;";
			break;
		case "/":
			encodeStr = "&#x2F;";
			break;
		case "<":
			encodeStr = "&lt;";
			break;
		case ">":
			encodeStr = "&gt;";
			break;
		}

		return encodeStr;
	}


}
