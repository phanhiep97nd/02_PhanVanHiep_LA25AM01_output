/**
 * Copyright(C) 2020  Luvina Software
 * Common.java, Jul 14, 2020 Phan Văn Hiệp
 */
package manageuser.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
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
	 * Chuyển chuỗi về dạng số nguyên
	 * 
	 * @param input        là giá trị chuỗi cần chuyển
	 * @param defaultValue giá trị mặc định mong muốn
	 * @return number trả về số nguyên
	 */
	public static int convertStringToInt(String input, int defaultValue) {
		// khai báo number
		int numberValue = 0;
		try {
			// chuyển đổi sang số nguyên
			numberValue = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			// Nếu giá trị truyền vào không phải chuỗi, gán bằng giá trị mặc định
			numberValue = defaultValue;
		}
		// trả về giá trị số đã được chuyển đổi
		return numberValue;
	}

	/**
	 * Lấy limit từ file config.properties
	 * 
	 * @return trả về số bản ghi tối đa hiển thị trên 1 page
	 */
	public static int getLimit() {
		// khai báo limit
		int limit = Constant.LIMIT_DEFAULT;
		// Lấy giá trị limit từ file config.properties
		String limitValue = ConfigProperties.getValueByKey(Constant.LIMIT);
		// Nếu limitValue rỗng hoặc null
		if (!"".equals(limitValue) && limitValue != null) {
			// CHuyển sang Interger
			limit = Integer.parseInt(limitValue);
		}
		return limit;
	}

	/**
	 * Lấy offset(Vị trí bắt đầu lấy data)
	 * 
	 * @param currentPage trang hiện tại
	 * @param limit       số lượng bản ghi hiển thị trên 1 page
	 * @return trả về offset
	 */
	public static int getOffset(int currentPage, int limit) {
		// khai báo offset
		int offset = Constant.OFFSET_DEFAULT;
		// tính toán offset
		offset = (currentPage - 1) * limit;
		// trả về offset cần lấy
		return offset;
	}

	/**
	 * Tính tổng số trang
	 * 
	 * @param totalUser tổng số user tìm được
	 * @param limit     số lượng bản ghi hiển thị trên 1 trang
	 * @return trả về tổng số trang
	 */
	public static int getTotalPage(int totalUser, int limit) {
		// khai báo tổng số trang
		int totalPage = 0;
		// Nếu tổng số User chia hết cho số bản ghi trên 1 page
		if (totalUser % limit == 0) {
			// totalPage bằng thương của totalUser và limit
			totalPage = totalUser / limit;
			// ngược lại nếu tổng số User không chia hết cho số bản ghi trên 1 page
		} else {
			// totalPage bằng thương của totalUser và limit cộng thêm 1
			totalPage = totalUser / limit + 1;
		}
		// trả về tổng số trang
		return totalPage;
	}

	/**
	 * Lấy ra danh sách chuỗi paging để hiển thị lên màn hình
	 * 
	 * @param totalUser   tổng số user tìm kiếm được
	 * @param currentPage trang hiện tại
	 * @param limit       số lượng bản ghi hiển thị trên 1 trang
	 * @return listPaging trả về list paging hiển thị
	 */
	public static ArrayList<Integer> getListPaging(int totalUser, int currentPage, int limit) {
		// khai báo listPaging
		ArrayList<Integer> listPaging = new ArrayList<>();
		// Khai báo và lấy về tổng số page
		int totalPage = Common.getTotalPage(totalUser, limit);
		// Khai báo và lấy về số page trên 1 chuỗi paging
		int limitPage = Common.convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_PAGE),
				Constant.LIMITPAGE_DEFAULT);
		// Page bắt đầu chuỗi paging
		int startPage = ((currentPage - 1) / limitPage) * limitPage + 1;
		// Page kết thúc chuỗi paging
		int endPage = startPage + limitPage - 1;
		// Nếu page kết thúc chuỗi lớn hơn tổng số page thì page kết thúc bằng page cuối
		// cùng
		if (endPage > totalPage) {
			endPage = totalPage;
		}
		// thêm trang vào listPageing
		for (int i = startPage; i <= endPage; i++) {
			listPaging.add(i);
		}
		// trả về list trang hiển thị
		return listPaging;
	}
}
