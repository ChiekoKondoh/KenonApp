package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NurseLoginDAO {
	private final String JDBC_URL = "jdbc:h2:~/kenonApp";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public Nurse findUser(String inputId, String inputPass) {
		// 戻り値用のバインダーを準備。DBに該当者がいなかった場合は「空(null)」のまま返すため。
		Nurse nurse = null; 

		System.out.println("DAO受信: ID=" + inputId + ", PASS=" + inputPass);

		String sql = "SELECT * FROM NURSE_LOGIN WHERE NURSE_ID = ? AND NURSE_PASS = ?";

		try {
			Class.forName("org.h2.Driver");
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
					PreparedStatement pstmt = conn.prepareStatement(sql)) {

				pstmt.setString(1, inputId);
				pstmt.setString(2, inputPass);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						// データが見つかったら、DBから各カラムの値を拾い上げる
						String nurseId = rs.getString("NURSE_ID");
						String nurseName = rs.getString("NURSE_NAME");
						String nursePass = rs.getString("NURSE_PASS");

						// 拾った情報をバインダー（Nurse.java）に詰める
						nurse = new Nurse(nurseId, nurseName, nursePass);

						System.out.println("DBで " + nurseName + " さんを発見しました！バインダーに格納します。");
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
		return nurse;
	}
}