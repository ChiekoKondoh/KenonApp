<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Patient"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>患者管理一覧 - KenonApp</title>
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
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
	width: 95%;
	max-width: 900px;
	margin: 40px 0;
}

h1 {
	color: #2C3E50;
	font-size: 1.5rem;
	margin-bottom: 5px;
}

.user-info {
	color: #666;
	font-size: 0.9rem;
	margin-bottom: 25px;
	border-bottom: 1px solid #eee;
	padding-bottom: 10px;
}

/* 3. 表のデザイン */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
	background-color: white;
	border: 1px solid #ccc;
}

th {
	background-color: #B4DEBD;
	color: #2C3E50;
	padding: 12px;
	border-bottom: 2px solid #80A1BA;
	border-right: 1px solid #ccc;
	font-size: 0.9rem;
}

td {
	padding: 12px;
	border-bottom: 1px solid #dcdcdc;
	border-right: 1px solid #eee;
	color: #333;
	font-size: 0.95rem;
}

tr:nth-child(even) {
	background-color: #f0f5f0;
}

tr:last-child td {
	border-bottom: none;
}

.temp-high {
	color: #e74c3c; /* 発熱は赤 */
	font-weight: bold;
}

/* 4. 看護師用確定ボタン（ピンク #DC9B9B） */
.submit-btn {
	margin-top: 25px;
	padding: 15px 30px;
	background-color: #DC9B9B; /* ピンク */
	color: white;
	border: none;
	border-radius: 10px;
	cursor: pointer;
	font-size: 1rem;
	font-weight: bold;
	width: 100%; /* スマホやタブレットでも押しやすく */
	transition: opacity 0.2s;
}

.submit-btn:hover {
	opacity: 0.8;
}

.detail-link {
	color: #80A1BA;
	text-decoration: none;
	font-weight: bold;
}
</style>
</head>
<body>
	<jsp:include page="/header.jsp" />

	<div class="content-wrapper">
		<header>
			<h1>🏥 患者管理システム</h1>
			<div class="user-info">
				ログイン：<strong><%= session.getAttribute("loginUser") != null ? ((model.Nurse) session.getAttribute("loginUser")).getNurseName() : "ゲスト" %></strong>
				様
			</div>
		</header>

		<main>
			<h3 style="color: #2C3E50;">入院患者一覧</h3>
			<form action="PatientListServlet" method="post">
				<table>
					<thead>
						<tr>
							<th>選択</th>
							<th>病室</th>
							<th>患者ID</th>
							<th>氏名</th>
							<th>性別</th>
							<th>生年月日</th>
							<th>最新体温</th>
							<th>詳細</th>
						</tr>
					</thead>
					<tbody>
						<%
                        List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
                        if (patientList != null && !patientList.isEmpty()) {
                            for (Patient p : patientList) {
                        %>
						<tr>
							<td style="text-align: center;"><input type="checkbox"
								name="selectedIds" value="<%= p.getPatientId() %>"
								style="transform: scale(1.2);"></td>
							<td><%= p.getRoomNum() %></td>
							<td><%= p.getPatientId() %></td>
							<td><strong><%= p.getPatientName() %></strong></td>
							<td><%= p.getGender() == 1 ? "男" : "女" %></td>
							<td><%= p.getBirthDate() %></td>
							<td><span
								class="<%= p.getTemp() >= 37.5 ? "temp-high" : "" %>"> <%= p.getTemp() %>
									℃
							</span></td>
							<td style="text-align: center;"><a
								href="PatientListServlet?patient_id=<%= p.getPatientId() %>"
								class="detail-link">表示</a></td>
						</tr>
						<%
                            }
                        } else {
                        %>
						<tr>
							<td colspan="8" style="text-align: center; padding: 30px;">患者データが見つかりません。</td>
						</tr>
						<%
                        }
                        %>
					</tbody>
				</table>

				<button type="submit" class="submit-btn">担当患者を確定してマイリストへ</button>
			</form>
		</main>
	</div>
</body>
</html>