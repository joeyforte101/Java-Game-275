package gameObjects.Entity;

import edu.udel.cisc275_15s.bigo.Notes;
import gameObjects.Question.Question;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battle {

	SpriteBatch batch;

	BitmapFont text;
	BitmapFont ans1;

	Texture pcBub;
	Texture oppBubTexture;
	Sprite oppBub;
	Texture background;
	
	Texture lifeGuage;
	Texture[] lifeBars;
	Texture redLifeBar;
	Texture yellowLifeBar;
	Texture greenLifeBar;
	Texture questionMark;
	Texture questionMarkGrey;
	public Trainer opponent;
	public String answerGiven; // Players selected response
	boolean qpresented = false;
	public int questionIndex = 0;
	int ansIndex = 0;
	public static int life = 18;

	/**
	 * <h2>Produces an instance of the Battle Class</h2>
	 * 
	 * <p>
	 * The constructor produces a battle with a default location(background). It
	 * initializes an array of zeros that represent which questions the player
	 * has gotten correct.
	 * </p>
	 * 
	 * @param player
	 * @param npc
	 */
	public Battle(Trainer npc, SpriteBatch batch) {
		setOpponent(npc);
		lifeGuage = new Texture("life guage.png");
		redLifeBar = new Texture("life bar-r.png");
		yellowLifeBar = new Texture("life bar-y.png");
		greenLifeBar = new Texture("life bar.png");
		questionMark = new Texture("q-mark.png");
		questionMarkGrey = new Texture("q-markgrey.png");
		this.batch = batch;

		text = new BitmapFont(); // Black Font for the text
		text.setColor(Color.BLACK);

		ans1 = new BitmapFont(); // Answer
		ans1.setColor(Color.BLACK);

		background = new Texture("Battle BackgroundBoy.png");
		
		pcBub = new Texture("speech bubble.png"); // Text bubble Background for
													// player
		oppBubTexture = new Texture("speech bubble.png"); // Text bubble Background for
		
		oppBub = new Sprite(oppBubTexture);	
		
		oppBub.setSize(oppBub.getWidth() - 100, oppBub.getHeight());// opponent
		
		oppBub.setPosition(100, background.getHeight()-oppBub.getHeight());
		
		 // Battle
														// background/location
		//opp = new Texture("opponent.png"); // Opponent sprite
		//pc = new Texture("player.png"); // Player sprite
	}

	/**
	 * Returns true if the game is over. meaning that the int array of correct
	 * answers has all 1's
	 * 
	 * 
	 * @return
	 */
	public boolean battleOver() {
		if (life <= 0){
			life = 18;
			return true;
		}
		
		for (Question q : opponent.questions) {
			if (!q.completed)
				return false;
		}
		
		life = 18;
		opponent.defeated = true;
		if(opponent.defeated && opponent instanceof Boss)
			Notes.progress++;
		return true;
	}

	/**
	 * Passes an answer to the game and determines if the answer is correct
	 * 
	 * If it is correct then the questionIndex is increment and a 1 is placed in
	 * the correct array for the current index, indicating correct answer.
	 * 
	 * returns true if correct, false otherwise
	 * 
	 * @param ans
	 * @return
	 */
	public boolean isCorrectAnswer(int buttonIndex) {
		String answer = getOpponent().questions.get(questionIndex).options[buttonIndex];
		for (int i = 0; i < getOpponent().questions.size(); i++) {
			if (getOpponent().questions.get(questionIndex).answer == answer) {
				nextQuestion();
				return true;
			}
		}
		life--;
		return false;
	}
	
	public void nextQuestion(){
		questionIndex = (questionIndex + 1) % getOpponent().questions.size();
	}

	public void update() {

	}

	public void draw() {

		batch.begin();

		
		// Draws background of the battle
		batch.draw(background, 0, 0);
		// Draws player character
		//batch.draw(pc, 0, 0);
		// Draws Opponent NPC
		//batch.draw(opp, background.getWidth() - opp.getWidth(),	background.getHeight() - opp.getHeight());
		// Draws TextBox for the answers
		batch.draw(pcBub, 180, 30);
		// Draws TextBox for opponent NPC's question
		//batch.draw(oppBub, 100, background.getHeight() - opp.getHeight() - 130);
		oppBub.draw(batch);
		// Prints current question to the screen
		String message = getOpponent().questions.get(questionIndex).getQuestion();
		String displaymessage = "";
		for (int i = 0; i < message.length(); i = i + 50) {
			int k = i + 50;
			while (k < message.length()) {
				if (message.substring(k, k + 1).equals(" "))
					break;
				k++;
			}
			if (message.length() >= i + 50) {
				if (i + 50 < message.length())
					displaymessage = message.substring(i, k);
				else
					displaymessage = message.substring(i) + "\n";
			} else
				displaymessage = message.substring(i) + "\n";

			text.draw(batch, displaymessage, 110, background.getHeight() - 50 - i / 3);

			i = k - 50;
		}

		text.draw(batch, "Touch the correct answer", 190,
				20 + pcBub.getHeight());

		for (int i = 0; i < 4; i++)
			text.draw(batch, getOpponent().questions.get(questionIndex).options[i], 190, pcBub.getHeight() - 20 - (20 * i));

		
		drawLife();
		drawQuestionMarks();
		batch.end();
	}

	public Trainer getOpponent() {
		return opponent;
	}

	public void setOpponent(Trainer opponent) {
		this.opponent = opponent;
	}
	
	public void drawQuestionMarks(){
		
//		Texture[] qMarksTextures = new Texture[correct.length];
//		Sprite[] qMarks = new Sprite[correct.length];
		
		for (int i = 0; i < opponent.questions.size(); i++){
			if(opponent.questions.get(i).completed){
				batch.draw(questionMarkGrey, 64, background.getHeight() - ((1 + i) * 32));
			}else{
				batch.draw(questionMark, 64, background.getHeight() - ((1 + i) * 32));
			}
				
//			qMarks[i] = new Sprite(qMarksTextures[i]);
//			
//			if(i < 7){
//				qMarks[i].setPosition(64, background.getHeight() - ((1 + i) * 32));
//			}else{
//				qMarks[i].setPosition(128, background.getHeight() - 32);
//			}
//			qMarks[i].draw(batch);
		}
	}
	
	public void drawLife(){
		
		//batch.draw(lifeGuage, 5,0);
		
		for(int i = 0; i < life; i++){
			if(i < 4){
				batch.draw(redLifeBar, 5, 20 + i * 21);
//				lifeBars[i] = redLifeBar;
			}else if(i >= 4 && i < 10){
				batch.draw(yellowLifeBar, 5, 20 + i * 21);
//				lifeBars[i] = yellowLifeBar;
			}else{
				batch.draw(greenLifeBar, 5, 20 + i * 21);
//				lifeBars[i] = greenLifeBar;
			}
			
//			lifeBars[i] = new Sprite(lifeBarTextures[i]);
//			lifeBars[i].setPosition(5,pc.getHeight() + 20 + (i * 21));
//			lifeBars[i].draw(batch);
		}
		
	}

}