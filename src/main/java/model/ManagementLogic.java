package model;

import java.util.List;

public class ManagementLogic {
    public List<Patient> execute() {
        
    	// 患者管理担当のDAOさんを準備する
        ManagementDAO dao = new ManagementDAO();
        
        //DAOさんから患者リスト(全員分)を受け取る
        List<Patient> patientList = dao.findAll();
        
        return patientList;
    }
}