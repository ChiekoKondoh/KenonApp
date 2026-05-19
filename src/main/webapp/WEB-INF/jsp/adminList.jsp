<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.HealthRecord" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面 - 検温一覧</title>
<style>
  body { 
    /* 桜の花びらのような、極めて淡いピンク */
    background-color: #fff5f7; 
    font-family: "Hiragino Maru Gothic ProN", "Yu Gothic", sans-serif;
    color: #2f4f4f; 
    margin: 20px;
  }
  h2 { 
    color: #d81b60; /* 見出しは少しハッキリさせて、視認性を確保 */
    border-bottom: 2px solid #f8bbd0;
    padding-bottom: 8px;
  }
  table {
    border-collapse: collapse; 
    width: 100%; 
    background-color: #ffffff;
    border: 1px solid #f8bbd0;
  }
  th {
    /* テーブルの見出しも、背景に合わせて淡いピンクに */
    background-color: #fdf2f4; 
    color: #ad1457;
    padding: 12px;
    border: 1px solid #f8bbd0;
  }
  td {
    padding: 10px;
    border: 1px solid #fdf2f4;
  }
</style>
</head>
<body>

<h2>【管理者専用】検温結果一覧</h2>

<table border="1">
    <tr>
        <th>患者ID</th>
        <th>氏名</th>
        <th>日付</th>
        <th>測定時刻</th>
        <th>測定した体温</th>
    </tr>
    <%
        List<HealthRecord> recordList = (List<HealthRecord>) request.getAttribute("recordList");
        
        if (recordList != null && !recordList.isEmpty()) {
            for (HealthRecord record : recordList) {
    %>
    <tr>
        <td><%= record.getUserId() %></td>
        <td><%= record.getUserName() %></td>
        <td><%= record.getDate() %></td>
        <td><%= record.getTime() %></td>
        <td style="<%= Double.parseDouble(record.getTemp()) >= 37.5 ? "color: red; font-weight: bold;" : "" %>">
            <%= record.getTemp() %> ℃
        </td>
    </tr>
    <%
            }
        } else {
    %>
    <tr>
        <td colspan="5">記録がありません。</td>
    </tr>
    <%
        }
    %>
</table>

<p style="margin-top: 20px;">
    <a href="index.jsp">入力画面に戻る</a>
</p>

</body>
</html>