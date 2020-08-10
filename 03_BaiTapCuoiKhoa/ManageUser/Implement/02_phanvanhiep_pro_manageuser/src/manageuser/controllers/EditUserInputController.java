/**
 * Copyright(C) 2020  Luvina Software
 * EditUserInputController.java, Aug 10, 2020 Phan Văn Hiệp
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
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.Constant;

/**
 * Controller để xử lý cho màn hình ADM003 truong hợp Edit
 * 
 * @author Phan Van Hiep
 */
public class EditUserInputController extends HttpServlet {
	/**
	 * Xử lý khi click vào button Edit của ADM005
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			// Khởi tạo session từ request
			HttpSession session = req.getSession();

			// Nếu checkLogin trả về true (Đã đăng nhập)
			if (Common.checkLogin(session)) {
				// gọi đến hàm setDataLogic để truyền các giá trị của pulldown
				// lên req
				setDataLogic(req);
				// lấy userInfoEntity
				UserInfoEntity userInfoEntity = getDefaultValue(req);
				if (userInfoEntity != null) {
					// gán userInfoEntity lên req
					req.setAttribute(Constant.REQUEST_USERINFORENTITY, userInfoEntity);
					// forward đến trang ADM003
					req.getServletContext().getRequestDispatcher(Constant.PATH_ADM003).forward(req, resp);
				} else {
					// Thông báo lỗi không tồn tại Id
					resp.sendRedirect(Constant.URL_SYSTEMERROR + Constant.URL_ERROR_NOTEXIST_ID);
				}
			} else {
				// gọi đến URL login.do để về màn hình login
				resp.sendRedirect(Constant.URL_LOGIN);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : EditUserInputController.doGet " + e.getMessage());
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
	 * @throws ClassNotFoundException, SQLException
	 */
	private UserInfoEntity getDefaultValue(HttpServletRequest req) throws ClassNotFoundException, SQLException {
		try {
			// Khởi tạo đối tượng TblUserLogic
			TblUserLogic tblUserLogic = new TblUserLogicImpl();
			// Khởi tạo đối tượng UserInfoEntity
			UserInfoEntity userInfoEntity = new UserInfoEntity();
			// Khởi tạo biến type để lấy type từ request
			String type = Constant.DEFAULT_EMPTY;

			// Lấy về type từ req
			type = req.getParameter(Constant.REQUEST_TYPE);
			// Lấy id từ request
			int userId = Common.convertStringToInt(req.getParameter(Constant.REQUEST_ID), Constant.USER_ID_DEFAULT);
			if (type == null) {
				// Nếu tồn tại user
				if (!tblUserLogic.checkExistUserById(userId)) {
					userInfoEntity = null;
				} else {
					// Lấy về giá trị cho đối tượng userInfoEntity
					userInfoEntity = tblUserLogic.getUserInfoByUserId(userId);
					// nếu không có trình độ tiếng nhật
					if (userInfoEntity.getCodeLevel() == null) {
						// Khởi tạo biến lấy về ngày, tháng, năm hiện tại
						int yearNow = Common.getDate().get(Calendar.YEAR);
						int monthNow = Common.getDate().get(Calendar.MONTH) + 1;
						int dayNow = Common.getDate().get(Calendar.DATE);
						String endDate = Common.setFormatDate(Integer.toString(yearNow + 1), Integer.toString(monthNow),
								Integer.toString(dayNow));
						String startDate = Common.setFormatDate(Integer.toString(yearNow), Integer.toString(monthNow),
								Integer.toString(dayNow));
						String total = Constant.DEFAULT_EMPTY;
						// set ngày bắt đầu, ngày hết hạn, điểm
						userInfoEntity.setStartDate(startDate);
						userInfoEntity.setEndDate(endDate);
						userInfoEntity.setTotal(total);
					}
				}
			}

			// Trả về đối tượng userInforEntity
			return userInfoEntity;
		} catch (SQLException | NullPointerException e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : EditUserInputController.getDefaultValue " + e.getMessage());
			// throw lỗi
			throw e;
		}
	}
}
