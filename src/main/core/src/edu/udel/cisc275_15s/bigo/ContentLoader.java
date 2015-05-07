package edu.udel.cisc275_15s.bigo;

import gameObjects.Room;
import gameObjects.Entity.NPC;
import gameObjects.Entity.Obstacle;
import gameObjects.Entity.YesNoNPC;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Stream;

import com.badlogic.gdx.graphics.Texture;

public class ContentLoader {

	static String[] YesNoMessageBank;
	static String[] YesNoYesBank;
	static String[] YesNoNoBank;
	static Random rand;
	
	public static ArrayList<Room> load() {
		
		rand = new Random();
		
		ArrayList<Room> result = new ArrayList<Room>();
		
		// load question/message banks
		
		// TODO
		
		// load rooms
		
		String cwd = Paths.get("").toAbsolutePath().toString();
		String path = cwd.substring(0, cwd.length() - 7) + "android\\assets\\rooms";
		
		File[] listOfFiles = new File(path).listFiles();
		for (File file : listOfFiles) {
			if (file.isDirectory()) {
				result.add(buildRoom(file.getAbsolutePath()));
			}
		}

		return result;
	}
	
	static Room buildRoom(String path) {
		
		Room result = new Room();	
		
		String pathToRoomFile = getRoomFilePath(new File(path).listFiles());
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathToRoomFile));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("Hash ="))
					result.hash = line.split("=")[1].replace(" ", "");
				else if (line.contains("Texture ="))
					result.background = new Texture(line.split("=")[1].replace(" ", ""));
				else if (line.contains("room_hash/x,y,width,height/outX,outY =")) {
					String trimmed = line.split("=")[1].replace(" ", "");
					String hash = trimmed.split("/")[0];
					String[] position = trimmed.split("/")[1].split(",");
					String[] out = trimmed.split("/")[2].split(",");
					result.addDoor(hash, toInt(position), toInt(out));
				}
				else if (line.contains("npc_type/x,y =")) {
					String trimmed = line.split("=")[1].replace(" ", "");
					String type = trimmed.split("/")[0];
					String[] position = trimmed.split("/")[1].split(",");
					result.addNPC(generateNPC(type, toInt(position)));;
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
	
	static NPC generateNPC(String type, int[] position) {
		if (type == "yes_no") {
			return generateYesNoNPC(position);
		} else if (type == "trainer") {
			// TODO
			return generateYesNoNPC(position);
		} else {
			// TODO
			return generateYesNoNPC(position);
		}
	}
	
	static YesNoNPC generateYesNoNPC(int[] position) {
		String message = YesNoMessageBank[rand.nextInt(YesNoMessageBank.length)];
		String yes = YesNoYesBank[rand.nextInt(YesNoYesBank.length)];
		String no = YesNoNoBank[rand.nextInt(YesNoNoBank.length)];
		return new YesNoNPC(position, message, yes, no);
	}
}
