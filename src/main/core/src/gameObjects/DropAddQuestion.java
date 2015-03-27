package gameObjects;

public class DropAddQuestion extends Question {

	public DropAddQuestion(String q, String a1, String a2, String a3,
			String a4, String ra) {
		super(q, a1, a2, a3, a4, ra);
	
	}


	public String type() {
		
		return "Drop Add";
	}

}
