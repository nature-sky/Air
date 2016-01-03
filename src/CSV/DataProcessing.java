package CSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import main.Constant;

public class DataProcessing {
	int count = 0;

	// ArrayList<String> dataList;
	public DataProcessing() {
		// dataList = Constant.
	}

	public void readData(String path) {
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), Constant.encode));

			// read file line by line
			while ((line = reader.readLine()) != null) {
				// System.out.println("AAA " + line);
				count++;
				if (line.contains("PM10") || count == 1) {
					Constant.dataList.add(line);
				}
			}

			// close stream
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void printData() {
		for (String s : Constant.dataList) {
			System.out.println(s);
		}
	}

	// write into a csv file
	public void outputData(String filename, ArrayList<String> dataList) {
		PrintWriter output;
		PrintWriter out;
		try {
			// Clear the file if the file has already existed
			output = new PrintWriter(new BufferedWriter(new FileWriter(filename
					+ ".csv", false)));
			output.print("");
			output.close();
			// Open a file
			// output = new PrintWriter(new BufferedWriter(new
			// FileWriter(filename+".csv",true)));
			// output = new BufferedW
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename
					+ ".csv", true)));
			for (String s : dataList) {
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
