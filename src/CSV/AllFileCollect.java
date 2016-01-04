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
	int count = 0;

	public static void main(String[] args) {
		new AllFileCollect().execute();
		// System.out.println(",32,,,,,".split(",").length);
	}

	public void execute() {
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
				boolean ok = true;
				String newLine = "";
				if (line.contains("PM10") || count == 1) {
//					System.out.println(line);
					line = line.replaceAll("\"", "");
//					System.out.println(line);
					line = line.replace("日期", "Date");
					line = line.replace("測站", "Station");
					line = line.replace("測項", "Item");
					line = line.replace("淡水", "Tamshui");
					line = line.replace("三義", "Sanyi");
					line = line.replace("三重", "Sanchong");
					line = line.replace("桃園", "Taoyuan");
					line = line.replace("基隆", "Keelung");
					line = line.replace("觀音", "Guanyin");
					line = line.replace("屏東", "Pingtung");
					line = line.replace("鳳山", "Fengshan");
					line = line.replace("台南", "Tainan");
					line = line.replace("臺南", "Tainan");
					line = line.replace("二林", "Erlin");
					line = line.replace("沙鹿", "Shalu");
					line = line.replace("彰化", "Changhua");
					if (line.split(",").length != 27) {
						continue;
					}
					for (int i = 0; i < line.split(",").length; i++) {
						if (i >= 3) {
							if (!line.split(",")[i].matches("[0-9]+") || Integer.valueOf(line.split(",")[i])>500) {
								ok = false;
								break;
							} else {
								if (count == 1) {
									newLine += ",Day" + line.split(",")[i];
								} else {
									newLine += ","
											+ Double.parseDouble(line
													.split(",")[i]);
								}
							}
						} else {
							if (i == 0) {
								newLine += line.split(",")[0];
							} else {
								newLine += "," + line.split(",")[i];
							}
						}
					}
					// System.out.println();
					if (ok) {
						newLine = newLine.replace(newLine.split(",")[0]+","+newLine.split(",")[1],newLine.split(",")[0]+newLine.split(",")[1]);
						Constant.dataList.add(newLine);
					}
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
				if (name.contains(".csv")) {
//					System.out.println(fileEntry.getPath());
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
			output = new PrintWriter(new BufferedWriter(new FileWriter(
					"allData.csv", false)));
			output.print("");
			output.close();
			// Open a file
			// output = new PrintWriter(new BufferedWriter(new
			// FileWriter(filename+".csv",true)));
			// output = new BufferedW
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("allData.csv"), "big5"));
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
					//System.out.println(s);
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
