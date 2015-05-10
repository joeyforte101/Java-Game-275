package gameObjects.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DialogBank {

	Random rand;
	
	HashMap<String, ArrayList<Info>> infoBank;
	HashMap<String, ArrayList<YesNoQuestion>> yesnoBank;
	HashMap<String, ArrayList<ScenarioQuestion>> scenarioBank;
	
	public DialogBank () {
		rand = new Random();
		infoBank = new HashMap<String, ArrayList<Info>>();
		yesnoBank = new HashMap<String, ArrayList<YesNoQuestion>>();
		scenarioBank = new HashMap<String, ArrayList<ScenarioQuestion>>();
		String[] subjects = {"advisor", "dates","overworld","resources","udsis"};
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
		return bank.get(rand.nextInt(bank.size()));
	}
	
	public ScenarioQuestion getScenarioQuestion(String subject) {
		ArrayList<ScenarioQuestion> bank = scenarioBank.get(subject);
		return bank.get(rand.nextInt(bank.size()));
	}
	
	public YesNoQuestion getYesNoQuestion(String subject) {
		ArrayList<YesNoQuestion> bank = yesnoBank.get(subject);
		return bank.get(rand.nextInt(bank.size()));
	}
	
}