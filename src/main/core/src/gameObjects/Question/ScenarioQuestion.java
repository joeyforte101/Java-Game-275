package gameObjects.Question;

import java.util.LinkedList;

public class ScenarioQuestion extends Question {
	
	int timesUsed;
	public String[] options;
	public String answer;
	
	public ScenarioQuestion() {
		
	}
	
//	public ScenarioQuestion(String q, String[] a,
//			String ra) {
//		timesUsed = 0;
//		question = q;
//		options = a;
//		answer = ra;
//	}
	
	public boolean isrightanswer(String answer) {
		return this.answer == answer;
	}

	public String getQuestion() {
		return message;
	}

	public void addOptions(LinkedList<String> answers) {
		options = new String[answers.size()];
		for (int i = 0; i < answers.size(); i++) {
			options[i] = answers.get(i);
		}
		
	}

//	public String toString() {
//		return "The Question is: " + question + "\n" + "The First Answer is: "
//				+ answer1 + "\n" + "The Second Answer is: " + answer2 + "\n"
//				+ "The Third Answer is: " + answer3 + "\n"
//				+ "The Fourth Answer is: " + answer4 + "\n"
//				+ "The Correct Answer is: " + rightanswer + "\n";
//				+ "And this Question is of Type: " + type;
//	}
}
