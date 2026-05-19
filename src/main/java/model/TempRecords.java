package model;

import java.io.Serializable;

/**
 * 検温記録を保持するJavaBeans
 * 「いつ」「誰が」「何度だったか」をひとまとめにします
 */
public class TempRecords implements Serializable {
    private String patientId;   // 患者ID（誰の）
    private String patientName; // 患者氏名（誰の）
    private String date;        // 日付（いつ）
    private String time;        // 時間（何時に）
    private double temp;        // 体温（何度）

    // 基本のコンストラクタ（JavaBeansの作法）
    public TempRecords() {}

    // データをセットするためのコンストラクタ
    public TempRecords(String patientId, String patientName, String date, String time, double temp) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
        this.temp = temp;
    }

    // ゲッターとセッター（各ポケットの出し入れ口）
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }
}