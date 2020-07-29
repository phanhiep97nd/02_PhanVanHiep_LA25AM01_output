/**
 * Copyright(C) 2020  Luvina Software
 * AddUserController.java, Jul 27, 2020 Phan Văn Hiệp
 */
package manageuser.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.MstJapanEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller để xử lý cho màn hình ADM003 truong hợp Add
 * 
 * @author Phan Van Hiep
 */
public class AddUserInputController extends HttpServlet {
	/**
	 * Xử lý khi click vào button Add của ADM002
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session từ request
			HttpSession session = req.getSession();

			// Nếu checkLogin trả về true (Đã đăng nhập)
			if (Common.checkLogin(session)) {
				setDataLogic(req);
				req.setAttribute("getDefaultValue", getDefaultValue());
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
			} else {
				// gọi đến URL login.do để về màn hình login
				resp.sendRedirect(Constant.URL_LOGIN);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserInputController.doGet " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.URL_SYSTEMERROR);
		}
	}

	/**
	 * Set các giá trị cho các selectbox ở màn hình ADM003
	 * 
	 * @param request
	 */
	private void setDataLogic(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		// Khởi tạo listGroup để lưu tất cả các group lấy được
		ArrayList<MstGroupEntity> listMstGroup = new ArrayList<>();
		// Khởi tạo listMstJapan để lưu tất cả các group lấy được
		List<MstJapanEntity> listMstJapan = new ArrayList<>();
		// khởi tạo listYear
		List<Integer> listYear = new ArrayList<>();
		// khởi tạo listMonth
		List<Integer> listMonth = new ArrayList<>();
		// khởi tạo listMonth
		ArrayList<Integer> listDay = new ArrayList<>();

		// Khởi tạo đối tượng mstGroupLogicImpl
		MstGroupLogic mstGroupLogicImpl = new MstGroupLogicImpl();
		// Khởi tạo đối tượng mstJapanLogicImpl
		MstJapanLogic mstJapanLogicImpl = new MstJapanLogicImpl();

		try {
			// lấy về giá trị listMstGroup
			listMstGroup = mstGroupLogicImpl.getAllMstGroup();
			// Lấy về giá trị listMstJapan
			listMstJapan = mstJapanLogicImpl.getAllMstJapan();
			// Lấy về listYear
			listYear = Common.getListYear(1990, Common.getDate().get(Calendar.YEAR));
			// Lấy về listMonth
			listMonth = Common.getListMonth();
			// Lấy về listDay
			listDay = Common.getListDay();

			// gán lên request
			req.setAttribute(Constant.REQUEST_LISTMSTGROUP, listMstGroup);
			req.setAttribute(Constant.REQUEST_LISTMSTJAPAN, listMstJapan);
			req.setAttribute(Constant.REQUEST_LISTYEAR, listYear);
			req.setAttribute(Constant.REQUEST_LISTMONTH, listMonth);
			req.setAttribute(Constant.REQUEST_LISTDAY, listDay);
		} catch (ClassNotFoundException | SQLException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : AddUserInputController.setDataLogic " + e.getMessage());
			// throw lỗi
			throw e;
		}
	}

	/**
	 * Lấy về các giá trị mặc đinh của các các hạng mục màn hình ADM003 tương ứng
	 * với các thuộc tính của đối tượng UserInfoEntity
	 * 
	 * @return đối tượng tblUserInfoEntity
	 */
	private UserInfoEntity getDefaultValue() {
		// Khởi tạo đối tượng UserInfoEntity
		UserInfoEntity userInforEntity = new UserInfoEntity();
		// Khởi tạo biến lấy về ngày, tháng, năm hiện tại
		int yearNow = Common.getDate().get(Calendar.YEAR);
		int monthNow = Common.getDate().get(Calendar.MONTH) + 1;
		int dayNow = Common.getDate().get(Calendar.DATE);
		// Khai báo và khởi tạo giá trị default các thuộc tính của đối tượng
		// UserInfoEntity
		String loginName = Constant.DEFAULT_EMPTY;
		String groupName = Constant.DEFAULT_EMPTY;
		int groupId = Constant.DEFAULT_ZERO;
		String fullName = Constant.DEFAULT_EMPTY;
		String fullNameKatana = Constant.DEFAULT_EMPTY;
		String birthday = yearNow + "/" + monthNow + "/" + dayNow;
		String email = Constant.DEFAULT_EMPTY;
		String tel = Constant.DEFAULT_EMPTY;
		String password = Constant.DEFAULT_EMPTY;
		String passwordConfirm = Constant.DEFAULT_EMPTY;
		String nameLevel = Constant.DEFAULT_EMPTY;
		String codeLevel = Constant.DEFAULT_EMPTY;
		String endDate = (yearNow + 1) + "/" + monthNow + "/" + dayNow;
		String startDate = yearNow + "/" + monthNow + "/" + dayNow;
		int total = Constant.DEFAULT_ZERO;

		// gán giá trị các thuộc tính đối tượng userInforEntity
		userInforEntity.setLoginName(loginName);
		userInforEntity.setGroupId(groupId);
		userInforEntity.setGroupName(groupName);
		userInforEntity.setFullName(fullName);
		userInforEntity.setFullNameKatana(fullNameKatana);
		userInforEntity.setBirthday(birthday);
		userInforEntity.setEmail(email);
		userInforEntity.setTel(tel);
		userInforEntity.setPassword(password);
		userInforEntity.setPasswordConfirm(passwordConfirm);
		userInforEntity.setNameLevel(nameLevel);
		userInforEntity.setCodeLevel(codeLevel);
		userInforEntity.setEndDate(endDate);
		userInforEntity.setStartDate(startDate);
		userInforEntity.setTotal(total);

		return userInforEntity;

	}
}
