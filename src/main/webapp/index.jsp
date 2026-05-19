<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KenonApp - ホーム</title>
<style>
    body {
        font-family: 'Helvetica Neue', Arial, sans-serif;
        background-color: #E5EEE4; /* 背景は明るく */
        margin: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    /* ヘッダーの代わりにカード上部に色を付ける */
    .menu-container {
        background: white;
        padding: 0; /* paddingを一度0にします */
        border-radius: 20px;
        box-shadow: 0 10px 25px rgba(0,0,0,0.05);
        text-align: center;
        width: 380px;
        overflow: hidden; /* 角丸からはみ出さないように */
    }

    /* 上部にベース色のラインを入れる（header.jspの代わり） */
    .card-top-line {
        background-color: #B4DEBD; /* パレットの薄いグリーン */
        height: 15px;
        width: 100%;
    }

    .card-content {
        padding: 40px 30px;
    }

    h1 {
        color: #80A1BA; /* パレットの落ち着いた青 */
        font-size: 1.6rem;
        margin-bottom: 5px;
    }

    h2 {
        color: #7f8c8d;
        font-size: 1rem;
        margin-bottom: 30px;
        font-weight: normal;
    }

    .menu-button {
        display: block;
        text-decoration: none;
        padding: 20px;
        margin: 15px 0;
        border-radius: 15px;
        font-weight: bold;
        transition: transform 0.2s, box-shadow 0.2s;
        color: white;
    }

    .menu-button:hover {
        transform: translateY(-3px);
        box-shadow: 0 5px 15px rgba(0,0,0,0.1);
    }

    /* 患者様ボタン：青ベース */
    .patient-btn {
        background-color: #80A1BA;
    }

    /* 看護師ボタン：濃いピンク */
    .nurse-btn {
        background-color: #DC9B9B;
    }

    .description {
        display: block;
        font-size: 0.8rem;
        font-weight: normal;
        margin-top: 5px;
        opacity: 0.9;
    }
</style>
</head>
<body>

<div class="menu-container">
    <div class="card-top-line"></div> <div class="card-content">
        <h1>🏥 検温管理システム</h1>
        <h2>ご利用の方を選択してください</h2>

        <a href="patient_login.jsp" class="menu-button patient-btn">
            👤 患者様入口
            <span class="description">体温の記録・履歴確認</span>
        </a>

        <a href="nurse_login.jsp" class="menu-button nurse-btn">
            🏥 看護師専用
            <span class="description">患者管理・一覧確認</span>
        </a>
    </div>
</div>

</body>
</html>