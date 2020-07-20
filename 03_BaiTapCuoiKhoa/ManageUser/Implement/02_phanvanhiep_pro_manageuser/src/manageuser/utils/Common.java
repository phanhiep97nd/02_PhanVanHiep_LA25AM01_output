/**
 * Copyright(C) 2020  Luvina Software
 * Common.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.http.HttpSession;

import manageuser.dao.TblUserDao;
import manageuser.dao.impl.TblUserDaoImpl;

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
	 * @throws Exception
	 */
	public static boolean checkLogin(HttpSession session) throws Exception {
		try {
			// Nếu giá trị session loginName != null nghĩa là đã có session
			if (session.getAttribute(Constant.SESSION_LOGINNAME) != null) {
				// Khởi tạp một đối tượng TblUserDaoImpl
				TblUserDao userDaoImpl = new TblUserDaoImpl();
				// Lấy giá trị từ session
				String loginName = (String) session.getAttribute(Constant.SESSION_LOGINNAME);
				// Nếu lấy được đối tượng UserDaoImpl từ hàm getTblUserByLoginName nghĩa là
				// loginName này có tồn tại trong DB
				if (userDaoImpl.getTblUserByLoginName(loginName) != null) {
					return true;
				}
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error : Common.checkLogin " + e.getMessage());
			throw e;
		}
		return false;

	}

	/**
	 * replaceWildCard thay đổi giá trị wideCard nhập vào tránh lỗi sqlInjection
	 * 
	 * @param str giá trị nhập vào
	 * @return str đã được được thay thế
	 */
	public static String replaceWildCard(String str) {
		if (!"".equals(str)) {
			str = str.replace("%", "\\%");
			str = str.replace("_", "\\_");
		}
		// trả về giá trị đã được thay đổi các kí tự đặc biệt
		return str;
	}

	/**
	 * mã hóa các kí tự đặc biệt trong html
	 * 
	 * @param value là giá trị cần mã hóa
	 * @return trả về chuỗi đã được mã hóa
	 */
	public static String encodeHTML(String str) {
		// nếu giá trị truyền vào khác rỗng
		if (!"".equals(str)) {
			str = str.replace("&", "&amp;");
			str = str.replace("'", "&#x27;");
			str = str.replace("/", "&#x2F;");
			str = str.replace("<", "&lt;");
			str = str.replace(">", "&gt;;");
		}
		// trả về giá trị đã được mã hóa
		return str;
	}

}
