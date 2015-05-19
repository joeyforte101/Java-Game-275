package gameObjects.Entity;

import edu.udel.cisc275_15s.bigo.Database;
import gameObjects.Question.ScenarioQuestion;

import java.util.ArrayList;

public class Boss extends Trainer {

	public Boss(ArrayList<ScenarioQuestion> questions, int[] position) {
		super(questions, position);
	}	
	
	@Override
	public void setHasBattled(boolean hasBattled) {
		this.hasBattled = hasBattled;
		Database.createEntry(questions.get(0).message, (questions.get(0).isrightanswer(questions.get(0).answer)));
		Database.createEntry(questions.get(1).message, (questions.get(1).isrightanswer(questions.get(1).answer)));
		Database.createEntry(questions.get(2).message, (questions.get(2).isrightanswer(questions.get(2).answer)));
		
	}
}
