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
				// Nếu type là default, hoặc search, hoặc sort, hoặc paging,
				// hoặc back
				if (Common.compareString(Constant.TYPE_DEFAULT, type)) {
					session.removeAttribute(Constant.SESSION_SEARCH);
					session.removeAttribute(Constant.SESSION_CURRENTPAGE);
					session.removeAttribute(Constant.SESSION_SORT_TYPE);
					session.removeAttribute(Constant.SESSION_SORT_LIKE);
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
					// xóa session sort (Để khi người dùng tìm kiếm kahi và click vào paging các giá
					// trị sort đã về mặc định)
					session.removeAttribute(Constant.SESSION_SORT_TYPE);
					session.removeAttribute(Constant.SESSION_SORT_LIKE);
				} else if (Common.compareString(Constant.TYPE_SORT, type)) {
					// Lấy về sortType từ request
					sortType = req.getParameter("sortType");
					// Gán sortType lên session
					session.setAttribute(Constant.SESSION_SORT_TYPE, sortType);
					// Lấy về kiểu sort ASC hay DESC
					String sortLike = req.getParameter("sortLike");
					// Nếu là sort theo fullName, hoặc sort theo codeLevel, hoặc là sort theo
					// endDate
					if (Common.compareString(Constant.SORT_TYPE_FULLNAME, sortType)) {
						// Nếu kiểu sort là ASC hoặc ngược lại kiểu sort là DESC
						if (Common.compareString(Constant.ASC, sortLike)) {
							sortByFullName = Constant.DESC;
							sortByCodeLevel = Constant.ASC;
							sortByEndDate = Constant.DESC;
						} else if (Common.compareString(Constant.DESC, sortLike)) {
							sortByFullName = Constant.ASC;
							sortByCodeLevel = Constant.ASC;
							sortByEndDate = Constant.DESC;
						}
						// Gán sortLike lên session
						session.setAttribute(Constant.SESSION_SORT_LIKE, sortByFullName);
					} else if (Common.compareString(Constant.SORT_TYPE_CODELEVEL, sortType)) {
						// Nếu kiểu sort là ASC hoặc ngược lại kiểu sort là DESC
						if (Common.compareString(Constant.ASC, sortLike)) {
							sortByCodeLevel = Constant.DESC;
							sortByFullName = Constant.ASC;
							sortByEndDate = Constant.DESC;
						} else if (Common.compareString(Constant.DESC, sortLike)) {
							sortByCodeLevel = Constant.ASC;
							sortByFullName = Constant.ASC;
							sortByEndDate = Constant.DESC;
						}
						// Gán sortLike lên session
						session.setAttribute(Constant.SESSION_SORT_LIKE, sortByCodeLevel);
					} else if (Common.compareString(Constant.SORT_TYPE_ENDDATE, sortType)) {
						// Nếu kiểu sort là ASC hoặc ngược lại kiểu sort là DESC
						if (Common.compareString(Constant.ASC, sortLike)) {
							sortByEndDate = Constant.DESC;
							sortByFullName = Constant.ASC;
							sortByCodeLevel = Constant.ASC;
						} else if (Common.compareString(Constant.DESC, sortLike)) {
							sortByEndDate = Constant.ASC;
							sortByFullName = Constant.ASC;
							sortByCodeLevel = Constant.ASC;
						}
						// Gán sortLike lên session
						session.setAttribute(Constant.SESSION_SORT_LIKE, sortByEndDate);
					}
					// Nếu tồn tại session search (Lấy xuống để khi click vẫn giữ được giá trị cùng
					// điều kiện tìm kiếm)
					if (session.getAttribute(Constant.SESSION_SEARCH) != null) {
						// lấy đối tượng userSearch từ SESSION_SEARCH
						TblUserEntity userSearch = (TblUserEntity) session.getAttribute(Constant.SESSION_SEARCH);
						// Lấy ra thuộc tính
						groupId = userSearch.getGroupId();
						fullName = userSearch.getFullName();
					}
					// Nếu có tồn tại session currentPage (Lấy xuống để vẫn giữ được paging)
					if (session.getAttribute(Constant.SESSION_CURRENTPAGE) != null) {
						// lấy trang từ session
						currentPage = (int) session.getAttribute(Constant.SESSION_CURRENTPAGE);
					}

				} else if (Common.compareString(Constant.TYPE_PAGING, type)) {
					currentPage = Common.convertStringToInt(req.getParameter("currentPage"),
							Constant.CURRENTPAGE_DEFAULT);
					// Nếu tồn tại session search (Lấy xuống để khi click vẫn giữ được giá trị cùng
					// điều kiện tìm kiếm)
					if (session.getAttribute(Constant.SESSION_SEARCH) != null) {
						// lấy đối tượng userSearch từ SESSION_SEARCH
						TblUserEntity userSearch = (TblUserEntity) session.getAttribute(Constant.SESSION_SEARCH);
						// Lấy ra thuộc tính
						groupId = userSearch.getGroupId();
						fullName = userSearch.getFullName();
					}
					// Nếu tồn tại session sortType (Lấy xuống để vẫn giữ được các giá trị sort)
					if (session.getAttribute(Constant.SESSION_SORT_TYPE) != null) {
						// lấy giá trị từ SESSION_SORT_TYPE
						sortType = (String) session.getAttribute(Constant.SESSION_SORT_TYPE);
						// nếu là loại sắp xếp theo full_name
						if (Common.compareString(Constant.SORT_TYPE_FULLNAME, sortType)) {
							// lấy giá trị sắp xếp theo fullname từ session
							sortByFullName = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
							// nếu là loại sắp xếp theo code level
						} else if (Common.compareString(Constant.SORT_TYPE_CODELEVEL, sortType)) {
							// lấy giá trị sắp xếp theo code level từ session
							sortByCodeLevel = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
							// nếu là sắp xếp theo end_date
						} else if (Common.compareString(Constant.SORT_TYPE_ENDDATE, sortType)) {
							// lấy giá trị sắp xếp theo code level từ session
							sortByEndDate = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
						}
					}
				} else if (Common.compareString(Constant.TYPE_BACK, type)) {
					// Nếu tồn tại session search (Lấy xuống để khi click vẫn giữ được giá trị cùng
					// điều kiện tìm kiếm)
					if (session.getAttribute(Constant.SESSION_SEARCH) != null) {
						// lấy đối tượng userSearch từ SESSION_SEARCH
						TblUserEntity userSearch = (TblUserEntity) session.getAttribute(Constant.SESSION_SEARCH);
						// Lấy ra thuộc tính
						groupId = userSearch.getGroupId();
						fullName = userSearch.getFullName();
					}
					// Nếu có tồn tại session currentPage (Lấy xuống để vẫn giữ được paging)
					if (session.getAttribute(Constant.SESSION_CURRENTPAGE) != null) {
						// lấy trang từ session
						currentPage = (int) session.getAttribute(Constant.SESSION_CURRENTPAGE);
					}
					// Nếu tồn tại session sortType (Lấy xuống để vẫn giữ được các giá trị sort)
					if (session.getAttribute(Constant.SESSION_SORT_TYPE) != null) {
						// lấy giá trị từ SESSION_SORT_TYPE
						sortType = (String) session.getAttribute(Constant.SESSION_SORT_TYPE);
						// nếu là loại sắp xếp theo full_name
						if (Common.compareString(Constant.SORT_TYPE_FULLNAME, sortType)) {
							// lấy giá trị sắp xếp theo fullname từ session
							sortByFullName = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
							// nếu là loại sắp xếp theo code level
						} else if (Common.compareString(Constant.SORT_TYPE_CODELEVEL, sortType)) {
							// lấy giá trị sắp xếp theo code level từ session
							sortByCodeLevel = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
							// nếu là sắp xếp theo end_date
						} else if (Common.compareString(Constant.SORT_TYPE_ENDDATE, sortType)) {
							// lấy giá trị sắp xếp theo code level từ session
							sortByEndDate = (String) session.getAttribute(Constant.SESSION_SORT_LIKE);
						}
					}
				}
				// Lấy về totalUser
				int totalUser = userLogicImpl.getTotalUsers(groupId, fullName);
				// Lấy về totalPage
				int totalPage = Common.getTotalPage(totalUser, limit);
				// Lấy về limitPage
				int limitPage = Common.convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_PAGE),
						Constant.LIMITPAGE_DEFAULT);
				// Trường hợp người dùng sửa URL currentPage ngoài phạm vi số
				// page
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
				// Nếu không tìm thấy user nào thì in ra câu thông báo, ngược
				// lại nếu có kết quả
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
				// gán curentPage lên session
				// truyền crrentPage vào SESSION_CURRENTPAGE
				session.setAttribute("currentPage", currentPage);
				// gán lên sortType lên request
				req.setAttribute("sortTypeFullName", Constant.SORT_TYPE_FULLNAME);
				req.setAttribute("sortTypeCodeLevel", Constant.SORT_TYPE_CODELEVEL);
				req.setAttribute("sortTypeEndDate", Constant.SORT_TYPE_ENDDATE);
				// gán sortLike của các trường lên request
				req.setAttribute("sortByFullName", sortByFullName);
				req.setAttribute("sortByCodeLevel", sortByCodeLevel);
				req.setAttribute("sortByEndDate", sortByEndDate);

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
