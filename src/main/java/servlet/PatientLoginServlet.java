package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Patient;
import model.PatientLoginLogic;

@WebServlet("/PatientLoginServlet")
public class PatientLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // doPost: 画面から「ログイン」ボタンが押された時に動くメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	// 開発用デバッグ：JSPからServletへ正しくリクエストが到達したか、コンソールで確認するため
        System.out.println("Servletに信号が届きました！");
        
        request.setCharacterEncoding("UTF-8");

        // 1. 画面(JSP)から送られてきた入力値を受け取る
        String patientId = request.getParameter("patientId");
        String patientPass = request.getParameter("patientPass");
        
        // 2. 判定役(インスタンス)を準備して、executeメソッドを実行！
        PatientLoginLogic patientLoginLogic = new PatientLoginLogic();
        Patient loginPatient = patientLoginLogic.execute(patientId, patientPass);

        // 3. 判定（executeした結果、nullでなければ成功！）
        if (loginPatient != null) {
        	
            // 4.セッション（サーバー内の個人用ロッカー）にバインダー(executeの結果)を預ける
            HttpSession session = request.getSession();
            session.setAttribute("loginPatient", loginPatient);

            // 5.これまでの結果を持ったまま、成功画面(JSP)へフォワードで送る
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/patient_loginOK.jsp");
            dispatcher.forward(request, response);
            
        } else {
            //ログイン失敗時ログイン画面に戻る
            response.sendRedirect("patient_login.jsp");
        }
    }
}