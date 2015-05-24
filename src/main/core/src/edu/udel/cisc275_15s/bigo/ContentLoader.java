package edu.udel.cisc275_15s.bigo;

import gameObjects.Room;
import gameObjects.Entity.Boss;
import gameObjects.Entity.InfoNPC;
import gameObjects.Entity.NPC;
import gameObjects.Entity.Obstacle;
import gameObjects.Entity.Trainer;
import gameObjects.Entity.YesNoNPC;
import gameObjects.Question.DialogBank;
import gameObjects.Question.Info;
import gameObjects.Question.ScenarioQuestion;
import gameObjects.Question.YesNoQuestion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

public class ContentLoader {

	static Random rand;
	
	public static ArrayList<Room> load(DialogBank dialogFactory) {
		
		rand = new Random();
		
		ArrayList<Room> result = new ArrayList<Room>();

		String cwd = Paths.get("").toAbsolutePath().toString();
		
		// load question/messages

		String pathToDialog = cwd.substring(0, cwd.length() - 7) + "android\\assets\\dialog";
		File[] listOfFiles = new File(pathToDialog).listFiles();
		// for each subject
		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				// add subject questions (info, scenario, yesno) to the bank
				buildDialog(file.getAbsolutePath(), dialogFactory);
			}
		}
		
		// load rooms
		
		String pathToRooms = cwd.substring(0, cwd.length() - 7) + "android\\assets\\rooms";		
		listOfFiles = new File(pathToRooms).listFiles();
		for (File file : listOfFiles) {
			result.add(buildRoom(file.getAbsolutePath(), dialogFactory));
		}

		return result;
	}

	static String getSubject(String path) {
	    int lastSlash = path.lastIndexOf("\\");
	    return path.substring(lastSlash + 1, path.length());
	}
	
	static void buildDialog(String path, DialogBank dialogFactory) {
		String subject = getSubject(path);
		loadInfo(subject, path, dialogFactory);
		loadScenario(subject, path, dialogFactory);
		loadYesNo(subject, path, dialogFactory);
	}
	
	static void loadInfo(String subject, String basePath, DialogBank dialogFactory) {
		String path = basePath + "\\info";
		File[] files = new File(path).listFiles();
		try {
			for (File file : files) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				Info result = new Info();
				while (line != null) {
					if (line.contains("message = ")) {
						result.message = line.split("=")[1].trim();
					} else if (line.contains("notes =")) {
						result.notes = line.split("=")[1].trim();
					}
					line = reader.readLine();
				}
				reader.close();
				dialogFactory.enterDialog(getSubject(basePath), "info", result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	static void loadScenario(String subject, String basePath, DialogBank dialogFactory) {
		String path = basePath + "\\scenario";
		File[] files = new File(path).listFiles();
		try {
			for (File file : files) {
				ScenarioQuestion result = new ScenarioQuestion();
				LinkedList<String> answers = new LinkedList<String>();
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					if (line.contains("question ="))
						result.message = line.split("=")[1].trim();
					else if (line.contains("option =")) 
						answers.add(line.split("=")[1].trim());
					else if (line.contains("answer =")) {
						String text = line.split("=")[1].trim();
						answers.add(text);
						result.answer = text;
					} else if (line.contains("notes ="))					
						result.notes = line.split("=")[1].trim();
			
					line = reader.readLine();
				}
				reader.close();
				
				result.addOptions(answers);
				dialogFactory.enterDialog(getSubject(basePath), "scenario", result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	static void loadYesNo(String subject, String basePath, DialogBank dialogFactory) {
		String path = basePath + "\\yesno";
		File[] files = new File(path).listFiles();
		try {
			for (File file : files) {
				YesNoQuestion result = new YesNoQuestion();
				
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = reader.readLine();
				while (line != null) {
					if (line.contains("question =")) 
						result.message = line.split("=")[1].trim();
					else if (line.contains("notes ="))					
						result.notes = line.split("=")[1].trim();
					else if (line.contains("incorrect ="))
						result.incorrect = line.split("=")[1].trim();
					else if (line.contains("correct ="))
						result.correct = line.split("=")[1].trim();
					else if (line.contains("answer ="))
						result.answer = (line.split("=")[1].trim().equals("yes"));
			
					line = reader.readLine();
				}
				reader.close();
				
				dialogFactory.enterDialog(getSubject(basePath), "yesno", result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	static Room buildRoom(String path, DialogBank dialogFactory) {
		
		Room result = new Room();	
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();
			dialogFactory.resetCount();
			while (line != null) {
				if (line.contains("subject ="))
					result.subject = line.split("=")[1].replace(" ", "");
				else if (line.contains("texture ="))
					result.background = new Texture("room_backgrounds\\" + line.split("=")[1].replace(" ", ""));
				else if (line.contains("room_subject/x,y,width,height/outX,outY =")) {
					String trimmed = line.split("=")[1].replace(" ", "");
					String roomID = trimmed.split("/")[0];
					String[] position = trimmed.split("/")[1].split(",");
					String[] out = trimmed.split("/")[2].split(",");
					result.addDoor(roomID, toInt(position), toInt(out));
				} else if (line.contains("npc_type/x,y =")) {
					String trimmed = line.split("=")[1].replace(" ", "");
					String type = trimmed.split("/")[0];
					String[] position = trimmed.split("/")[1].split(",");
					result.addNPC(generateNPC(result.subject, type, toInt(position), dialogFactory));;
				} else if (line.contains("x,y,width,height =")) {
					String trimmed = line.split("=")[1].replace(" ", "");
					String[] bounds = trimmed.split(",");
					result.addObstacle(new Obstacle(toInt(bounds)));
				}
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	static String getRoomFilePath(File[] files) {
		for (File file : files) {
			if (file.getPath().contains(".room"))
				return file.getAbsolutePath();
		}
		return null;
	}
	
	static int[] toInt(String[] array) {
		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++)
			result[i] = Integer.parseInt(array[i]);
		return result;
	}
	
	static NPC generateNPC(String subject, String npcType, int[] position, DialogBank dialogFactory) {
		if (npcType.equals("yesno")) {
			return generateYesNoNPC(subject, position, dialogFactory);
		} else if (npcType.equals("trainer")) {
			return generateTrainer(subject, position, dialogFactory);
		} else if (npcType.equals("boss")) {
			return generateBoss(subject, position, dialogFactory);
		} else {
			return generateInfoNPC(subject, position, dialogFactory);
		}
	}
	
	static Boss generateBoss(String subject, int[] position, DialogBank dialogFactory) {
		ArrayList<ScenarioQuestion> questions = dialogFactory.getScenarioQuestions(subject);
		return new Boss(questions, position);
	}	
	
	static Trainer generateTrainer(String subject, int[] position, DialogBank dialogFactory) {
		ScenarioQuestion question = dialogFactory.getScenarioQuestion(subject);
		return new Trainer(question, position);
	}
	
	static YesNoNPC generateYesNoNPC(String subject, int[] position, DialogBank dialogFactory) {
		YesNoQuestion question = dialogFactory.getYesNoQuestion(subject);
		return new YesNoNPC(question, position);
	}
	
	static InfoNPC generateInfoNPC(String subject, int[] position, DialogBank dialogFactory) {
		Info info = dialogFactory.getInfo(subject);		
		return new InfoNPC(info, position);
	}
}
