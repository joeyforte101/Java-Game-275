package gameObjects;

public abstract class Question {
private int timesused;
private String question;
private String answer1;
private String answer2;
private String answer3;
private String answer4;
private String rightanswer;

public Question(String q, String a1, String a2, String a3, String a4, String ra)
{
	timesused = 0;
	question = q;
	answer1 = a1;
	answer2 = a2;
	answer3 = a3;
	answer4 = a4;
	rightanswer = ra;
}

public boolean isrightanswer(String answer)
		{
	return rightanswer == answer;
		}
public String getQuestion()
{
	return question;
}
public String getanswer1()
{
	return answer1;
}
public String getanswer2()
{
	return answer2;
}
public String getanswer3()
{
	return answer3;
}
public String getanswer4()
{
	return answer4;
}

public int gettimesused()
{
	return timesused;
}
public abstract String type();

public String toString()
{
return "The Question is: " + question + "\n"+
 "The First Answer is: " + answer1 + "\n" +
 "The Second Answer is: " + answer2 + "\n" +
 "The Third Answer is: " + answer3 + "\n" +
 "The Fourth Answer is: " + answer3 + "\n" +
 "The Correct Answer is: " + rightanswer + "\n" +
 "And this Question is of Type: " + this.type();
	
}
}
