package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ManagementLogic;
import model.Patient;

@WebServlet("/ManagementServlet")
public class ManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Logicに「患者リストを持ってきて」と頼む
    	//管理人さんを準備する
        ManagementLogic logic = new ManagementLogic();
        //管理人さんに患者リスト(全員分)持ってきてもらい、患者リストに詰める
        List<Patient> patientList = logic.execute();
        
        System.out.println("--- デバッグ開始 ---");
        System.out.println("取得した患者数: " + (patientList != null ? patientList.size() : "null"));
        
        if (patientList != null && patientList.size() > 0) {
            System.out.println("最初の患者名: " + patientList.get(0).getPatientName());
        }
        System.out.println("--- デバッグ終了 ---");
        
        // 2. 届いたリストを「全員分の名簿（request）」としてセットする
        // リクエストスコープで患者リスト(全員分)を送って保存する
        request.setAttribute("patientList",patientList);
        
        // 3. 画面表示JSPへフォワード(安全な場所へご案内)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/management.jsp");
        dispatcher.forward(request, response);
    }
}