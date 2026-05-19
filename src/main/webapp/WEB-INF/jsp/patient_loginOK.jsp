<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Patient"%>
<%@ include file="header.jsp" %>
<%
Patient loginPatient = (Patient) session.getAttribute("loginPatient");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功</title>
<style>
/* 1. 背景は共通の薄グリーン */
body {
	background-color: #E5EEE4;
	margin: 0;
	font-family: sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

/* 2. patient_login.jsp と同じ「白い枠」を作成 */
.content-wrapper {
	background-color: white;
	padding: 40px 30px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0,0,0,0.05);
	width: 400px; 
	text-align: center;
}

h1 {
	color: #2C3E50;
	margin-top: 0;
}

.menu-container {
	margin-top: 30px;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="content-wrapper">
		<h1>ログイン成功！</h1>
		<p>
			こんにちは、<strong><%=loginPatient.getPatientName()%></strong> さん。
		</p>

		<div class="menu-container">
			<p style="color: #666;">ご希望の操作を選択してください</p>

			<a href="kenon_input.jsp"
				style="display: block; width: 300px; padding: 15px; margin: 10px auto; background-color: #80A1BA; color: white; text-decoration: none; border-radius: 10px; text-align: center; font-weight: bold; font-size: 1.1em; box-sizing: border-box;">
				1. 体温を記録する </a> 
				
			<a href="kenon_record.jsp"
				style="display: block; width: 300px; padding: 15px; margin: 10px auto; background-color: #B4DEBD; color: #2C3E50; text-decoration: none; border-radius: 10px; text-align: center; font-weight: bold; font-size: 1.1em; box-sizing: border-box; border: 1px solid #80A1BA;">
				2. 検温履歴を見る </a>
		</div>

		<hr style="width: 80%; margin: 30px auto 20px; border: 0; border-top: 1px solid #B4DEBD;">
		<a href="index.jsp" style="color: #7f8c8d; text-decoration: none; font-size: 0.9em;">ログアウトしてトップに戻る</a>
	</div>
</body>
</html>