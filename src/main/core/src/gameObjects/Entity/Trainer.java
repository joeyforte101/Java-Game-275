package gameObjects.Entity;

import gameObjects.Question.Question;

public class Trainer extends NPC {
	

	
	private Question[] questions;
	private boolean hasBattled;
	
	
	public Trainer (int x, int y, String sprite, String message, Question[] questions, String notes){
		
		super(x, y, sprite, message, notes);
		this.hasBattled = false;
		this.questions = questions;
		
	}
	
	public Trainer (int x, int y, String message, Question[] questions, String notes){
		
		super(x, y, "npc_sprites/trainer.png", message ,notes);
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
