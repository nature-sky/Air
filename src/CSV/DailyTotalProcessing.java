package CSV;

import main.Constant;

public class DailyTotalProcessing {
	String field = "DailyTol";
	
	public void execute() {
		for (int i = 0; i < Constant.dataList.size(); i++) {
			System.out.println(Constant.dataList.get(i) + "  ");
		}
		processingDailyTotal();
	}

    public void processingDailyTotal() {
    	for (int i = 0; i < Constant.dataList.size(); i++) {
			String data= Constant.dataList.get(i);
			// first enter
			if(i==0){
				Constant.dailyTotalDataList.add(data+","+field);
			} else{
				String split[] = data.split(",");
				double rowSum = 0;
				for(int r = 3; r < split.length; r++) {
					rowSum += Double.parseDouble(split[r]);
				}
				if(rowSum<10000){
					Constant.dailyTotalDataList.add(data+","+Integer.valueOf((int)rowSum).toString());
//					System.out.println(Integer.valueOf((int)rowSum).toString());
				}
			}
		}
	}
}





