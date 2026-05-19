package model;
import java.io.Serializable;

public class Patient implements Serializable {
    private String patientId;
    private String patientName;
    private String patientPass; // 追加
    private int gender;         // 追加
    private String birthDate;
    private String roomNum;
    private double temp; // 追加（最新体温用）

    // 1. 引数なしのコンストラクタ（これがないとエラーになることがあります）
    public Patient() {}

    // 2. 引数ありのコンストラクタ（必要に応じて）
    public Patient(String patientId, String patientName, String patientPass, int gender, String birthDate, String roomNum, double temp) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientPass = patientPass;
        this.gender = gender;
        this.birthDate = birthDate;
        this.roomNum = roomNum;
        this.temp = temp;
    }

    // --- ここからゲッターとセッター ---
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getPatientPass() { return patientPass; }
    public void setPatientPass(String patientPass) { this.patientPass = patientPass; }

    public int getGender() { return gender; }
    public void setGender(int gender) { this.gender = gender; } // ← これでDAOの赤線が消える！

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getRoomNum() { return roomNum; }
    public void setRoomNum(String roomNum) { this.roomNum = roomNum; }

    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }
}