package gameObjects.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class DialogBank {

	Random rand;
	static int[] infoarr;
	static int[] trainarr;
	
	HashMap<String, ArrayList<Info>> infoBank;
	HashMap<String, ArrayList<YesNoQuestion>> yesnoBank;
	HashMap<String, ArrayList<ScenarioQuestion>> scenarioBank;
	
	public DialogBank () {
		rand = new Random();
		infoBank = new HashMap<String, ArrayList<Info>>();
		infoarr = new int[10];
		trainarr = new int[10];
		yesnoBank = new HashMap<String, ArrayList<YesNoQuestion>>();
		scenarioBank = new HashMap<String, ArrayList<ScenarioQuestion>>();
		String[] subjects = {"advisor", "dates","overworld","resources","udsis","finalroom"};
		for(String s : subjects) {
			infoBank.put(s, new ArrayList<Info>());
			yesnoBank.put(s, new ArrayList<YesNoQuestion>());
			scenarioBank.put(s, new ArrayList<ScenarioQuestion>());
		}
	}
	
	public void enterDialog(String subject, String type, Dialog dialog) {
		if (type == "info") 
			infoBank.get(subject).add((Info)dialog);
		else if (type == "yesno")
			yesnoBank.get(subject).add((YesNoQuestion)dialog);
		else if (type == "scenario")
			scenarioBank.get(subject).add((ScenarioQuestion)dialog);
	}
	
	public Info getInfo(String subject) {
		ArrayList<Info> bank = infoBank.get(subject);
		int num = rand.nextInt(bank.size());
		while(infoarr[num] == 1){
			System.out.println(num);
			num = rand.nextInt(bank.size());
		}
		infoarr[num]=1;
		return bank.get(num);
	}
	
	public ScenarioQuestion getScenarioQuestion(String subject) {
		ArrayList<ScenarioQuestion> bank = scenarioBank.get(subject);
		int num = rand.nextInt(bank.size());
		while(trainarr[num] == 1){
			System.out.println(num);
			num = rand.nextInt(bank.size());
		}
		trainarr[num]=1;
		return bank.get(num);
	}
	
	public ArrayList<ScenarioQuestion> getScenarioQuestions(String subject) {
		ArrayList<ScenarioQuestion> result = new ArrayList<ScenarioQuestion>();
		ArrayList<ScenarioQuestion> bank = scenarioBank.get(subject);
		LinkedList<Integer> openSet = new LinkedList<Integer>();	
		for(int i = 0; i < bank.size(); i++)
			openSet.add(new Integer(i));
		for(int i = 0; i < 3; i++) {
			int index = rand.nextInt(openSet.size());
			result.add(bank.get(index));
			openSet.remove(index);
		}
		return result;
	}
	
	public YesNoQuestion getYesNoQuestion(String subject) {
		ArrayList<YesNoQuestion> bank = yesnoBank.get(subject);
		return bank.get(rand.nextInt(bank.size()));
	}
	
	public void resetCount(){
		infoarr= new int[10];
		trainarr = new int[10];
	}
}
