<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Patient" %>
<%@ include file="WEB-INF/jsp/header.jsp" %>
<%
    Patient loginPatient = (Patient) session.getAttribute("loginPatient");
    
    // 万が一ログインせずにこのページに来た場合の安全策
    if (loginPatient == null) {
        response.sendRedirect("patient_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検温入力 - KenonApp</title>
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

/* 2. patient_loginOK.jsp と共通の「白い枠」 */
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

.input-box {
    margin: 25px 0;
    padding: 20px;
    background-color: #fcfcfc;
    border: 1px solid #B4DEBD; /* ヘッダー色の薄緑で縁取り */
    border-radius: 10px;
}

/* 入力欄のスタイル：少し大きくして入力しやすく */
input[type="number"] {
    width: 100px;
    padding: 10px;
    font-size: 1.5em;
    border: 1px solid #ccc;
    border-radius: 5px;
    text-align: center;
}

/* 記録するボタン：患者様ブルー (#80A1BA) */
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
    <jsp:include page="/header.jsp" />
    
    <div class="content-wrapper">
        <h2>検温記録の入力</h2>
        <p><strong><%= loginPatient.getPatientName() %></strong> さん</p>
        
        <div class="input-box">
            <form action="KenonSaveServlet" method="post">
                <p style="color: #666; margin-bottom: 15px;">現在の体温を入力してください</p>
                <input type="number" name="temp" step="0.1" min="34.0" max="43.0" required> 
                <span style="font-size: 1.2em; font-weight: bold; color: #2C3E50;"> ℃</span>
                <br><br>
                <input type="submit" value="記録する" class="submit-btn">
            </form>
        </div>
        
        <a href="index.jsp" class="back-link">メニューに戻る</a>
    </div>
</body>
</html>