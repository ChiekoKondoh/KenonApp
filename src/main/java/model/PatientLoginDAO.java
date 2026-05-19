package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientLoginDAO {
	private final String JDBC_URL = "jdbc:h2:~/kenonApp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public Patient findUser(String patientId, String patientPass) {
		// 戻り値用のバインダーを準備。DBに該当者がいなかった場合は「空(null)」のまま返すため。
		Patient patient = null; 

		System.out.println("DAO受信: ID=" + patientId + ", PASS=" + patientPass);

		String sql = "SELECT * FROM PATIENT_LOGIN WHERE PATIENT_ID = ? AND PATIENT_PASS = ?";

		try {
			Class.forName("org.h2.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, patientId);
				pstmt.setString(2, patientPass);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						// データが見つかったら、DBから各カラムの値を拾い上げる
						String id = rs.getString("PATIENT_ID");
						String name = rs.getString("PATIENT_NAME");
						String pass = rs.getString("PATIENT_PASS");

						// 拾った情報をバインダー（Patient.java）に詰める！
						patient = new Patient();
						patient.setPatientId(id);
						patient.setPatientName(name);
						patient.setPatientPass(pass);

						// 「今は使わない」と思っても、DBから取れる情報は全部入れておく！
						//patient.setGender(rs.getInt("gender"));
						//patient.setBirthDate(rs.getString("birth_date"));
						//patient.setRoomNum(rs.getString("room_num"));

						System.out.println("DBで " + name + " さんを発見しました！バインダーに格納します。");
					} else {
						System.out.println("DBに一致するデータがありませんでした...");
					}
				}
			}
		} catch (Exception e) {
			System.out.println("DAOでエラー発生！");
			e.printStackTrace();
		}
		// 成功なら中身入りの、失敗なら空(null)のバインダーを返す
		return patient;
	}
}