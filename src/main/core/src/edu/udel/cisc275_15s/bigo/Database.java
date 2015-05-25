package edu.udel.cisc275_15s.bigo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import gameObjects.Question.DialogBank;
import helperClasses.CsvWriter;

public class Database {
	
	static String outputFile = "userdData.csv";
	public static String name = "";
	public static DialogBank bank;
	public static void createUserData(String name){
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("Username");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			
			// write out a few records
			csvOutput.write(name);
			csvOutput.endRecord();
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createEntry(String question, boolean result){
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
				csvOutput.write(question);
				//csvOutput.endRecord();
			// else assume that the file already has the correct header line
				csvOutput.endRecord();
				csvOutput.close();
				CsvWriter csvOutput1 = new CsvWriter(new FileWriter(outputFile, true), ',');
			// write out a few records
			if (result)
				csvOutput1.write("correct");
			else
				csvOutput1.write("incorrect");
			csvOutput1.endRecord();
			csvOutput1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}