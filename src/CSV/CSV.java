package CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSV {
	private ArrayList<String> dataList = new ArrayList<String>();
	
	public CSV() {
		
	}

	public void readData(String path) {
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(path));

			// read file line by line
			while ((line = reader.readLine()) != null) {
				dataList.add(line);
		    }
		    // close stream
		    reader.close();
	    } catch (IOException e1) {
		    e1.printStackTrace();
	    }
    }
	
	//Add Season attribute and Dummy attribute(1) into the data
	public void processDataForFourSeason() {
		for(int i = 0; i < dataList.size(); i++) {
			String line = dataList.get(i);
			String[] linesplit = line.split(",");
			
			// Process Header row
			if(linesplit[2].equals("測項")){
				dataList.set(i, "Season,Dummy,"+line);
			}
			
			// Fetch the row that has PM10 value
			else if(linesplit[2].equals("PM10")) {
		    	for(int j = 1; j <= 12; j++) {
		    		String[] date = linesplit[0].split("/");
		    		switch(Integer.valueOf(date[1])) {
		    		
		    		//Add Spring into Season attribute
		    		case 3:
			        case 4:
			        case 5: 
		            	dataList.set(i, "Spring,1,"+line);
		                break;
		            //Add Summer into Season attribute
			        case 6:
			        case 7:
			        case 8: 
		            	dataList.set(i, "Summer,1,"+line);
		                break;
		            //Add Autumn into Season attribute
		            case 9:
		            case 10:
		            case 11:
		            	dataList.set(i, "Autumn,1,"+line);
		                break;
		            //Add Winter into Season attribute
		            case 12:
		            case 1:
		            case 2:
		            	dataList.set(i, "Winter,1,"+line);
		                break;
		                
		            default:
		            	break;
		    		}
		        }
		    }
		}
	}
	
	public void printData() {
		for (String s : dataList) {
			System.out.println(s);
	    }
	}
	
	//write into a csv file
	public void outputData(String filename) {
		PrintWriter output;
		try {
			//Clear the file if the file has already existed
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".csv",false)));
			output.print("");
			
			//Open a file
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename+".csv",true)));
			for (String s : dataList) {
				String[] split = s.split(",");
				Boolean WRITE_STATUS = true;
				
				//If there is invalid value("") in the row, not write into file
				for(int i = 0; i < split.length; i++) {
					if(split[i].equals("\"\"")) {
						WRITE_STATUS = false;
						break;
					}
				}
				
				// write the row into file
				if(WRITE_STATUS == true)
				    output.println(s);
		    }
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
