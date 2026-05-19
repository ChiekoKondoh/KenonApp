package model;

import java.io.Serializable;

public class HealthRecord implements Serializable {
    private String userId;
    private String userName; // 追加
    private String temp;
    private String date;     // 追加（日付）
    private String time;     // 時刻

    public HealthRecord() {}

    // 5つのデータを受け取るように修正
    public HealthRecord(String userId, String userName, String temp, String date, String time) {
        this.userId = userId;
        this.userName = userName;
        this.temp = temp;
        this.date = date;
        this.time = time;
    }

    // すべての項目の「ゲッター」を用意
    public String getUserId() { return userId; }
    public String getUserName() { return userName; }
    public String getTemp() { return temp; }
    public String getDate() { return date; }
    public String getTime() { return time; }
}