package model;

public class PatientLoginLogic {
    
    public Patient execute(String patientId, String patientPass) {
        
        PatientLoginDAO dao = new PatientLoginDAO();
        
        // DAOからバインダー（Patient）を受け取ります
        Patient patient = dao.findUser(patientId, patientPass);
        
        // もしバインダーが空っぽ（null）じゃなければ、ログイン成功とみなして
        // そのままバインダーを返します
        return patient;
    }
}