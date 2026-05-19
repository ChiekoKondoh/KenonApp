package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Patient;
import model.TempRecords;
import model.TempRecordsLogic;

@WebServlet("/KenonSaveServlet")
public class KenonSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("KenonSaveServletに到達しました！ 入力値: " + request.getParameter("temp"));
		
		// 1. 画面から入力された体温を受け取る
		String tempStr = request.getParameter("temp");
		double temp = Double.parseDouble(tempStr); // 文字列を数値(double)に変換

		// 2. 「誰の(体温か)」をセッションから取り出す
		HttpSession session = request.getSession();
		Patient loginPatient = (Patient) session.getAttribute("loginPatient");

		// 3. 現在の日時を取得する
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(DateTimeFormatter.ofPattern("yyy-MM-dd"));
		String time = now.format(DateTimeFormatter.ofPattern("HH:mm"));

		// 4. TempRecordsバインダーにすべての情報を詰め込む
		TempRecords tr = new TempRecords(
				loginPatient.getPatientId(),
				loginPatient.getPatientName(),
				date,
				time,
				temp);

		// 5. 保存と判定を実行
		//処理担当さんを準備する。
		TempRecordsLogic tempRecordsLogic = new TempRecordsLogic();

		// 処理担当さんにバインダー(tr)を渡して、保存を実行してもらう。
		boolean isSuccess = tempRecordsLogic.execute(tr);

		// 6. 結果によって画面を振り分ける
		if (isSuccess) {
			// 保存成功！judgeメソッドで「判定」をもらう
			String message = tempRecordsLogic.judge(temp);

			// リクエストスコープに体温と判定をセットする
			request.setAttribute("message", message);
			request.setAttribute("temp", temp);

			// これまでの情報をもったまま、jspへフォワードで送る
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/kenon_result.jsp");
			dispatcher.forward(request, response);

		} else {
			// 保存失敗にて、体温記録画面へ戻る
			response.sendRedirect("kenon_input.jsp");
		}
	}
}