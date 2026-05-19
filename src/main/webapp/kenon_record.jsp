<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>履歴の条件選択 - KenonApp</title>
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
/* 2. 共通の「白い枠」 */
.content-wrapper {
	background-color: white;
	padding: 40px 30px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0,0,0,0.05);
	width: 400px;
	text-align: center;
}
h2 {
	color: #2C3E50;
	margin-top: 0;
}
.selection-box {
	margin: 25px 0;
	padding: 20px;
	background-color: #fcfcfc;
	border: 1px solid #B4DEBD; /* ヘッダー色の薄緑 */
	border-radius: 10px;
}
/* 日付選択欄のスタイル */
input[type="date"] {
	padding: 10px;
	font-size: 1.1em;
	border: 1px solid #ccc;
	border-radius: 5px;
	margin-top: 10px;
	color: #2C3E50;
}
/* 実行ボタン：患者様ブルー (#80A1BA) */
.submit-btn {
	width: 100%;
	padding: 15px;
	background-color: #80A1BA;
	color: white;
	border: none;
	border-radius: 10px;
	font-weight: bold;
	font-size: 1.1rem;
	cursor: pointer;
	margin-top: 20px;
	transition: opacity 0.2s;
}
.submit-btn:hover {
	opacity: 0.8;
}
.back-link {
	display: inline-block;
	margin-top: 20px;
	text-decoration: none;
	color: #95a5a6;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="content-wrapper">
		<h2>表示期間の選択</h2>
		
		<p style="color: #666; font-size: 0.95em; line-height: 1.6;">
			表示したい「開始日」を選んでください。<br> 
			その日から検温履歴を表示します。
		</p>

		<div class="selection-box">
			<form action="KenonHistoryServlet" method="get">
				<label style="color: #2C3E50; font-weight: bold;">表示開始日</label><br>
				<input type="date" name="startDate" required>
				
				<button type="submit" class="submit-btn">検温履歴を表示</button>
			</form>
		</div>

		<a href="Nurse_loginOK.jsp" class="back-link">メニューに戻る</a>
	</div>
</body>
</html>