package helperClasses;


import edu.udel.cisc275_15s.bigo.Database;
import gameObjects.Question.DialogBank;
import gameObjects.Question.ScenarioQuestion;
import gameObjects.Question.YesNoQuestion;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CsvWriting {

		
		//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		
		//CSV file header
		private static final String FILE_HEADER = "ID,Question,Attempts";

		public static void writeCsvFile(String fileName,DialogBank bank) {
			
			
			FileWriter fileWriter = null;
			String[] previousNames;
			previousNames = CsvReading.readCsvFile(fileName);
			try {
				fileWriter = new FileWriter(fileName);
				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				for(String s : previousNames){
					fileWriter.append(s);
				}
				fileWriter.append(Database.name);
				fileWriter.append(COMMA_DELIMITER);
				//Write a new student object list to the CSV file
				fileWriter.append("yesno Questions:");
				fileWriter.append(COMMA_DELIMITER);
				for(ArrayList<YesNoQuestion> list : bank.yesnoBank.values()){
					for(int q = 0;q<list.size();q++){
						fileWriter.append(list.get(q).message);
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(Integer.toString(list.get(q).attempts));
						fileWriter.append(COMMA_DELIMITER);
					}
				}
				fileWriter.append("Scenario Questions:");
				fileWriter.append(COMMA_DELIMITER);
				for(ArrayList<ScenarioQuestion> list :	bank.scenarioBank.values()){
					for(int q = 0;q<list.size();q++){
						fileWriter.append(list.get(q).message);
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(Integer.toString(list.get(q).attempts));
						fileWriter.append(COMMA_DELIMITER);
					}
				}
				fileWriter.append(NEW_LINE_SEPARATOR);
				System.out.println("CSV file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				
			}
		}
	}


