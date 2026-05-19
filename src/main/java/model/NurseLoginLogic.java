package model;

public class NurseLoginLogic {
    
    public Nurse execute(String nurseId, String nursePass) {
        
        NurseLoginDAO dao = new NurseLoginDAO();
        
        // DAOからバインダー（Nurse）を受け取ります
        Nurse Nurse = dao.findUser(nurseId, nursePass);
        
        // もしバインダーが空っぽ（null）じゃなければ、ログイン成功とみなして
        // そのままバインダーを返します
        return Nurse;
    }
}