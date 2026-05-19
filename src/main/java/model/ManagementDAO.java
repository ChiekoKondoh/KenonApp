package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagementDAO {
    
    private final String JDBC_URL = "jdbc:h2:~/kenonApp";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    public List<Patient> findAll() {
    	//空の患者リストを準備する
        List<Patient> patientList = new ArrayList<>();

     // 全患者を対象に、最新（最大）の体温を1つだけ結合するSQL
        String sql = " SELECT M.PATIENT_ID, M.PATIENT_NAME, M.PATIENT_PASS, M.GENDER, M.BIRTHDATE, M.ROOM_NUM, T.TEMP " +
                " FROM MANAGEMENT M " +
                " LEFT JOIN TEMP_RECORDS T ON M.PATIENT_ID = T.PATIENT_ID " +
                " AND T.DATE = (SELECT MAX(DATE) FROM TEMP_RECORDS WHERE PATIENT_ID = M.PATIENT_ID) " +
                " AND T.TIME = (SELECT MAX(TIME) FROM TEMP_RECORDS WHERE PATIENT_ID = M.PATIENT_ID AND DATE = T.DATE) ";
        
        //1．データベースを開けて、SQL文をセットする
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {
            
        	//2．SQL文を実行し、結果(患者カルテの山)を受け取る
            ResultSet rs = pStmt.executeQuery();

            //3．カルテを上から１つずつめくってループ処理する(データがある限り続く)
            while (rs.next()) {
                // 患者インスタンスを準備してDBの値をセット
                Patient patient = new Patient();
                patient.setPatientId(rs.getString("PATIENT_ID"));
                patient.setPatientName(rs.getString("PATIENT_NAME"));
                patient.setPatientPass(rs.getString("PATIENT_PASS"));
                patient.setGender(rs.getInt("GENDER"));
                patient.setBirthDate(rs.getString("BIRTHDATE"));
                patient.setRoomNum(rs.getString("ROOM_NUM"));
                // 体温（まだ記録がない場合は0.0などになる）
                patient.setTemp(rs.getDouble("TEMP"));

                //完成した患者データを患者リストへ1人ずつ追加していく
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        //４．患者リスト(全員分)を処理担当さんへ返す
        return patientList;
    }
}