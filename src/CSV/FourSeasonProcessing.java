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
			if (linesplit[1].equals("Item")) {
				Constant.seasonDataList.add("Season,Dummy," + line);
			}

			// Fetch the row that has PM10 value
			else if (linesplit[1].equals("PM10")) {
//				for (int j = 1; j <= 12; j++) {
					String[] date = linesplit[0].split("/");

					switch (Integer.valueOf(date[1])) {

					// Add Spring into Season attribute
					case 3:
						Constant.seasonDataList.add("Spring,1000," + line);
						break;
					case 4:
						Constant.seasonDataList.add("Spring,1000," + line);
						break;
					case 5:
						Constant.seasonDataList.add("Spring,1000," + line);
						break;
					// Add Summer into Season attribute
					case 6:
						Constant.seasonDataList.add( "Summer,1000," + line);
						break;
					case 7:
						Constant.seasonDataList.add( "Summer,1000," + line);
						break;
					case 8:
						Constant.seasonDataList.add( "Summer,1000," + line);
						break;
					// Add Autumn into Season attribute
					case 9:
						Constant.seasonDataList.add("Autumn,1000," + line);
						break;
					case 10:
						Constant.seasonDataList.add("Autumn,1000," + line);
						break;
					case 11:
						Constant.seasonDataList.add("Autumn,1000," + line);
						break;
					// Add Winter into Season attribute
					case 12:
						Constant.seasonDataList.add( "Winter,1000," + line);
						break;
					case 1:
						Constant.seasonDataList.add( "Winter,1000," + line);
						break;
					case 2:
						Constant.seasonDataList.add( "Winter,1000," + line);
						break;

					default:
						break;
					}
//				}
			}
		}
	}
}
