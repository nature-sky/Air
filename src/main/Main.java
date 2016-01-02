package main;

import java.util.Scanner;

import CSV.DataProcessing;
import CSV.FourSeasonProcessing;

public class Main {
	Scanner scanner;
	DataProcessing csv;
	FourSeasonProcessing fs;
	public Main(){
		csv = new DataProcessing();
	}
	public static void main(String[] args) {
		Main m =new Main();
		m.readData();
		m.processing();
		m.outputData();
	}
	
	public void processing(){
		fs= new FourSeasonProcessing();
		fs.processDataForFourSeason();
	}
	
	public void readData(){
		String path = null;
		System.out.print("Please enter your file path:");
		scanner = new Scanner(System.in);
		path = scanner.nextLine();
		csv.readData(path);
	}
	
	public void outputData(){
		System.out.print("Please enter your output filename:");
		String path = scanner.nextLine();
		csv.outputData(path);
		System.out.print("Finish output: " + path + ".csv");
	}
}
