package gameObjects.Entity;

import edu.udel.cisc275_15s.bigo.Notes;
import gameObjects.Question.Question;
import gameObjects.Question.QuestionFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class NPC extends Obstacle {
	
	private String message;
	private boolean isTalking;
	
	// How far can a trainer see in either compass direction 
	public final int SIGHT_RANGE = 35;
	
	/**
	 * Creates an instance of an NPC
	 * 
	 * @param position
	 * @param sprite
	 */
	public NPC(int x, int y, String sprite, String message){
		
		super(x, y, sprite);
		this.isTalking = false;
		this.message = message;
		
	}	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setTalking(boolean t){
		this.isTalking = t;
	}
	
	public boolean isTalking(){
		return this.isTalking;
	}	

	public boolean playerInRange(UserCharacter player){
		
		if((getX() == player.getX()) && (Math.abs(getY() - player.getY()) <= SIGHT_RANGE)){
			return true;
		}
		else if((getY() == player.getY()) && (Math.abs(getX() - player.getX()) <= SIGHT_RANGE)){
			return true;
		}
		else if(Math.abs(getY() - player.getY()) <= SIGHT_RANGE && Math.abs(getX() - player.getX()) <= SIGHT_RANGE){
			return true;
		}
		
		return false;
	}

	public void drawText(SpriteBatch batch){
		Texture textbox = new Texture("speech bubble.png");
		BitmapFont text = new BitmapFont();
		text.setColor(Color.BLACK);
		batch.draw(textbox, 20, 60);
		
		String displaymessage = "";
		for(int i = 0; i < this.message.length(); i = i + 50){
			int k = i + 50;
			while(k < this.message.length()){
				if(this.message.substring(k,k+1).equals(" "))
					break;
				
					
				k++;
			}
			if(this.message.length() >= i  + 50)
			{
		    	if(i + 50 < this.message.length())
				displaymessage =  this.message.substring(i, k);
				else
				displaymessage = this.message.substring(i) +"\n";
			}
			else
				displaymessage =  this.message.substring(i) + "\n";
			
			text.draw(batch, displaymessage, 30, 60 + textbox.getHeight() - 10 - i/3);
		
			i = k - 50;
		}
		
		Notes.addnote(this.message);
	}
	
	/**
	 * Takes in the file name of a game script with the extension ".pks"
	 * The method return a NPC with created from the information in the 
	 * script
	 */
	public static NPC parseScript(String file)
	{
		
		  String fileName = file; 	// Name of script file that is being parsed
		  String cmd; 				// String representation of command being passed into the parser
		  Command command;			// Command enumeration type
		  
		  String line = null;		// Line currently being read

		  
		  
		  NPC newNPC = null;				// New NPC to be created
		  NPCType npcType= null;			// Type of the NPC to be created
		  int x = 0;						// X-coordinate of the NPC's position
		  int y = 0;						// Y-coordinate of the NPC's position
		  String message = null;			// X-coordinate of the NPC's position
		  String messageY = null;			// Yes message if an InfoNPC
		  String messageN = null;			// No message if an InfoNPC
		  Question question = null;			// Question if Trainer
		  
		  try{
			  
			  if(!file.substring(file.length() - 4, file.length()).equals(".pks")){
				  throw new IncorrectFileException();	// The file does not have the correct extension
			  }
			  
			  FileReader fileReader = new FileReader(fileName);
			  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
			  //sets line to first line of the file.
			  line = bufferedReader.readLine();
			  
			  //removes all white spaces from the line;
			  line.replace(" ","");
			  if(line.length() > 5 || line.charAt(0) != '#' || line == null){
				  bufferedReader.close();
				  throw new IncorrectFileException();
			  }
			  
			  // Generates the NPCType from the script
			  npcType = NPCType.fromString(line.substring(1, 5));
		  
			  //Iterates through each line of the script, starting with line 2
			  while((line = bufferedReader.readLine()) != null) {
                
				  line.trim();	//eliminates any white space at the beginning or end of the line
                
				  // Reads out the x and coordinates of the NPC
				  if(line.charAt(0) == '('){
					  
					  // Saves the numbers between the '(' to ',' in x and ',' to ')' in y
					  x = Integer.parseInt(line.substring( 1, line.indexOf(',')));
					  y = Integer.parseInt(line.substring(line.indexOf(',') + 1, line.indexOf(')')));
		
				  }
				  
				  // Since the first character of the line is not a '('
				  // ,then the line must start with a command followed by ':'
				  else{
					  
					  // Saves the characters until the ':' as the command code
					  cmd = line.substring(0,line.indexOf(':'));
					  //System.out.println(cmd);
					  
					  // Converts the code to the type NPCType for the switch statement
					  command = Command.fromString(cmd);
		    		
					  switch(command){
					  	
					  	case MESSAGE:
					  	{
					  		switch(npcType){
					  			
					  		case YSNO:
					  		{
					  			message = line.substring(line.indexOf(':') + 1, line.indexOf("[Y]"));
					  			messageY = line.substring(line.indexOf("[Y]") + 3, line.indexOf("[N]"));
					  			messageN = line.substring(line.indexOf("[N]") + 3);
					  			break;
					  		}
					  		
					  		default: message = line.substring(line.indexOf(':') + 1);
					  			
					  		}
					  	}
					  	
					  	case QUESTION: {question = QuestionFactory.getQuestion(line.substring(line.indexOf(':') + 1));
					  					break;
					  					}
					  	
		    		
					  	default: 
		    		
		    		}
		    	}
		    }
		    
			bufferedReader.close();
		  }
		  catch(IncorrectFileException ex) {
			    System.out.println(
			        "wrong file '" + 
			        fileName + "'");                
		  }
		  catch(FileNotFoundException ex) {
			    System.out.println(
				        "The following file was not found: '" + 
				        fileName + "'");                
		  }
		  catch(IOException ex) {
			    System.out.println(
			        "Error reading file '" 
			        + fileName + "'");                   
			    
		  }
		  
		  // Based on npcType a different type on NPC is produced
		  System.out.println(npcType);
		  
		  switch(npcType){
		  
		  	case INFO: {newNPC = new InfoNPC(x, y, message);
		  				System.out.println("info Was created");
		  				break;}
		  	
		  	case TRNR: {newNPC = new Trainer(x, y, message, new Question[]{question}); 
		  				System.out.println("trainer Was created");
		  				break;}
		  						
		  	
		  	case YSNO: {newNPC = new YesNoNPC(x, y, message, messageY, messageN);
		  				System.out.println("yes no  Was created");
		  				break;}
		  
		  	default:
		  		
		  }
		  
		  
		  
		  return newNPC;
	}
	
	/*
	 *  Takes in an ArrayList of Strings that are the file names of NPC scripts;
	 */
	public static ArrayList<NPC> generateNPCs(String[] scripts){
		
		ArrayList<NPC> npcs = new ArrayList<NPC>();
		
		for(int i = 0; i < scripts.length; i++){
			npcs.add(parseScript(scripts[i]));
		}
		
		return npcs;
	}

}
