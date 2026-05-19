package servlet;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/KenonHistoryServlet")
public class KenonHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. 「誰の(検温履歴か)」セッションから取り出す
		HttpSession session = request.getSession();
		String patientId = null;
		
		// 1. まずはパラメータから、看護師さんがクリックしたことを確認
	    String paramId = request.getParameter("patientId");
	    
	    if (paramId != null) {
	        // 看護師さんが一覧から選んだ場合
	        patientId = paramId;
	    } else {
	        // パラメータがない＝患者さん本人がログインして見ている場合
		    Patient loginPatient = (Patient) session.getAttribute("loginPatient");
		    if (loginPatient != null) {
	            patientId = loginPatient.getPatientId();
	        }
	    }
	    // 2. 最終チェック：どっちの方法でもIDが手に入らなかったらアウト
	    if (patientId == null) {
			response.sendRedirect("index.jsp");
			return;
		}
	    // 3. 確定した patientId を使って履歴を取得
	    //処理担当さんを準備する
	    TempRecordsLogic tempRecordsLogic = new TempRecordsLogic();
	    //画面の入力欄(name="startDate")に書かれた日付を取り出す
	    String startDate = request.getParameter("startDate");
	    //処理担当さんに患者IDと日付を渡して、検温履歴を取得し、リストに詰める。
	    List<TempRecords> historyList = tempRecordsLogic.getHistory(patientId, startDate);

		// 取得したリストをリクエストスコープで保存
		request.setAttribute("historyList", historyList);

		// 履歴表示JSPへフォワード(安全な場所へご案内)
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/kenon_history.jsp");
		dispatcher.forward(request, response);
	}
}