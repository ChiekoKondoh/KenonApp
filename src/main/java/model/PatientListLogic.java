package model;

import java.util.List;

public class PatientListLogic {
    public List<Patient> execute(String[] selectedIds) {
        
    	//患者管理(検索担当)DAOさんを準備する
    	PatientListDAO dao = new PatientListDAO();
    	
    	//DAOさんから、探してもらった複数の患者さんのカルテを返してもらう
        return dao.findSelectedPatients(selectedIds);
    }
}