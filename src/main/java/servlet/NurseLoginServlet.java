package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession; // 追加：セッションを使うために必要

import model.Nurse;
import model.NurseLoginLogic;

@WebServlet("/NurseLoginServlet")
public class NurseLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // doPost: 画面から「ログイン」ボタンが押された時に動くメソッド
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	// 開発用デバッグ：JSPからServletへ正しくリクエストが到達したか、コンソールで確認するため
        System.out.println("Servletに信号が届きました！");
        
        request.setCharacterEncoding("UTF-8");

        // 1. 画面(JSP)から送られてきた入力値を受け取る
        String nurseId = request.getParameter("nurseId");
        String nursePass = request.getParameter("nursePass");
        
        // 2. 判定役(インスタンス)を準備して、executeメソッドを実行！
        NurseLoginLogic NurseLoginLogic = new NurseLoginLogic();
        Nurse loginNurse = NurseLoginLogic.execute(nurseId, nursePass);

        // 3. 判定（バインダーが null でなければ成功！）
        if (loginNurse != null) {
            // 🏥 セッション（サーバー内の個人用ロッカー）にバインダー(executeの結果)を預ける
            HttpSession session = request.getSession();
            session.setAttribute("loginUser",loginNurse);

            // 成功画面へ
            response.sendRedirect("ManagementServlet");
        } else {
            // 失敗したらログイン画面に戻る
            response.sendRedirect("nurse_login.jsp");
        }
    }
}