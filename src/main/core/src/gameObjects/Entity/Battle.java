package gameObjects.Entity;


import gameObjects.Room;
import gameObjects.Question.Question;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Battle {
	
	private UserCharacter player;
	private NPC opponent;
	private String location;		// May be initialized to produce a different background for the battle
	private Question[] Questions;	// Array of questions to be presented to the player
	private int[] correct;			// Initialized as an array of zeros (one 0 for each question).
	private String answerGiven;		// Players selected response
	boolean qpresented = false;
	int index = 0;
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
		this.Questions = ((Trainer)opponent).getQuestions();
		
	}
	
	public Battle(NPC opponent, String location){
		this.opponent = opponent;
		this.location = location;
		
	}
	
	public boolean battleLoop(){
		return false;
		
	}
	
	public void draw(){
		
		SpriteBatch batch = new SpriteBatch();
		
		BitmapFont question = new BitmapFont();			// Question to be printed
		question.setColor(Color.BLACK);
		BitmapFont instructions = new BitmapFont();		// Instructions for the player
		instructions.setColor(Color.BLACK);
		
		BitmapFont ans1 = new BitmapFont();				// Answer 1
		ans1.setColor(Color.BLACK);
		BitmapFont ans2 = new BitmapFont();				// Answer 2
		ans2.setColor(Color.BLACK);
		BitmapFont ans3 = new BitmapFont();				// Answer 3
		ans3.setColor(Color.BLACK);
		BitmapFont ans4 = new BitmapFont();				// Answer 4
		ans4.setColor(Color.BLACK);
		
		Texture pcBub = new Texture("speech bubble.png");		// Text bubble Background for player
		Texture oppBub = new Texture("speech bubble.png");		// Text bubble Background for oppponent
		
		Texture background = new Texture("background2.png");	// Battle background/location	
		Texture opp = new Texture("opponent.png");				// Opponent sprite
		Texture pc = new Texture("player.png");					// Player sprite
		
		
		batch.begin();
		
			batch.draw(background, 0, 0);
			batch.draw(pc, 0, 0);
			batch.draw(opp, background.getWidth() - opp.getWidth(), background.getHeight() - opp.getHeight());
			batch.draw(pcBub, 100, 30);
			batch.draw(oppBub, 100, background.getHeight() - opp.getHeight() - 130);
			question.draw(batch, Questions[0].getQuestion(), 110, background.getHeight() - 50);
			instructions.draw(batch, "Touch the correct answer", 110, 20 + pcBub.getHeight());
			ans1.draw(batch, "A)" + Questions[0].getanswer1()  +"\n  B)" +  Questions[0].getanswer2()  + "  C)" + Questions[0].getanswer3()  + "  D)" +  Questions[0].getanswer4(), 100, 130);
		qpresented = true;
		batch.end();
	}

}