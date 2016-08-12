<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
<script type="text/javascript">
	function queryItems() {
		document.form1.action = "${pageContext.request.contextPath }/items/queryItems.action";
		document.form1.submit();
	}

	function deleteItems() {
		document.form1.action = "${pageContext.request.contextPath }/items/deleteItems.action";
		document.form1.submit();
	}

	function editItems() {
		document.form1.action = "${pageContext.request.contextPath }/items/editItems.action";
		document.form1.submit();
	}

	function sel() {
		var check = document.getElementById("selectAll");
		if (check.checked == true) {
			selectAll();
		} else {
			selectNone();
		}

	}

	function selectAll() {
		var options = document.getElementsByName("itemsId");
		for ( var i = 0; i < options.length; i++) {
			options[i].checked = true;
		}
	}

	function selectNone() {
		var options = document.getElementsByName("itemsId");
		for ( var i = 0; i < options.length; i++) {
			options[i].checked = false;
		}
	}
</script>
</head>
<body>
	<h1>查询商品</h1>
	<c:if test="${username != null }">
		当前用户：${username }&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="${pageContext.request.contextPath }/logout.action">退出</a>
	</c:if>
	<form name="form1" method="post">
		<table width="100%" border=1>
			<tr>
				<td>查询条件：商品类型： <select name="itemtype">
						<c:forEach items="${itemtypes }" var="itemtype">
							<option value="${itemtype.key }">${itemtype.value }</option>
						</c:forEach>
				</select> <input type="text" name="itemsCustom.name" /> <input type="button"
					value="查询" onclick="queryItems()" /></td>
			</tr>
			<tr>
				<td><input id="selectAll" type="checkbox" onclick="sel()" />全选/全不选：<input
					type="button" value="删除" onclick="deleteItems()" /> <input
					type="button" value="修改" onclick="editItems()" /></td>
			</tr>
		</table>
		商品列表：
		<table width="100%" border=1>
			<tr>
				<td>选择</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${itemsList }" var="item">
				<tr>
					<td><input type="checkbox" name="ids" value="${item.id}" />
					</td>
					<td>${item.name }</td>
					<td>${item.price }</td>
					<td><fmt:formatDate value="${item.createtime}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${item.detail }</td>
					<td><a
						href="${pageContext.request.contextPath }/items/editItem.action?id=${item.id}">修改</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>

</html>