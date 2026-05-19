<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>判定結果</title>
<style>
  body {
    /* 落ち着いた薄いグリーン */
    background-color: #d1e8d1;
    font-family: "Hiragino Maru Gothic ProN", "Yu Gothic", sans-serif;
    color: #2f4f4f; /* 文字色を濃いグレーにして読みやすく */
    margin: 20px;
  }
  h1 {
    color: #556b2f; /* 見出しをオリーブグリーンに */
    border-bottom: 2px solid #556b2f;
    padding-bottom: 5px;
  }
</style>
</head>
<body>
    <h2>判定結果</h2>
    <p>入力された体温：<strong>${temp}</strong> ℃</p> 
    <p style="color: ${temp >= 37.5 ? 'red' : 'black'};">
    <strong>${message}</strong>
    </p>
    <a href="index.jsp">戻る</a>
    
</body>
</html>