package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TempRecordsDAO {
	private final String JDBC_URL = "jdbc:h2:~/kenonApp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//体温記録のバインダー(tr)を保存する
	public boolean create(TempRecords tr) {
		//DBに保存できなかった場合は「false」を返すため。
		boolean result = false;

		String sql = "INSERT INTO TEMP_RECORDS (PATIENT_ID, PATIENT_NAME, DATE, TIME, TEMP) VALUES (?, ?, ?, ?, ?)";
		try {
			Class.forName("org.h2.Driver");
			
			System.out.println("実行直前のSQL: " + sql);
			
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				// バインダー（tr）から情報を取り出して、SQLの「？」にセットする
				pstmt.setString(1, tr.getPatientId());
				pstmt.setString(2, tr.getPatientName());
				pstmt.setString(3, tr.getDate());
				pstmt.setString(4, tr.getTime());
				pstmt.setDouble(5, tr.getTemp());

				// SQLを実行！
				int rows = pstmt.executeUpdate();

				// 1件以上更新（保存）されたら成功
				if (rows > 0) {
					result = true;
					
					System.out.println("DBへの検温記録の保存に成功しました！");
				}

			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DAOで保存エラーが発生しました！");
			e.printStackTrace();
		}
		return result;
	}
	
	// 検温履歴を探す
	public List<TempRecords> findByDateRange(String patientId, String startDate) {
		
		List<TempRecords> historyList = new ArrayList<>();
		
		String sql = "SELECT * FROM TEMP_RECORDS WHERE PATIENT_ID = ? AND (? IS NULL OR DATE >= ?) ORDER BY DATE DESC, TIME DESC";
		
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
		     PreparedStatement pstmt = conn.prepareStatement(sql)) {

		    pstmt.setString(1, patientId);
		    pstmt.setString(2, startDate);
		    pstmt.setString(3, startDate); // 3番目の ? (比較用)
		    
		    try (ResultSet rs = pstmt.executeQuery()) {
		    	
		    	//条件に合う検温履歴を、上から順に探す(全件ループ)
		        while (rs.next()) {
		        	
		            System.out.println("データを見つけました！: " + rs.getDouble("TEMP"));
		            //検温履歴が見つかったら、DBから各カラムの値を拾い上げ、recordバインダーへ情報を詰める。
					TempRecords record = new TempRecords();
					record.setPatientId(rs.getString("PATIENT_ID"));
					record.setPatientName(rs.getString("PATIENT_NAME"));
					record.setDate(rs.getString("DATE"));
					record.setTime(rs.getString("TIME"));
					record.setTemp(rs.getDouble("TEMP"));
					//recordバインダーの中身をリストに加える
					historyList.add(record);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//成功なら中身入りの、リストを返す
		return historyList;
	}
}
		
	

	   
		