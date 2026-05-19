<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 1. リクエストスコープからデータを受け取る
    String message = (String) request.getAttribute("message");
    Double tempObj = (Double) request.getAttribute("temp");
    
    double temp = (tempObj != null) ? tempObj : 0.0;

    // 2. 体温別カラーコード
    String color = "#2C3E50"; // 基本
    if (temp >= 38.0)      { color = "#e74c3c"; } // 赤
    else if (temp >= 37.1) { color = "#e67e22"; } // オレンジ
    else if (temp >= 35.5) { color = "#27ae60"; } // 緑
    else                   { color = "#2980b9"; } // 青
%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検温結果 - KenonApp</title>
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
.result-box {
    margin: 25px 0;
    padding: 25px;
    background-color: #fcfcfc;
    border: 1px solid #B4DEBD; /* ヘッダー色の薄緑で縁取り */
    border-radius: 10px;
}
.temp-display {
    font-size: 1.2rem;
    color: #666;
    margin-bottom: 15px;
}
.temp-value {
    font-size: 2rem;
    font-weight: bold;
    color: #2C3E50;
}
/* 3. 判定メッセージ：色をJava側から制御 */
.status-msg {
    font-size: 1.3em;
    font-weight: bold;
    color: <%= color %>;
    line-height: 1.4;
    margin-top: 15px;
}
.history-btn {
    display: block;        
    width: 260px;          
    margin: 20px auto 0;  /* 「auto」を指定することで確実に中央に寄せる */
    padding: 15px;
    background-color: #80A1BA; 
    color: white;
    text-decoration: none;
    border-radius: 10px;
    font-weight: bold;
    font-size: 1.1rem;
    transition: opacity 0.2s;
}
.history-btn:hover {
    opacity: 0.8;
}
</style>
</head>
<body>
    <jsp:include page="/header.jsp" />
    
    <div class="content-wrapper">
        <h2>測定結果</h2>
        
        <div class="result-box">
            <div class="temp-display">
                今回の体温：<span class="temp-value">${temp}</span> ℃
            </div>
            <hr style="border: 0; border-top: 1px dashed #B4DEBD; margin: 15px 0;">
            <div class="status-msg"><%= message %></div>
        </div>
        
        <a href="KenonHistoryServlet" class="history-btn">履歴で確認する</a>
    </div>
</body>
</html>