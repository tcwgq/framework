<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>

</head>
<body>
	<h1>修改商品信息</h1>
	<c:forEach items="${errors }" var="error">
		<font color="red">${error.defaultMessage }</font>
	</c:forEach>
	<!-- 注意!!!enctype="multipart/form-data"时，无法通过参数绑定获取参数 -->
	<form id="itemForm"
		action="${pageContext.request.contextPath}/items/editItemSubmit.action"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${item.id }" /> 修改商品信息：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<td><input type="text" name="name" value="${item.name }" />
				</td>
			</tr>
			<tr>
				<td>商品价格</td>
				<td><input type="text" name="price" value="${item.price }" />
				</td>
			</tr>
			<tr>
				<td>商品生产日期</td>
				<td><input type="text" name="createtime"
					value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</td>
			</tr>
			<tr>
				<td>商品图片</td>
				<td><c:if test="${item.pic !=null}">
						<!-- 用于不更新图片时保存图片信息 -->
						<input type="hidden" name="pic" value="${item.pic }" />
						<img src="/pic/${item.pic}" width=100 height=100 />
						<br />
					</c:if> <input type="file" name="items_pic" /></td>
			</tr>
			<tr>
				<td>商品简介</td>
				<td><textarea rows="3" cols="30" name="detail">${item.detail }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="提交" />
				</td>
			</tr>
		</table>
	</form>
</body>

</html>