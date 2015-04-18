package gameObjects.Entity;

import gameObjects.Question.Question;

public class Trainer extends NPC {
	
	// How far can a trainer see in either compass direction 
	private final int sightRange = 75;
	
	private Question[] questions;
	private String message;
	private boolean hasBattled = false;
	
	
	public Trainer (Position position, String sprite, String message, Question[] questions){
		
		super(position, sprite);
		this.questions = questions;
		this.message = message;
		
	}
	
	public Question[] getQuestions(){
		return this.questions;
	}
	
	public void battled(){
		this.hasBattled = true;
	}
	
	public boolean playerInRange(UserCharacter player){
		
		if((this.position.getX() == player.position.getX()) && (Math.abs(this.position.getY() - player.position.getY()) <= this.sightRange)){
			return true;
		}
		else if((this.position.getY() == player.position.getY()) && (Math.abs(this.position.getX() - player.position.getX()) <= this.sightRange)){
			return true;
		}
		
		return false;
	}
	
}
