package gameObjects.Entity;

import java.util.ArrayList;

import gameObjects.Question.Question;
import gameObjects.Question.ScenarioQuestion;

public class Trainer extends NPC {
	
	public ArrayList<ScenarioQuestion> questions;
	public boolean hasBattled;
	static int i = 0;
	static String npcswitch;
	
	
	public Trainer(ScenarioQuestion question, int[] position) {
		super (position[0], position[1], switchNPC());
		hasBattled = false;
		questions = new ArrayList<ScenarioQuestion>();
		questions.add(question);
	}
	private static String switchNPC() {
		if(i == 0){
			i = 1;
			return "Villagers-Split/Boy3.png";
		}
		if(i == 1){
			i = 0;
			return "Villagers-Split/Girl8.png";
		}
		return npcswitch;
		
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
