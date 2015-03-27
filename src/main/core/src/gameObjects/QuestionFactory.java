package gameObjects;

public class QuestionFactory {
public static Question getQuestion(String line)
{
	char[] LineArray = line.toCharArray();
	int startpos = 0;

	String Question = null;
	String answer1 = null;
	String answer2 = null;
	String answer3 = null;
	String answer4 = null;
	String rightanswer = null;
	boolean RA = false;
	
	//getting the question from line
	for(int i = 0; i < line.length(); i++)
	{
		if(LineArray[i] == '|')
		{
			Question = line.substring(startpos,i);
			startpos = i + 1;
			break;
		}
	}
	for(int i = startpos; i < line.length(); i++)
	{
		if(LineArray[i] == '[')
		{
			RA = true;
			startpos = startpos + 3;	
		}
		if(LineArray[i] == '|')
		{
			answer1 = line.substring(startpos,i);
			
			if(RA)
				{
				rightanswer = answer1;
				RA = false;
				}
				
			startpos = i + 1;
			break;
		}
	}
	
	for(int i = startpos; i < line.length(); i++)
	{
		if(LineArray[i] == '[')
		{
			RA = true;
			startpos = startpos + 3;	
		}
		if(LineArray[i] == '|')
		{
			answer2 = line.substring(startpos,i);
			
			if(RA)
				{
				rightanswer = answer2;
				RA = false;
				}
				
			startpos = i + 1;
			break;
		}
	}
	for(int i = startpos; i < line.length(); i++)
	{
		if(LineArray[i] == '[')
		{
			RA = true;
			startpos = startpos + 3;	
		}
		if(LineArray[i] == '|')
		{
			answer3 = line.substring(startpos,i);
			
			if(RA)
				{
				rightanswer = answer3;
				RA = false;
				}
				
			startpos = i + 1;
			break;
		}
	}
	for(int i = startpos; i < line.length(); i++)
	{
		if(LineArray[i] == '[')
		{
			RA = true;
			startpos = startpos + 3;		
		}
		if(LineArray[i] == '|')
		{
			answer4 = line.substring(startpos,i);
			
			if(RA)
				{
				rightanswer = answer4;
				RA = false;
				}
				
			startpos = i + 1;
			break;
		}
	}
	
	if(LineArray[startpos] == 'D')
		return new DropAddQuestion(Question,answer1,answer2,answer3,answer4,rightanswer);
	if(LineArray[startpos] == 'A')
		return new AdvisementQuestion(Question,answer1,answer2,answer3,answer4,rightanswer);
	if(LineArray[startpos] == 'U')
		return new UDSISQuestion(Question,answer1,answer2,answer3,answer4,rightanswer);
	
	
	return null;
}


}
