package model;

import java.io.Serializable;

/**
 * 患者情報を保持するJavaBeans
 * DBの各カラムに対応するフィールドを持たせます
 */
public class Nurse implements Serializable {
    private String nurseId;   // 患者ID
    private String nurseName; // 患者氏名
    private String nursePass; // パスワード（必要に応じて保持）

    // 基本のコンストラクタ
    public Nurse() {}

    // 全データをセットするコンストラクタ
    public Nurse(String nurseId, String nurseName, String nursePass) {
        this.nurseId = nurseId;
        this.nurseName = nurseName;
        this.nursePass = nursePass;
    }

    // ゲッターとセッター
    public String getNurseId() { return nurseId; }
    public void setNurseId(String NurseId) { this.nurseId = NurseId; }

    public String getNurseName() { return nurseName; }
    public void setNurseName(String NurseName) { this.nurseName = NurseName; }

    public String getNursePass() { return nursePass; }
    public void setNursePass(String NursePass) { this.nursePass = NursePass; }
}