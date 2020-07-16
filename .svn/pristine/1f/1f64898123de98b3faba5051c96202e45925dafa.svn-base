/**
 * Copyright(C) 2020  Luvina Software
 * Common.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpSession;

/**
 * Chứa các hàm common của dự án
 * 
 * @author Phan Van Hiep
 */
public class Common {

	/**
	 * Hàm mã hóa password
	 * 
	 * @param salt password người dùng nhập vào
	 * @param salt chuỗi salt là ngày giờ hiện tại
	 * @return chuỗi khi đã được mã hóa
	 * @throws NoSuchAlgorithmException
	 */
	public static String enscryptPassword(String pass, String salt) throws NoSuchAlgorithmException {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			String saltPassword = salt + pass;
			byte[] messageDigest = md.digest(saltPassword.getBytes());
			return Base64.getEncoder().encodeToString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error : Common.enscryptPassword " + e.getMessage());
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
		// Nếu chuỗi 1 bằng chuỗi 2 thì trả về true, không thì trả về false
		if (str1.equals(str2)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Kiểm tra xem đã đăng nhập thành công chưa
	 * 
	 * @param session truyền vào session để kiểm tra
	 * @return boolean (Nếu đăng nhập rồi thì trả về true, chưa đăng nhập thì trả về
	 *         false)
	 */
	public static boolean checkLogin(HttpSession session) {
		// Nếu giá trị session loginName = null nghĩa là chưa đăng nhập thành công =>
		// trả về false
		if (session.getAttribute(Constant.SESSION_LOGINNAME) == null) {
			return false;
			// Ngược lại session khác null nghĩa là đã đăng nhập thành công => trả về true
		} else {
			return true;
		}
	}
}
