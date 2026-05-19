<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>看護師ログイン - KenonApp</title>
<style>
/* 1. 背景は共通の薄グリーン */
body {
    background-color: #E5EEE4;
    margin: 0;
    padding: 0;
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
    width: 350px;
    text-align: center;
}

h2 {
    color: #2C3E50;
    margin-top: 0;
}

.login-form {
    text-align: left;
    margin-top: 25px;
}

.input-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 8px;
    color: #2C3E50;
    font-weight: bold;
    font-size: 0.9em;
}
/* 入力枠のスタイル */
input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 12px;
    box-sizing: border-box;
    border: 1px solid #ccc; 
    border-radius: 6px;
    font-size: 1em;
    outline: none; 
    transition: border-color 0.3s;
}

/* クリックした時（フォーカス時）の色を指定 */
input[type="text"]:focus,
input[type="password"]:focus {
    border: 2px solid #B4DEBD; 
}
/* 3. 看護師用ボタン：ピンク (#DC9B9B) */
.login-btn {
    width: 100%;
    padding: 15px;
    background-color: #DC9B9B; 
    color: white;
    border: none;
    border-radius: 10px;
    font-weight: bold;
    font-size: 1.1rem;
    cursor: pointer;
    margin-top: 10px;
    transition: opacity 0.2s;
}

.login-btn:hover {
    opacity: 0.8;
}

.description {
    color: #666;
    font-size: 0.9em;
    margin-bottom: 20px;
}
</style>
</head>
<body>
    <jsp:include page="/header.jsp" />

    <div class="content-wrapper">
        <h2>看護師ログイン</h2>
        <p class="description">看護師IDとパスワードを入力してください</p>

        <div class="login-form">
            <form action="NurseLoginServlet" method="post">
                <div class="input-group">
                    <label>看護師ID</label>
                    <input type="text" name="nurseId" required placeholder="IDを入力">
                </div>

                <div class="input-group">
                    <label>パスワード</label>
                    <input type="password" name="nursePass" required placeholder="パスワードを入力">
                </div>

                <button type="submit" class="login-btn">ログイン</button>
            </form>
        </div>
    </div>
</body>
</html>