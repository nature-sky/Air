package CSV;

import main.Constant;

public class DayProcessing {
	//morning 5~10(7~12),afternoon 11~16(13~18), evening 17~22(19~24), night 23~4(25,2~6)
	
	public void processForDays(){
		boolean inDaySection = false;
		int dataSize;
		for(int i=0; i<Constant.dataList.size(); i++){
			String line = Constant.dataList.get(i);
			String[] linesplit = line.split(",");
			dataSize = linesplit.length;
			
			if (i==0) {
//				Constant.dataList.set(i, "Days,Dummy," + line);
				Constant.dayDataList.add("Days,Dummy,"+linesplit[0]+","+linesplit[1]+","+"Day00,Day01,Day02,Day03,Day04,Day05");
			}
			else if(linesplit[1].equals("PM10")){
				String m,a,e,n;
				m = "M,1000,"+linesplit[0]+"m,"+linesplit[1];
				a = "A,1000,"+linesplit[0]+"a,"+linesplit[1];
				e = "E,1000,"+linesplit[0]+"e,"+linesplit[1];
				n = "N,1000,"+linesplit[0]+"n,"+linesplit[1];
				//morning,afternoon,evening
				for(int j=0; j<4; j++){
					for(int k=0; k<6; k++){
						if(j==0){
							if(k+7<dataSize){
								m = m + "," + linesplit[k+7];
							}
							else{
								m = m + ",";
							}
						}
						else if(j==1){
							if(k+13<dataSize){
								a = a + "," + linesplit[k+13];
							}
							else{
								a = a + ",";
							}							
						}
						else if(j==2){
							if(k+19<dataSize){
								e = e + "," + linesplit[k+19];
							}
							else{
								e = e + ",";
							}
						}
						else{
							if(k<1){
								if(k+25<dataSize){
									n = n + "," +linesplit[k+25];
								}
								else{
									n = n + ",";
								}
							}
							else{
								if(k+1<dataSize){
									n = n + "," +linesplit[k+1];
								}
								else{
									n = n + ",";
								}
							}
						}
					}
					
					if(j==0){
						Constant.dayDataList.add(m);
					}
					else if(j==1){
						Constant.dayDataList.add(a);
					}
					else if(j==2){
						Constant.dayDataList.add(e);
					}
					else{
						Constant.dayDataList.add(n);
					}
				}
			}	
		}
	}
}
