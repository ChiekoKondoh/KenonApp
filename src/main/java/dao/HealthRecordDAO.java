package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.HealthRecord;

public class HealthRecordDAO {
    // H2 Databaseへの接続情報（環境に合わせて確認してください）
	private final String JDBC_URL = "jdbc:h2:~/kenonApp;AUTO_SERVER=TRUE";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    // 1. データベースから全件取得するメソッド
    public List<HealthRecord> findAll() {
        List<HealthRecord> recordList = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // IDの降順（新しい順）で取得
            String sql = "SELECT USER_ID, USER_NAME, TEMP, DATE, TIME FROM HEALTH_RECORDS ORDER BY ID DESC";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("USER_ID");
                String userName = rs.getString("USER_NAME");
                String temp = rs.getString("TEMP");
                String date = rs.getString("DATE");
                String time = rs.getString("TIME");
                
                HealthRecord record = new HealthRecord(userId, userName, temp, date, time);
                recordList.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return recordList;
    }

    // 2. データベースに1件保存するメソッド
    public boolean create(HealthRecord record) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            String sql = "INSERT INTO HEALTH_RECORDS(USER_ID, USER_NAME, TEMP, DATE, TIME) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            
            pStmt.setString(1, record.getUserId());
            pStmt.setString(2, record.getUserName());
            pStmt.setString(3, record.getTemp());
            pStmt.setString(4, record.getDate());
            pStmt.setString(5, record.getTime());

            int result = pStmt.executeUpdate();
            return (result == 1);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}