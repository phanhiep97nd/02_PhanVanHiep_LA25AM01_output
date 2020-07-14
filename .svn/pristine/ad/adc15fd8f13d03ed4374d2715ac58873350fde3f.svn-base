/**
 * Copyright(C) 2020  Luvina Software
 * Common.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Chứa các hàm common của dự án
 * 
 * @author Phan Van Hiep
 */
public class Common {

	/**
	 * Description của method là làm gì
	 * 
	 * @param [name] [giải thích]
	 * @return [giải thích]
	 * @throws NoSuchAlgorithmException 
	 */
	public static String enscryptPassword(String pass, String salt) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			String saltPassword = salt + pass;
			byte[] messageDigest = md.digest(saltPassword.getBytes());
			return Base64.getEncoder().encodeToString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw e;
		}
	}

	/**
	 * Kiểm tra 2 chuỗi có giống nhau không
	 * 
	 * @param str1 chuỗi cần so sánh
	 * @param str2 chuỗi cần so sánh
	 * @return boolean
	 */
	public static boolean compareString(String str1, String str2) {
		if (str1.equals(str2)) {
			return true;
		} else {
			return false;
		}

	}
}
