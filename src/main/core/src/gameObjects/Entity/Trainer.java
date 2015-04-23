package gameObjects.Entity;

import gameObjects.Question.Question;

public class Trainer extends NPC {
	

	
	private Question[] questions;
	private boolean hasBattled;
	
	
	public Trainer (Position position, String sprite, String message, Question[] questions){
		
		super(position, sprite, message);
		this.hasBattled = false;
		this.questions = questions;
		
	}
	
	public Question[] getQuestions(){
		return this.questions;
	}
	
	public void battled(){
		this.hasBattled = true;
	}
	
	public boolean hasBattled() {
		return hasBattled;
	}

	public void setHasBattled(boolean hasBattled) {
		this.hasBattled = hasBattled;
	}
	
}
