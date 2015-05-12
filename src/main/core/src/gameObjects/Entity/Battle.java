package gameObjects.Entity;

import edu.udel.cisc275_15s.bigo.Notes;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battle {

	SpriteBatch batch;

	BitmapFont text;
	BitmapFont ans1;

	Texture pcBub;
	Texture oppBub;
	Texture background;
	Texture opp;
	Texture pc;
	Trainer opponent;
	boolean[] correct; // Initialized as an array of false.
	public String answerGiven; // Players selected response
	boolean qpresented = false;
	public int questionIndex = 0;
	int ansIndex = 0;

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
		opponent = npc;
		correct = new boolean[npc.questions.size()];

		this.batch = batch;

		text = new BitmapFont(); // Black Font for the text
		text.setColor(Color.BLACK);

		ans1 = new BitmapFont(); // Answer
		ans1.setColor(Color.BLACK);

		pcBub = new Texture("speech bubble.png"); // Text bubble Background for
													// player
		oppBub = new Texture("speech bubble.png"); // Text bubble Background for
													// opponent

		background = new Texture("background2.png"); // Battle
														// background/location
		opp = new Texture("opponent.png"); // Opponent sprite
		pc = new Texture("player.png"); // Player sprite
	}

	/**
	 * Returns true if the game is over. meaning that the int array of correct
	 * answers has all 1's
	 * 
	 * 
	 * @return
	 */
	public boolean battleOver() {
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
	 * If it is correct then the questionIndex is increment and a 1 is placed in
	 * the correct array for the current index, indicating correct answer.
	 * 
	 * returns true if correct, false otherwise
	 * 
	 * @param ans
	 * @return
	 */
	public boolean isCorrectAnswer(int buttonIndex) {
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

	public void update() {

	}

	public void draw() {

		batch.begin();

		// Draws background of the battle
		batch.draw(background, 0, 0);
		// Draws player character
		batch.draw(pc, 0, 0);
		// Draws Opponent NPC
		batch.draw(opp, background.getWidth() - opp.getWidth(),	background.getHeight() - opp.getHeight());
		// Draws TextBox for the answers
		batch.draw(pcBub, 100, 30);
		// Draws TextBox for opponent NPC's question
		batch.draw(oppBub, 100, background.getHeight() - opp.getHeight() - 130);
		
		// Prints current question to the screen
		String message = opponent.questions.get(questionIndex).getQuestion();
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

		text.draw(batch, "Touch the correct answer", 110,
				20 + pcBub.getHeight());

		for (int i = 0; i < 4; i++)
			text.draw(batch, opponent.questions.get(questionIndex).options[i], 110, pcBub.getHeight() - 20 + (20 * i));

		batch.end();
	}

}