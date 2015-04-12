package gameObjects.Entity;

import gameObjects.Question.Question;
import gameObjects.Question.QuestionFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class NPC extends Obstacle {
	
	//May need a factory method
	
	/*
	 *  Will add the script parsing method for creating instances of sprites. 
	 *  
	 *  I'm thinking that all sprite information will be located in the script.
	 *  So the script will include...
	 *  - Sprite position
	 *  - Sprite Type
	 *  	- INFO
	 *  		- message
	 *  	- YSNO
	 *  		- initial message
	 *  		- message if yes
	 *  		- message if no
	 *      - BOSS
	 *      	- question denoted Q: or [Q]
	 *      	- set of answers denoted A: or [A]
	 * 
	 */
	
	public NPC(Position position, String sprite){
		
		super(position, sprite);
		
	}
	
	
	private void buildquestionlists(String file)
	{
		  String fileName = file;
		  String line = null;
		  int x;
		  Question testquestion;
		  
		  try{
		  FileReader fileReader = new FileReader(fileName);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		    while((line = bufferedReader.readLine()) != null) {
		        testquestion = QuestionFactory.getQuestion(line);
		        //if(testquestion.type() == "UDSIS")
		        	//UDSISQuestionList.add((UDSISQuestion) testquestion);
		        //else if(testquestion.type() == "Drop Add")
		        	//DropAddQuestionList.add((DropAddQuestion) testquestion);
		        //else
		        	//AdvisementQuestionList.add((AdvisementQuestion) testquestion);
		        
		    }
		  }
		  catch(FileNotFoundException ex) {
			    System.out.println(
			        "Unable to open file '" + 
			        fileName + "'");                
			}
			catch(IOException ex) {
			    System.out.println(
			        "Error reading file '" 
			        + fileName + "'");                   
			    
			}
	}

}
