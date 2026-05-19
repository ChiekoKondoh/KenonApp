package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.PatientListLogic;

@WebServlet("/PatientListServlet")
public class PatientListServlet extends HttpServlet {
	// doPst : 画面のボタンを押した時に動くメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. チェックされた複数の患者ID(selectedIds)を配列で受け取る
        String[] selectedIds = request.getParameterValues("selectedIds");

     // 2. IDが1つでも選択されていたら、Logicを動かしてDBから情報を取る
        if (selectedIds != null) {
        	
        	//処理担当さんを準備する
            PatientListLogic logic = new PatientListLogic();
            
            //処理担当さんに、複数の患者IDを渡して実行してもらい「私の担当患者リスト」に詰めてもらう
            java.util.List<model.Patient> myPatients = logic.execute(selectedIds);
            
            // 3. 取得した「私の担当患者リスト」をリクエストスコープに保存
            request.setAttribute("myPatients", myPatients);
        }
	
        // 3. 次の画面（担当患者一覧）へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/patient_list.jsp");
        dispatcher.forward(request, response);
    }
    
    // 以前作った doGet も残しておくと、リンククリック時に便利です
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // 1. JSPのリンクから送られてきた ID を受け取る
        String patientId = request.getParameter("patientId");

        // 2. 動作確認（コンソールにIDが出れば第一関門突破！）
        System.out.println("詳細を表示する患者ID: " + patientId);

        // ※ 本来はここで Logic を呼び出しますが、まずは画面遷移を確認しましょう
        
        // 3. patient_list.jsp へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/patient_list.jsp");
        dispatcher.forward(request, response);
    }
}