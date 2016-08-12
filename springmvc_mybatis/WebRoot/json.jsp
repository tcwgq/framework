<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>json交互的测试</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	//请求json，输出json
	function requestJson() {
		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath }/requestJson.action',
			contentType : 'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data : '{"name":"手机","price":999}',
			success : function(data) {//返回json结果
				alert(data.name);
			}

		});
	}
	//请求key/value，输出json
	function responseJson() {
		$.ajax({
			type : 'post',
			url : '${pageContext.request.contextPath }/responseJson.action',
			//contentType : 'application/json;charset=utf-8',
			//数据格式是json串，商品信息
			data : 'name=笔记本&price=5288',
			success : function(data) {//返回json结果
				alert(data.name);
			}

		});
	}
</script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<input type="button" onclick="requestJson()" value="请求json，输出json" />
	<input type="button" onclick="responseJson()"
		value="请求key/value，输出json" />
	<br>
</body>
</html>
