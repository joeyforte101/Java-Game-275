package gameObjects.Entity;

import gameObjects.Question.Question;
import gameObjects.Question.QuestionFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


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
	
	
	private NPC parseScript(String file)
	{
		  //name of file
		  String fileName = file;
		  
		  //current line being read
		  String line = null;
		  int x;
		  
		  //NPC to be generated from script
		  NPC newNPC = null;
		  String npcType;
		  
		  try{
		  FileReader fileReader = new FileReader(fileName);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		  //sets line to first line of the file.
		  line = bufferedReader.readLine();
		  if(line.length() > 5 || line.charAt(0) != '#' || line == null){
	    	  System.out.println("Script syntax error");
	    	  //throw new Exception();
	      }
		  npcType = line.substring(1, 5);
		  
		  
		    while((line = bufferedReader.readLine()) != null) {
		      if(line.length() > 5 || line.charAt(0) != '#'){
		    	  
		      }
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
		  
		  return newNPC;
	}
	
	/*
	 *  Takes in an ArrayList of Strings that are the file names of NPc scripts;
	 */
	public static ArrayList<NPC> generateNPCs(ArrayList<String> scripts){
		
		
		
		return null;
	}

}
