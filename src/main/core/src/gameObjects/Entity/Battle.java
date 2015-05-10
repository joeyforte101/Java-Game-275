package gameObjects.Entity;



import edu.udel.cisc275_15s.bigo.MainClass;
import edu.udel.cisc275_15s.bigo.Notes;
import gameObjects.Question.Question;
import gameObjects.Question.ScenarioQuestion;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battle {
	
	private UserCharacter player;
	private Trainer opponent;
//	private String location;		// May be initialized to produce a different background for the battle
//	public Scenario[] questions;	// Array of questions to be presented to the player
	private boolean[] correct;		// Initialized as an array of false.
	public String answerGiven;		// Players selected response
	boolean qpresented = false;
	public int questionIndex = 0;
	int ansIndex = 0;
	
	/**
	 * <h2>Produces an instance of the Battle Class</h2>
	 * 
	 * <p> The constructor produces a battle with a default location(background).
	 * 	It initializes an array of zeros that represent which questions the player
	 *  has gotten correct. </p>
	 * 
	 * @param player
	 * @param npc
	 */
	public Battle(UserCharacter player, Trainer npc){
		this.player = player;
		this.opponent = npc;
		correct = new boolean[npc.questions.size()];
//		for(int i = 0; i < correct.length; i++){
//			correct[i] = false;
//		}
		
	}
	
	/**
	 * Produces an Battle object using an opponent using a UserCharacter
	 * NPC and a location that will become the background of the battle
	 *
	 * @param player
	 * @param opponent
//	 * @param location
	 */
//	public Battle(UserCharacter player, Trainer opponent){
////		this.location = location;
//		this.player = player;
//		this.opponent = opponent;
////		this.questions = ((Trainer)opponent).getQuestions();
//		correct = new int[questions.length];
//		for(int i = 0; i < correct.length; i++){
//			correct[i] = 0;
//		}
//	}
	
	/**
	 * Returns true if the game is over.
	 * meaning that the int array of correct
	 * answers has all 1's
	 * 
	 * 
	 * @return
	 */
	public boolean battleOver(){
//		for(int i = 0; i < correct.length ; i++){
//			if(correct[i] != 1){
//				return false;
//			}
//		}
		for (boolean q : correct) {
			if (!q)
				return false;
		}
		Notes.progress++;
		return true;		
	}
	
	/**
	 * Passes an answer to the game and determines if the answer is correct
	 * 
	 * If it is correct then the questionIndex is increment and a 1 is 
	 * placed in the correct array for the current index, indicating  
	 * correct answer.
	 * 
	 * returns true if correct, false otherwise
	 * 
	 * @param ans
	 * @return
	 */
	public boolean isCorrectAnswer(int buttonIndex){
//		if(questions[questionIndex].isrightanswer(answer)){
//			
//			correct[questionIndex] = 1;
//			if(questionIndex == questions.length -1){
//				questionIndex = 0;
//			}else{
//				questionIndex++;
//			}
//			return true;
//		}
		String answer = opponent.questions.get(questionIndex).options[buttonIndex];
		for (int i = 0; i < opponent.questions.size(); i++) {
			if (opponent.questions.get(questionIndex).answer == answer) {
				correct[questionIndex] = true;
				questionIndex = (questionIndex + 1) % opponent.questions.size();
				return true;
			}				
		}
		return false;
	}
	
	/**
	 * 
	 * Draws the battle on the screen
	 * 
	 */
	public void draw(){
		
		SpriteBatch batch = new SpriteBatch();
		
		BitmapFont text = new BitmapFont();			// Black Font for the text
		text.setColor(Color.BLACK);
		
		BitmapFont ans1 = new BitmapFont();				// Answer
		ans1.setColor(Color.BLACK);
		
		Texture pcBub = new Texture("speech bubble.png");		// Text bubble Background for player
		Texture oppBub = new Texture("speech bubble.png");		// Text bubble Background for opponent
		
		Texture background = new Texture("background2.png");	// Battle background/location	
		Texture opp = new Texture("opponent.png");				// Opponent sprite
		Texture pc = new Texture("player.png");					// Player sprite
		
		
		batch.begin();
			
			// Draws background of the battle
			batch.draw(background, 0, 0);
			// Draws player character 
			batch.draw(pc, 0, 0);
			// Draws Opponent NPC
			batch.draw(opp, background.getWidth() - opp.getWidth(), background.getHeight() - opp.getHeight());
			// Draws TextBox for the answers  
			batch.draw(pcBub, 100, 30);
			// Draws TextBox for opponent NPC's question
			batch.draw(oppBub, 100, background.getHeight() - opp.getHeight() - 130);
			// Prints current question to the screen
			
			String message = opponent.questions.get(questionIndex).getQuestion();
			String displaymessage = "";
			for(int i = 0; i < message.length(); i = i + 50){
				int k = i + 50;
				while(k < message.length()){
					if(message.substring(k,k+1).equals(" "))
						break;
					
						
					k++;
				}
				if(message.length() >= i  + 50)
				{
			    	if(i + 50 < message.length())
					displaymessage =  message.substring(i, k);
					else
					displaymessage = message.substring(i) +"\n";
				}
				else
					displaymessage =  message.substring(i) + "\n";
				
				text.draw(batch, displaymessage, 110, background.getHeight() - 50 - i/3);
			
				i = k - 50;
			}
			
			
			
			//text.draw(batch, questions[0].getQuestion(), 110, background.getHeight() - 50);
			// Prints instructions for the player to follow
			text.draw(batch, "Touch the correct answer", 110, 20 + pcBub.getHeight());
			// Prints the potential answers to the question presented
			for (int i = 0; i < 4; i++)
				text.draw(batch, opponent.questions.get(questionIndex).options[i], 110, pcBub.getHeight() - 20 + (20 * i));
//			text.draw(batch, "A) " + questions[questionIndex].getanswer1(), 110, pcBub.getHeight() - 20);
//			text.draw(batch, "B) " + questions[questionIndex].getanswer2(), 110, pcBub.getHeight() - 40);
//			text.draw(batch, "C) " + questions[questionIndex].getanswer3(), 110, pcBub.getHeight() - 60);
//			text.draw(batch, "D) " + questions[questionIndex].getanswer4(), 110, pcBub.getHeight() - 80);
			
		batch.end();
	}

}