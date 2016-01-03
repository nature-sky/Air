package CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import main.Constant;

public class AllFileCollect {
	File folder;
	int count =0;
	public static void main(String[] args){
		new AllFileCollect().execute();
	}
	public void execute(){
		folder = new File(Constant.folderLocation);
		listFilesForFolder(folder);
		outputData();
	}
	public void readData(String path) {
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "big5"));

			// read file line by line
			while ((line = reader.readLine()) != null) {
				count++;
				if (line.contains("PM10") || count == 1) {
					System.out.println("AAA " + line);
					Constant.dataList.add(line);
				}
			}

			// close stream
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void listFilesForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        		String name = fileEntry.getName();
	        		if(name.contains(".csv")){
	        			System.out.println(fileEntry.getPath());
	        			readData(fileEntry.getPath());
	        		}
	        }
	    }
	}
	
	public void outputData() {
		PrintWriter output;
		BufferedWriter out;
		try {
			// Clear the file if the file has already existed
			output = new PrintWriter(new BufferedWriter(new FileWriter("allData.csv", false)));
			output.print("");
			output.close();
			// Open a file
			// output = new PrintWriter(new BufferedWriter(new
			// FileWriter(filename+".csv",true)));
			// output = new BufferedW
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("allData.csv")));
			for (String s : Constant.dataList) {
				String[] split = s.split(",");
				Boolean WRITE_STATUS = true;

				// Maybe another strings need to be processed
				// If there is invalid value("") in the row, not write into file
				for (int i = 0; i < split.length; i++) {
					if (split[i].equals("\"\"")) {
						WRITE_STATUS = false;
						break;
					}
				}

				// write the row into file
				if (WRITE_STATUS == true) {
					System.out.println(s);
					out.write(s + "\n");
				}
			}
			out.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
