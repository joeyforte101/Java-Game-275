package gameObjects.Entity;



import gameObjects.Question.Question;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battle {
	
	private UserCharacter player;
	private NPC opponent;
	private String location;		// May be initialized to produce a different background for the battle
	public Question[] questions;	// Array of questions to be presented to the player
	private int[] correct;			// Initialized as an array of zeros (one 0 for each question).
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
	 * @param opponent
	 */
	public Battle(UserCharacter player,NPC opponent){
		this.player = player;
		this.opponent = opponent;
		this.questions = ((Trainer)opponent).getQuestions();
		correct = new int[questions.length];
		for(int i = 0; i < correct.length; i++){
			correct[i] = 0;
		}
		
	}
	
	/**
	 * Produces an Battle object using an opponent using a UserCharacter
	 * NPC and a location that will become the background of the battle
	 *
	 * @param player
	 * @param opponent
	 * @param location
	 */
	public Battle(UserCharacter player, NPC opponent, String location){
		this.location = location;
		this.player = player;
		this.opponent = opponent;
		this.questions = ((Trainer)opponent).getQuestions();
		correct = new int[questions.length];
		for(int i = 0; i < correct.length; i++){
			correct[i] = 0;
		}
	}
	
	/**
	 * Returns true if the game is over.
	 * meaning that the int array of correct
	 * answers has all 1's
	 * 
	 * 
	 * @return
	 */
	public boolean battleOver(){
		for(int i = 0; i < correct.length ; i++){
			if(correct[i] != 1){
				return false;
			}
		}
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
	public boolean giveAnswer(String ans){
		if(questions[questionIndex].isrightanswer(ans)){
			correct[questionIndex] = 1;
			if(questionIndex == questions.length -1){
				questionIndex = 0;
			}else{
				questionIndex++;
			}
			return true;
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
			text.draw(batch, questions[0].getQuestion(), 110, background.getHeight() - 50);
			// Prints instructions for the player to follow
			text.draw(batch, "Touch the correct answer", 110, 20 + pcBub.getHeight());
			// Prints the potential answers to the question presented
			text.draw(batch, "A) " + questions[questionIndex].getanswer1(), 110, pcBub.getHeight() - 20);
			text.draw(batch, "B) " + questions[questionIndex].getanswer2(), 110, pcBub.getHeight() - 40);
			text.draw(batch, "C) " + questions[questionIndex].getanswer3(), 110, pcBub.getHeight() - 60);
			text.draw(batch, "D) " + questions[questionIndex].getanswer4(), 110, pcBub.getHeight() - 80);
			
		batch.end();
	}

}