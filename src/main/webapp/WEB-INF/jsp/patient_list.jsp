<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Patient"%>
<%@ include file="header.jsp" %>
<%
List<Patient> myPatients = (List<Patient>) request.getAttribute("myPatients");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>担当患者一覧 - KenonApp</title>
<style>
    /* 1. 全体の背景とレイアウト */
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
        background-color: #E5EEE4; /* 共通の薄グリーン */
        font-family: sans-serif;
    }

    body {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    /* 2. メインの白いカード */
    .content-wrapper {
        background-color: white;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.05);
        width: 95%;
        max-width: 800px;
        margin: 40px 0;
    }

    h1 {
        color: #2C3E50;
        font-size: 1.5rem;
        margin-bottom: 20px;
        text-align: center;
    }

    /* 3. テーブルデザイン（1pxの繊細な線 ＋ シマシマ） */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 10px;
        background-color: white;
        border: 1px solid #ccc;
    }

    th {
        background-color: #B4DEBD; /* 共通の薄緑ヘッダー */
        color: #2C3E50;
        padding: 12px;
        border-bottom: 2px solid #80A1BA; /* 青いアクセント線 */
        border-right: 1px solid #ccc;
        font-size: 0.9rem;
    }

    td {
        padding: 15px 12px; /* 少し高さを出してタップしやすく */
        border-bottom: 1px solid #eee;
        border-right: 1px solid #eee;
        color: #333;
        font-size: 1rem;
        text-align: center;
    }

    /* 一行おきに薄いグリーン */
    tr:nth-child(even) {
        background-color: #f0f5f0;
    }

    /* 患者名リンクを「KenonAppブルー」に */
    .patient-link {
        color: #80A1BA;
        text-decoration: none;
        font-weight: bold;
        border-bottom: 1px solid transparent;
        transition: border-bottom 0.2s;
    }

    .patient-link:hover {
        border-bottom: 1px solid #80A1BA;
    }

    /* 熱がある時の表示 */
    .temp-high {
        color: #e74c3c;
        font-weight: bold;
    }

    /* 4. 戻るボタン（落ち着いたグレー） */
    .btn-back {
        display: block;
        width: fit-content;
        margin: 30px auto 0;
        padding: 12px 25px;
        background: #9e9e9e;
        color: white;
        text-decoration: none;
        border-radius: 8px;
        font-weight: bold;
        font-size: 0.9rem;
        transition: opacity 0.2s;
    }

    .btn-back:hover {
        opacity: 0.8;
    }

    .empty-message {
        color: #e74c3c;
        text-align: center;
        font-weight: bold;
        padding: 20px;
    }
</style>
</head>
<body>
    <jsp:include page="/header.jsp" />

    <div class="content-wrapper">
        <h1>📋 今日の受け持ち患者一覧</h1>

        <%
        if (myPatients != null && !myPatients.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>部屋番号</th>
                    <th>患者名</th>
                    <th>性別</th>
                    <th>生年月日</th>
                    <th>最新体温</th>
                </tr>
            </thead>
            <tbody>
                <%
                for (Patient p : myPatients) {
                %>
                <tr>
                    <td><%= p.getRoomNum() %></td>
                    <td>
                        <a href="KenonHistoryServlet?patientId=<%= p.getPatientId() %>" class="patient-link">
                            <%= p.getPatientName() %> 様
                        </a>
                    </td>
                    <td><%= p.getGender() == 1 ? "男性" : "女性" %></td>
                    <td><%= p.getBirthDate() %></td>
                    <td>
                        <span class="<%= p.getTemp() >= 37.5 ? "temp-high" : "" %>">
                            <%= p.getTemp() %> ℃
                        </span>
                    </td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
        <%
        } else {
        %>
        <p class="empty-message">担当患者が選択されていません。<br>患者一覧から選択してください。</p>
        <%
        }
        %>

        <a href="ManagementServlet" class="btn-back">← 患者一覧に戻る</a>
    </div>
</body>
</html>