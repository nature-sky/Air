package splitStation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Split {

	public static void main(String[] args) {
		BufferedReader br;
		BufferedWriter bw;
		try {
			br = new BufferedReader(new FileReader("output.csv.csv"));
			bw = new BufferedWriter(new FileWriter("splitByStation.csv"));
			
			String[] titles = br.readLine().split(",");
			bw.write("Station");
			for(int i = 1; i < titles.length; i++)
				bw.write("," + titles[i]);
			bw.newLine();
			
			while(br.ready()){
				String[] splitLine = br.readLine().split(",");
				bw.write(splitLine[2].substring(10));
				for(int i = 1; i < splitLine.length; i++)
					bw.write("," + splitLine[i]);
				bw.newLine();
			}
			
			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
