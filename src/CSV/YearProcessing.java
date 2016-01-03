package CSV;

import java.util.ArrayList;

import main.Constant;

public class YearProcessing {
	String fields = "Year,Dummy,";
	public static void main(String[] args) {
		new YearProcessing().execute();
	}
	
	public YearProcessing(){
//		outputList = new ArrayList<String>();
	}

	public void execute() {
		for (int i = 0; i < Constant.dataList.size(); i++) {
			System.out.println(Constant.dataList.get(i) + "  ");
		}
		processing();

	}

	public void processing() {
		for (int i = 0; i < Constant.dataList.size(); i++) {
			String data= Constant.dataList.get(i);
			// first enter
			if(i==0){
				fields += data;
				Constant.yearDataList.add(fields);
			} else{
				String year = data.split(",")[0].split("/")[0];
				String dummy = "1000";
				Constant.yearDataList.add(year+","+dummy+","+data);
//				System.out.println(year);
			}
		}
	}
}
