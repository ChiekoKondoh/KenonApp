package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.HealthRecordDAO;
import model.HealthRecord;

/**
 * Servlet implementation class HealthCheckServlet
 */
@WebServlet("/HealthCheckServlet")
public class HealthCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HealthCheckServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 1. 画面（index.jsp）から値を取得
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String tempStr = request.getParameter("temp");

		// ★【追加】名前が空（null）だった場合に、プログラムが止まらないように空文字を入れる
		if (userName == null) { userName = ""; }
		
		// 2. 管理者ID（看護師モード）の判定
		if ("kango01".equals(userId)) {
			HealthRecordDAO dao = new HealthRecordDAO();
			List<HealthRecord> list = dao.findAll(); // データベースから最新のリストを取得
			request.setAttribute("recordList", list); // 取得したリストを画面に渡す
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminList.jsp");
			dispatcher.forward(request, response);
			return;
		}
		// 3. 日付と時刻を自動取得
		String currentDate = new java.text.SimpleDateFormat("MM/dd").format(new java.util.Date());
		String currentTime = new java.text.SimpleDateFormat("HH:mm").format(new java.util.Date());

		// 4. 一般患者の判定と保存
		String message = "";
		try {
			if (tempStr == null || tempStr.isEmpty()) {
				message = "体温が入力されていません。";
			} else {
				double temp = Double.parseDouble(tempStr);
				if (temp >= 37.5) {
					message = "【注意】発熱があります。ナースコールで知らせてください！";
				} else if (temp >= 37.0) {
					message = "少し高めです。経過を観察しましょう。";
				} else {
					message = "平熱です。問題ありません。";
				}
				// 1. まずは「保存するデータ」をひとまとめにする
				HealthRecord record = new HealthRecord(userId, userName, tempStr, currentDate, currentTime);

				// 2. DAO（専門スタッフ）を呼んで、データベースに保存してもらう
				HealthRecordDAO dao = new HealthRecordDAO();
				dao.create(record); // もしDAOのメソッド名が insert なら .insert(record) にしてください
			}
		} catch (NumberFormatException e) {
			message = "体温を数字で入力してください。";
		}

		//5. 結果表示へ
		request.setAttribute("userId", userId);
		request.setAttribute("userName", userName);
		request.setAttribute("temp", tempStr);
		request.setAttribute("message", message);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);
	}
}