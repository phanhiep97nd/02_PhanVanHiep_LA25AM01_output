<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="<c:url value = "Views/css/style.css"/>" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../js/user.js"></script>
<title>ユーザ管理</title>
</head>
<body>

	<!-- Begin vung header -->
	<%@include file="header.jsp"%>
	<!-- End vung header -->


	<!-- Begin vung dieu kien tim kiem -->
	<form action="listUser.do?type=search" method="get" name="mainform">
		<input type="hidden" value="search" name="type">
		<table class="tbl_input" border="0" width="90%" cellpadding="0"
			cellspacing="0">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>会員名称で会員を検索します。検索条件無しの場合は全て表示されます。</td>
			</tr>
			<tr>
				<td width="100%">
					<table class="tbl_input" cellpadding="4" cellspacing="0">
						<tr>
							<td class="lbl_left">氏名:</td>
							<td align="left"><input class="txBox" type="text"
								name="name" value="${fn:escapeXml(fullName)}" size="20"
								onfocus="this.style.borderColor='#0066ff';"
								onblur="this.style.borderColor='#aaaaaa';" /></td>
							<td></td>
						</tr>
						<tr>
							<td class="lbl_left">グループ:</td>
							<td align="left" width="80px"><select name="group_id">
									<option value="0">全て</option>
									<c:forEach items="${listGroup}" var="group">
										<option value="${group.getGroupId() }"  <c:if test="${groupId == group.getGroupId()}">selected</c:if>>${group.getGroupName() }</option>
									</c:forEach>
							</select></td>
							<td align="left"><input class="btn" type="submit" value="検索" />
								<input class="btn" type="button" value="新規追加" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- End vung dieu kien tim kiem -->
	</form>
	<!-- Begin vung hien thi danh sach user -->
	<table class="tbl_list" border="1" cellpadding="4" cellspacing="0"
		width="80%">

		<tr class="tr2">
			<th align="center" width="20px">ID</th>
			<th align="left">氏名 <a href="">▲▽</a>
			</th>
			<th align="left">生年月日</th>
			<th align="left">グループ</th>
			<th align="left">メールアドレス</th>
			<th align="left" width="70px">電話番号</th>
			<th align="left">日本語能力 <a href="">▲▽</a>
			</th>
			<th align="left">失効日 <a href="">△▼</a>
			</th>
			<th align="left">点数</th>
		</tr>
	
		<c:forEach items="${listUserInfo}" var="userInfor">
			<tr>
				<td align="right"><a href="ADM005.html">${fn:escapeXml(userInfor.getUserId())}</a>
				</td>
				<td>${fn:escapeXml(userInfor.getFullName())}</td>
				<td align="center">${fn:escapeXml(userInfor.getBirthday())}</td>
				<td>${fn:escapeXml(userInfor.getGroupName())}</td>
				<td>${fn:escapeXml(userInfor.getEmail())}</td>
				<td>${fn:escapeXml(userInfor.getTel())}</td>
				<td>${fn:escapeXml(userInfor.getNameLevel())}</td>
				<td align="center">${fn:escapeXml(userInfor.getEndDate())}</td>
				<td align="right">${fn:escapeXml(userInfor.getTotal())}</td>
			</tr>
		</c:forEach>

	</table>
	<c:if test="${fn:length(listUserInfo) == 0}">
	<p class="errMsg" colspan="2"style="color: red; border: 0; box-shadow: 0">&nbsp;${notiMSG005}</p>
	</c:if>
	<!-- End vung hien thi danh sach user -->

	<!-- Begin vung paging -->
	<table>
		<tr>
			<td class="lbl_paging"><a href="#">1</a> &nbsp;<a href="#">2</a>
				&nbsp;<a href="#">3</a>&nbsp;<a href="#">>></a></td>
		</tr>
	</table>
	<!-- End vung paging -->

	<!-- Begin vung footer -->
	<div class="lbl_footer">
		<%@include file="footer.jsp"%>
	</div>
	<!-- End vung footer -->

</body>

</html>