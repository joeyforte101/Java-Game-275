package gameObjects.Entity;

import gameObjects.Question.Question;

public class Trainer extends NPC {

	// How far can a trainer see in either compass direction
	private final int sightRange = 75;

	private Question[] questions;
	private String message;
	private boolean hasBattled = false;

	public Trainer(int x, int y, String sprite, String message,
			Question[] questions) {
		super(x, y, sprite);
		this.questions = questions;
		this.message = message;
	}

	public Question[] getQuestions() {
		return this.questions;
	}

	public void battled() {
		this.hasBattled = true;
	}

	public boolean playerInRange(UserCharacter player) {
		if ((getX() == player.getX())
				&& (Math.abs(getY() - player.getY()) <= sightRange)) {
			return true;
		} else if ((getY() == player.getY())
				&& (Math.abs(getX() - player.getX()) <= sightRange)) {
			return true;
		}

		return false;
	}

}
