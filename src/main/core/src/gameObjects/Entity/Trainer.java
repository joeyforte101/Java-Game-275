package gameObjects.Entity;

import java.util.ArrayList;

import gameObjects.Question.Question;
import gameObjects.Question.ScenarioQuestion;

public class Trainer extends NPC {
	
	public ArrayList<ScenarioQuestion> questions;
	public boolean hasBattled;	
	
	public Trainer(ScenarioQuestion question, int[] position) {
		super (position[0], position[1], "npc_sprites/trainer.png");
		hasBattled = false;
		questions = new ArrayList<ScenarioQuestion>();
		questions.add(question);
	}
	
//	public Trainer (int x, int y, String sprite, String message, Question[] questions, String notes){
//		
//		super(x, y, sprite, message, notes);
//		this.hasBattled = false;
//		this.question = questions;		
//	}
	
//	public Trainer (int x, int y, String message, Question[] questions, String notes){
//		
//		super(x, y, "npc_sprites/trainer.png", message ,notes);
//		this.hasBattled = false;
//		this.questions = questions;		
//	}	
	
//	public Question[] getQuestions(){
//		return this.questions;
//	}
	
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
