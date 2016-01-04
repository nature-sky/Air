package CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MonthAverageProcessing {
	ArrayList<String> outputData;
	PrintWriter output;
	PrintWriter out;

	public static void main(String[] args) {
		new MonthAverageProcessing();
	}

	public MonthAverageProcessing() {
		outputData = new ArrayList<String>();
		execute();
	}

	public void execute() {
		outputData();
		readData();

	}

	public void readData() {
		BufferedReader reader;
		String line;
		int count = 0;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream("allData.csv")));

			// read file line by line
			// System.out.println("33");
			String currentMonth = "";
			String storeMonth = "";
			String storeName = "";
			double all = 0;
			double monthAll = 0;
			while ((line = reader.readLine()) != null) {
				count++;
				if(count ==1){
					out.println(line+",field");
				}
				if (count > 1) {
					if(count != 2){
						currentMonth = line.split(",")[0].split("/")[1];
					}
					if (currentMonth.equals(storeMonth)) {
						// do nothing
						currentMonth = line.split(",")[0].split("/")[1];
						storeMonth = currentMonth;
						for (int i = 2; i < line.split(",").length; i++) {
							all += Double.parseDouble(line.split(",")[i]);
						}
						monthAll += all/24;
					} else {
						storeMonth = currentMonth;
						storeName = line.split(",")[0].split("/")[0];
						storeName += "/" + storeMonth + "/--";
						storeName += line.split(",")[0].substring(10) +",";
						for (int i = 1; i < line.split(",").length; i++) {
							if (i == 1) {
								storeName += line.split(",")[i];
							} else {
								storeName += "," + line.split(",")[i];
							}
						}
						storeName += "," + monthAll / 30;
						out.println(storeName);
						monthAll = 0;
						all = 0;
						storeMonth = currentMonth;
					}
				}
			}
			out.close();
			output.close();
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void outputData() {

		try {
			// Clear the file if the file has already existed
			output = new PrintWriter(new BufferedWriter(new FileWriter(
					"monthAverage.csv", false)));
			output.print("");
			output.close();
			// Open a file
			// output = new PrintWriter(new BufferedWriter(new
			// FileWriter(filename+".csv",true)));
			// output = new BufferedW
			out = new PrintWriter(new BufferedWriter(new FileWriter(
					"monthAverage.csv", true)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
