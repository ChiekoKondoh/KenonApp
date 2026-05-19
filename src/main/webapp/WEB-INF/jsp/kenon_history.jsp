<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.TempRecords"%>
<%@ include file="header.jsp" %>
<%
// 1. サーブレットから届いたリストを受け取る
List<TempRecords> list = (List<TempRecords>) request.getAttribute("historyList");

// 2. 名前を表示するための準備
String dispName = "患者";
if (list != null && !list.isEmpty()) {
	// リストの1件目から名前を取り出す
	dispName = list.get(0).getPatientName();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検温履歴 - KenonApp</title>
<style>
/* 背景を画面全体に隙間なく塗る設定 */
body {
	background-color: #E5EEE4; 
	margin: 0;
	padding: 0;
	font-family: sans-serif;
	display: flex;
	flex-direction: column;
	align-items: center;
	min-height: 100vh; 
	width: 100%; 
}

/* 白い枠 */
.content-wrapper {
	background-color: white;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
	width: 90%;
	max-width: 600px; 
	margin: 40px 20px;
	text-align: center;
}

h1 {
	color: #2C3E50;
	margin-top: 0;
	font-size: 1.5rem;
}

/* テーブル全体 */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background-color: white;
	border: 1px solid #bdc3c7;
}

th {
	background-color: #B4DEBD; 
	color: #2C3E50;
	padding: 12px;
	border-bottom: 2px solid #80A1BA;
	border-right: 1px solid #bdc3c7;
}

td {
	padding: 12px;
	border-bottom: 1px solid #dcdcdc;
	border-right: 1px solid #dcdcdc;
	color: #333;
}

th:last-child, td:last-child {
	border-right: none;
}

tr:nth-child(even) {
	background-color: #f2f7f2; /* 薄いグリーンの縞々に変更 */
}

.high-temp {
	color: #e74c3c;
	font-weight: bold;
}

.back-link {
	display: inline-block;
	margin-top: 25px;
	text-decoration: none;
	color: #95a5a6;
	font-size: 0.9em;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="content-wrapper">
		<header>
			<%-- 患者名を大きく、安心感のある太字で表示 --%>
			<h1 style="color: #2C3E50; margin-bottom: 5px;">
				<%=dispName%>
				様
			</h1>
			<p style="color: #666; font-size: 1rem; margin-top: 0;">検温履歴一覧</p>
		</header>

		<table>
			<thead>
				<tr>
					<th>日付</th>
					<th>時刻</th>
					<th>体温</th>
					<th>判定</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<TempRecords> historylist = (List<TempRecords>) request.getAttribute("historyList");

				if (list != null && !list.isEmpty()) {
					for (TempRecords record : list) {
				%>
				<tr>
					<td><%=record.getDate()%></td>
					<td><%=record.getTime()%></td>
					<td><span
						class="<%=record.getTemp() >= 37.5 ? "high-temp" : ""%>"> <%=record.getTemp()%>
							℃
					</span></td>
					<td>
						<%
						if (record.getTemp() >= 37.5) {
						%> <span class="high-temp">⚠️ 発熱</span> <%
 } else {
 %> <span style="color: #27ae60;">平熱</span> <%
 }
 %>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="4">記録がありません。</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>