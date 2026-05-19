<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Nurse"%>
<%
Nurse loginNurse = (Nurse) session.getAttribute("loginNurse");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功</title>
<style>
.menu-container {
	margin-top: 30px;
}

.btn {
	display: inline-block;
	width: 200px;
	margin: 10px;
	padding: 15px;
	background-color: #3498db;
	color: white;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
}

.btn:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div style="text-align: center; margin-top: 50px;">
		<h1>ログイン成功！</h1>
		<p>
			お疲れ様です、<strong><%=loginNurse.getNurseName()%></strong> さん。
		</p>

		<div class="menu-container">
			<p>ご希望の操作を選択してください</p>

			<a href="management.jsp"
				style="display: block; width: 300px; padding: 15px; margin: 10px auto; background-color: #3498db; color: white; text-decoration: none; border-radius: 10px; text-align: center; font-weight: bold; font-size: 1.1em; box-sizing: border-box;">
				1. 患者一覧へ </a> 
		</div>

		<hr style="width: 50%; margin-top: 30px;">
		<a href="index.jsp" style="color: #7f8c8d;">ログアウトしてトップに戻る</a>
	</div>
</body>
</html>