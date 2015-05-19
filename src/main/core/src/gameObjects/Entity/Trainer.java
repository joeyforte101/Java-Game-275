package gameObjects.Entity;

import java.util.ArrayList;

import gameObjects.Question.Question;
import gameObjects.Question.ScenarioQuestion;

public class Trainer extends NPC {
	
	public ArrayList<ScenarioQuestion> questions;
	public boolean hasBattled;
	public boolean defeated;
	static int i = 0;
	static String npcswitch;	
	
	public Trainer(ScenarioQuestion question, int[] position) {
		super (position[0], position[1], switchNPC());
		hasBattled = false;
		defeated = false;
		questions = new ArrayList<ScenarioQuestion>();
		questions.add(question);
	}

	public Trainer(ArrayList<ScenarioQuestion> questions, int[] position) {
		super (position[0], position[1], switchNPC());
		hasBattled = false;
		this.questions = questions;
	}
	
	public Trainer(int[] position) {
		super (position[0], position[1], switchNPC());
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
	
	public void battled(){
		this.hasBattled = true;
	}
	
	public boolean hasBattled() {
		return hasBattled;
	}
	
	public boolean beaten() {
		for (Question q : questions) {
			if (!q.completed)
				return false;
		}
		return true;
	}

	public void setHasBattled(boolean hasBattled) {
		this.hasBattled = hasBattled;
	}
	
}
