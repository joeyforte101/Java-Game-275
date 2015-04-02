package gameObjects.Question;


public class AdvisementQuestion extends Question {

	public AdvisementQuestion(String q, String a1, String a2, String a3,
			String a4, String ra) {
		super(q, a1, a2, a3, a4, ra);
	
	}


	public String type() {
		
		return "Advisement";
	}

}
