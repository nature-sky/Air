package CSV;

import main.Constant;

public class FourSeasonProcessing {
	
	public void execute(){
		processDataForFourSeason();
	}
	// Add Season attribute and Dummy attribute(1000) into the data
	public void processDataForFourSeason() {
		for (int i = 0; i < Constant.dataList.size(); i++) {
			String line = Constant.dataList.get(i);
			String[] linesplit = line.split(",");

			// Process Header row
			if (linesplit[2].equals("測項")) {
				Constant.dataList.set(i, "Season,Dummy," + line);
			}

			// Fetch the row that has PM10 value
			else if (linesplit[2].equals("PM10")) {
				for (int j = 1; j <= 12; j++) {
					String[] date = linesplit[0].split("/");

					switch (Integer.valueOf(date[1])) {

					// Add Spring into Season attribute
					case 3:
					case 4:
					case 5:
						Constant.dataList.set(i, "Spring,1000," + line);
						break;
					// Add Summer into Season attribute
					case 6:
					case 7:
					case 8:
						Constant.dataList.set(i, "Summer,1000," + line);
						break;
					// Add Autumn into Season attribute
					case 9:
					case 10:
					case 11:
						Constant.dataList.set(i, "Autumn,1000," + line);
						break;
					// Add Winter into Season attribute
					case 12:
					case 1:
					case 2:
						Constant.dataList.set(i, "Winter,1000," + line);
						break;

					default:
						break;
					}
				}
			}
		}
	}
}
