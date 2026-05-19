package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class PatientListDAO {
    private final String JDBC_URL = "jdbc:h2:~/kenonApp"; // あなたの環境に合わせてください
    private final String DB_USER = "sa";
    private final String DB_PASS = "";
    
    public List<Patient> findSelectedPatients(String[] selectedIds) {
    	//空の患者リストを準備する
    	List<Patient> patientList = new ArrayList<>();

        StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < selectedIds.length; i++) {
            sj.add("?");
        }

     
        // 1. LEFT JOINで患者情報と検温記録を結合
        // 2. 副問合せ(MAX DATE/TIME)により、数値の大きさではなく「最新の日時」の1件のみを特定
        // 3. 現場の動線を考慮し、部屋番号(ROOM_NUM)の昇順でソート
        String sql = " SELECT M.PATIENT_ID, M.PATIENT_NAME, M.PATIENT_PASS, M.GENDER, M.BIRTHDATE, M.ROOM_NUM, T.TEMP " +
                " FROM MANAGEMENT M " +
                " LEFT JOIN TEMP_RECORDS T ON M.PATIENT_ID = T.PATIENT_ID " +
                " AND T.DATE = (SELECT MAX(DATE) FROM TEMP_RECORDS WHERE PATIENT_ID = M.PATIENT_ID) " +
                " AND T.TIME = (SELECT MAX(TIME) FROM TEMP_RECORDS WHERE PATIENT_ID = M.PATIENT_ID AND DATE = T.DATE) " +
                " WHERE M.PATIENT_ID IN ( " + sj.toString() + " ) " +
                " GROUP BY M.PATIENT_ID, M.PATIENT_NAME, M.PATIENT_PASS, M.GENDER, M.BIRTHDATE, M.ROOM_NUM, T.TEMP " +
                " ORDER BY M.ROOM_NUM ASC ";
        
      //1．データベースを開けて、SQL文をセットする
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            // ? に患者IDを順番にセットする
            for (int i = 0; i < selectedIds.length; i++) {
                pStmt.setString(i + 1, selectedIds[i]);
            }

            //２．SQL文を実行し、選ばれた患者のカルテの山を受け取る
            ResultSet rs = pStmt.executeQuery();
            
            // ★【重複防止】一度リストに入れた患者さんを記憶しておくための「メモ帳」
            Set<String> addedIds = new HashSet<>();
            
            //３．カルテの山を上から1枚ずつめくってループ処理
            while (rs.next()) {
                String patientId = rs.getString("PATIENT_ID");
                
                // 【二重チェック】もしすでにメモ帳にこのIDがあれば、ダブりなのでこの患者さんは飛ばして次へ！
                if (addedIds.contains(patientId)) {
                    continue; 
                }
                
                String patientName = rs.getString("PATIENT_NAME");
                String patientPass = rs.getString("PATIENT_PASS");
                int gender = rs.getInt("GENDER");
                String birthDate = rs.getString("BIRTHDATE");
                String roomNum = rs.getString("ROOM_NUM");
                double temp = rs.getDouble("TEMP");

                //患者インスタンスを作って、正式な「私の担当患者リスト」へ追加
                Patient p = new Patient(patientId, patientName, patientPass, gender, birthDate, roomNum, temp);
                patientList.add(p);
                
                // 【メモに控える】次に同じ人が出てきても弾けるように、メモ帳にIDを書いておく
                addedIds.add(patientId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return patientList;
    }
}