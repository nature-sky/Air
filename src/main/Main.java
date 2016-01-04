package main;

import java.util.ArrayList;
import java.util.Scanner;

import CSV.DataProcessing;
import CSV.DayProcessing;
import CSV.FourSeasonProcessing;
import CSV.YearProcessing;

public class Main {
	Scanner scanner;
	DataProcessing csv;
	YearProcessing yp;
	FourSeasonProcessing fs;
	DayProcessing dp;
	public Main(){
		csv = new DataProcessing();
	}
	public static void main(String[] args) {
		Main m =new Main();
		m.readData();
		m.processingAndOutput();
		
	}
	
	public void processingAndOutput(){
//		fourSeasonProcessingAndOutputData();
//		yearProcessingAndOutputData();
//		yp.execute();
		dayProcessingAndOutputData();
	}
	
	public void readData(){
		String path = null;
//		System.out.print("Please enter your file path:");
//		scanner = new Scanner(System.in);
//		path = scanner.nextLine();
//		csv.readData(path);
		csv.readData(Constant.readFilePath);

	}
	
	public void outputData(ArrayList<String> outputList){
//		System.out.print("Please enter your output filename:");
//		String path = scanner.nextLine();
//		csv.outputData(path);
		csv.outputData(Constant.outputFilePath, outputList);
//		System.out.print("Finish output: " + path + ".csv");
		System.out.print("Finish output: " + Constant.outputFilePath +".csv");
	}
	
	public void fourSeasonProcessingAndOutputData(){
		fs= new FourSeasonProcessing();
		fs.processDataForFourSeason();
		outputData(Constant.seasonDataList);
	}
	
	public void yearProcessingAndOutputData(){
		yp = new YearProcessing();
		yp.execute();
		outputData(Constant.yearDataList);
	}
	
	public void dayProcessingAndOutputData(){
		dp = new DayProcessing();
		dp.processForDays();
		outputData(Constant.dayDataList);
	}
	
}
