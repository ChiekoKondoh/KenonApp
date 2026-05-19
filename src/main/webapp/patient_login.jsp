<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者ログイン - KenonApp</title>
<style>
body {
	font-family: 'Helvetica Neue', Arial, sans-serif;
	background-color: #E5EEE4;
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	position: relative;
}

.login-container {
	background: white;
	border-radius: 12px; 
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
	width: 380px;
	padding: 40px 30px;
	text-align: center;
	box-sizing: border-box;
}

h1 {
	color: #2C3E50;
	font-size: 1.5rem;
	margin-bottom: 5px;
	font-weight: bold;
}

.subtitle {
	color: #666; 
	font-size: 0.9rem;
	margin-bottom: 25px;
}

.input-group {
	text-align: left;
	margin-bottom: 15px; 
}

label {
	display: block;
	color: #333;
	font-size: 0.9rem;
	margin-bottom: 5px;
	font-weight: bold;
}

/* 患者様側の入力欄を看護師側と完全に「お揃い」にする */
input[type="text"], 
input[type="password"] {
    width: 100%;
    padding: 12px;
    border: 2px solid #ccc; 
    border-radius: 6px;
    box-sizing: border-box; 
    font-size: 1rem;
    outline: none;
    transition: border-color 0.3s;
}

/* フォーカスした時の太さも合わせる */
input:focus {
    /* フォーカス時も 2px を維持して、色だけ変える */
    border-color: #80A1BA;
    box-shadow: 0 0 5px rgba(128, 161, 186, 0.3);
}
/* クリックした時は、患者様カラーの「青」で光らせる */
input:focus {
	border-color: #80A1BA;
	box-shadow: 0 0 5px rgba(128, 161, 186, 0.3);
}

.login-btn {
	width: 100%;
	padding: 12px; 
	background-color: #80A1BA;
	color: white;
	border: none;
	border-radius: 5px;
	font-weight: bold;
	font-size: 1rem;
	cursor: pointer;
	margin-top: 10px;
}

.back-link {
	display: inline-block;
	margin-top: 20px;
	text-decoration: none;
	color: #95a5a6;
	font-size: 0.85rem;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="login-container">
		<h1>👤 患者様 ログイン</h1>
		<p class="subtitle">診察券番号とパスワードを入力してください</p>

		<form action="PatientLoginServlet" method="post">
			<div class="input-group">
				<label>診察券番号</label> <input type="text" name="patientId"
					placeholder="番号を入力" required>
			</div>

			<div class="input-group">
				<label>パスワード</label> <input type="password" name="patientPass"
					placeholder="パスワードを入力" required>
			</div>

			<button type="submit" class="login-btn">ログイン</button>
		</form>
	</div>
</body>
</html>