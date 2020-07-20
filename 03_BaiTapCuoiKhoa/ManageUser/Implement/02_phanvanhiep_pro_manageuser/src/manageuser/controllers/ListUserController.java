/**
 * Copyright(C) 2020  Luvina Software
Abc.java, Jul 13, 2020 Phan Van Hiep
 */
package manageuser.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroupEntity;
import manageuser.entities.TblUserEntity;
import manageuser.entities.UserInfoEntity;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.TblUserLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.utils.Common;
import manageuser.utils.ConfigProperties;
import manageuser.utils.Constant;
import manageuser.utils.MessageProperties;

/**
 * Controller để xử lý cho màn hình ADM002
 * 
 * @author Phan Van Hiep
 */
public class ListUserController extends HttpServlet {

	/**
	 * Xử lí khi đăng nhập thành công
	 * 
	 * @param req  request
	 * @param resp response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			// Khởi tạo session từ request
			HttpSession session = req.getSession();

			// Khởi tạo đối tượng groupLogicImpl
			MstGroupLogic groupLogicImpl = new MstGroupLogicImpl();
			// khởi tạo đối tượng userLogicImpl
			TblUserLogic userLogicImpl = new TblUserLogicImpl();

			// Khởi tạo listUserInfo để chứa kết qẩu trả về hàm getListUser
			ArrayList<UserInfoEntity> listUserInfo = new ArrayList<UserInfoEntity>();
			// Khởi tạo listUser chứa kết quả trả về hàm getAllMstGroup()
			ArrayList<MstGroupEntity> listGroup = new ArrayList<MstGroupEntity>();
			// Khởi tạo listPaging chứa kết quả trả về hàm getListPaging()
			ArrayList<Integer> listPaging = new ArrayList<Integer>();

			// khai báo giá trị default cho các biến
			int limit = Common.getLimit();
			int offset = Constant.OFFSET_DEFAULT;
			int groupId = Constant.GROUPID_DEFAULT;
			String fullName = Constant.FULLNAME_DEFAULT;
			String sortType = Constant.SORTTYPE_DEFAULT;
			String sortByFullName = Constant.ASC;
			String sortByCodeLevel = Constant.ASC;
			String sortByEndDate = Constant.DESC;
			int currentPage = Constant.CURRENTPAGE_DEFAULT;

			// Nếu checkLogin trả về true (Đã đăng nhập)
			if (Common.checkLogin(session)) {
				// Lấy giá trị của type trên request
				String type = req.getParameter("type");
				// Nếu type là default, hoặc search, hoặc sort, hoặc paging, hoặc back
				if (Common.compareString(Constant.TYPE_DEFAULT, type)) {

				} else if (Common.compareString(Constant.TYPE_SEARCH, type)) {
					// Lấy fullNam và groupId người dùng nhập ở request
					fullName = req.getParameter("name");
					groupId = Common.convertStringToInt(req.getParameter("group_id"), Constant.GROUPID_DEFAULT);
					// khởi tạo đối tượng userSearch
					TblUserEntity userSearch = new TblUserEntity();
					// gán các giá trị cho thuộc tính
					userSearch.setGroupId(groupId);
					userSearch.setFullName(fullName);
					// truyền userSearch vào sessin SESSION_SEARCH
					session.setAttribute(Constant.SESSION_SEARCH, userSearch);
				} else if (Common.compareString(Constant.TYPE_SORT, type)) {

				} else if (Common.compareString(Constant.TYPE_PAGING, type)) {
					currentPage = Common.convertStringToInt(req.getParameter("currentPage"),
							Constant.CURRENTPAGE_DEFAULT);
					// Nếu tồn tại session search
					if (session.getAttribute(Constant.SESSION_SEARCH) != null) {
						// lấy đối tượng userSearch từ SESSION_SEARCH
						TblUserEntity userSearch = (TblUserEntity) session.getAttribute(Constant.SESSION_SEARCH);
						// Lấy ra thuộc tính
						groupId = userSearch.getGroupId();
						fullName = userSearch.getFullName();
					}
				} else if (Common.compareString(Constant.TYPE_BACK, type)) {

				}
				// Lấy về totalUser
				int totalUser = userLogicImpl.getTotalUsers(groupId, fullName);
				// Lấy về totalPage
				int totalPage = Common.getTotalPage(totalUser, limit);
				// Lấy về limitPage
				int limitPage = Common.convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_PAGE),
						Constant.LIMITPAGE_DEFAULT);
				// Trường hợp người dùng sửa URL currentPage ngoài phạm vi số page
				if (totalPage >= 1 && currentPage > totalPage) {
					currentPage = totalPage;
				} else if (currentPage <= 0) {
					currentPage = Constant.CURRENTPAGE_DEFAULT;
				}
				// Lấy về giá trị offset
				offset = Common.getOffset(currentPage, limit);
				// Lấy về listGroup từ hàm getAllMstGroup
				listGroup = groupLogicImpl.getAllMstGroup();
				// Gán kết quả trả về hàm getListUser vào listUserInfo
				listUserInfo = userLogicImpl.getListUser(offset, limit, groupId, fullName, sortType, sortByFullName,
						sortByCodeLevel, sortByEndDate);
				// Gán kết quả trả về hàm getListPaging vào listPaging
				listPaging = Common.getListPaging(totalUser, currentPage, limit);

				// gán danh sách group lên request
				req.setAttribute("listGroup", listGroup);
				// Nếu không tìm thấy user nào thì in ra câu thông báo, ngược lại nếu có kết quả
				// thì gửi lên trang jsp
				if (listUserInfo.size() == 0) {
					String notiMSG005 = MessageProperties.getValueByKey(Constant.MSG005);
					req.setAttribute("notiMSG005", notiMSG005);
				} else {
					// gán danh sách userInfor lên request
					req.setAttribute("listUserInfo", listUserInfo);
					// Gán listPaging lên Request
					req.setAttribute("listPaging", listPaging);
				}
				// truyền fullname tìm kiếm vào request
				req.setAttribute("fullName", fullName);
				// truyền groupID tìm kiếm vào request
				req.setAttribute("groupId", groupId);
				// Gán totalPage lên request
				req.setAttribute("totalPage", totalPage);
				// Gán limitPage lên request
				req.setAttribute("limitPage", limitPage);
				// Gán currentPage lên request
				req.setAttribute("currentPage", currentPage);

				// Chuyển hướng sang trang ADM002
				req.getServletContext().getRequestDispatcher(Constant.PATH_ADM002).forward(req, resp);
			} else {
				// gọi đến URL login.do để về màn hình login
				resp.sendRedirect(Constant.URL_LOGIN);
			}
		} catch (Exception e) {
			// Hiển thị ở console lỗi
			System.out.println("Error : ListUserServletController.doPost " + e.getMessage());
			// Chuyển đến màn hình System_Error
			resp.sendRedirect(Constant.PATH_SYSTEM_ERROR);
		}

	}
}
