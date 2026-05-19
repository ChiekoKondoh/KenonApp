package model;

import java.util.List;

public class TempRecordsLogic {
	public boolean execute(TempRecords tr) {
		
		// 1. 保存担当のDAOを準備する
		TempRecordsDAO dao = new TempRecordsDAO();

		// 2. DAOにバインダー(tr)を渡して、DBへの保存をお願いする
		boolean result = dao.create(tr);

		// 3. 保存が成功したかどうかをServletに報告する
		return result;
	}

	// 体温を判定するためのjudgeメソッド
	public String judge(double temp) {
		if (temp >= 38.0) {
			return "⚠️ 発熱しています。ナースコールを押してください。";
		} else if (temp >= 37.0) {
			return "💡 体温が上昇傾向です。注視しましょう。";
		} else if (temp >= 35.5) {
			return "✅ 平熱です。";
		} else {
			return "❓ もう一度測定しましょう。";
		}
	}
	
	//検温履歴を読み上げる
	public List<TempRecords> getHistory(String patientId, String startDate) {
		//履歴(検索)担当のDAOさんを準備する
	    TempRecordsDAO dao = new TempRecordsDAO();
	    //履歴担当DAOさんが検索したものを返す
	    return dao.findByDateRange(patientId, startDate);
	}
}