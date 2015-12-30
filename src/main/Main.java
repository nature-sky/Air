package main;

import java.util.Scanner;
import CSV.CSV;

public class Main {

	public static void main(String[] args) {
		CSV csv = new CSV();
		String path = null;
		System.out.print("Please enter your file path:");
		Scanner scanner = new Scanner(System.in);
		path = scanner.nextLine();
		csv.readData(path);
		csv.processDataForFourSeason();
		//csv.printData();
		
		System.out.print("Please enter your output filename:");
		path = scanner.nextLine();
		csv.outputData(path);
		System.out.print("Finish output: " + path + ".csv");
	}
}
