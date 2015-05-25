package helperClasses;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class CsvReading {

		
		//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		
		//Student attributes index
		public static String[] readCsvFile(String fileName) {

			BufferedReader fileReader = null;
        	ArrayList<String> Ids = new ArrayList();
	        try {
	        	
	        	//Create a new list of student to be filled by CSV file data 
	        	Ids = new ArrayList();
	        	
	            String line = "";
	            
	            //Create the file reader
	            fileReader = new BufferedReader(new FileReader(fileName));
	            
	            //Read the CSV file header to skip it
	            fileReader.readLine();
	            //Read the file line by line starting from the second line
	            while ((line = fileReader.readLine()) != null) {
	                //Get all tokens available in line
	                Ids.add(line+"\n");
	            }
	            
	        } 
	        catch (Exception e) {
	        	System.out.println("Error in CsvFileReader !!!");
	            e.printStackTrace();
	        } finally {
	            try {
	                fileReader.close();
	            } catch (IOException e) {
	            	System.out.println("Error while closing fileReader !!!");
	                e.printStackTrace();
	            }
	        }
	        String[] IdsArray = new String[Ids.size()];
	        for(int i = 0;i<Ids.size();i++){
	        	IdsArray[i] = Ids.get(i);
	        	System.out.println(IdsArray[i]);
	        }
	        return IdsArray;
		}

	
}
