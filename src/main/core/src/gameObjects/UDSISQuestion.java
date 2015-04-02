package gameObjects;

import gameObjects.Question.Question;

public class UDSISQuestion extends Question {

	public UDSISQuestion(String q, String a1, String a2, String a3, String a4,
			String ra) {
		super(q, a1, a2, a3, a4, ra);
		
	}


	public String type() {
		
		return "UDSIS";
	}

}
