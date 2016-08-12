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
	<!-- 注意!!!enctype="multipart/form-data"时，无法通过参数绑定获取参数 -->
	<form
		action="${pageContext.request.contextPath}/items/editItemsSubmit.action"
		method="post">
		<c:forEach items="${itemsList }" var="item" varStatus="status">
			<input type="hidden" name="itemsList[${status.index }].id"
				value="${item.id }" /> 修改商品信息：
		<table width="100%" border=1>
				<tr>
					<td>商品名称</td>
					<td><input type="text" name="itemsList[${status.index }].name"
						value="${item.name }" />
					</td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td><input type="text"
						name="itemsList[${status.index }].price" value="${item.price }" />
					</td>
				</tr>
				<tr>
					<td>商品生产日期</td>
					<td><input type="text"
						name="itemsList[${status.index }].createtime"
						value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</td>
				</tr>
				<%-- <tr>
	<td>商品图片</td>
	<td>
		<c:if test="${item.pic !=null}">
			<img src="/pic/${item.pic}" width=100 height=100/>
			<br/>
		</c:if>
		<input type="file"  name="pictureFile"/> 
	</td>
</tr> --%>
				<tr>
					<td>商品简介</td>
					<td><textarea rows="3" cols="30"
							name="itemsList[${status.index }].detail">${item.detail }</textarea>
					</td>
				</tr>
			</table>
		</c:forEach>
		<input type="submit" value="提交" />
	</form>
</body>

</html>