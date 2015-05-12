package gameObjects.Entity;

import gameObjects.Question.Info;

public class InfoNPC extends NPC {
	static String npcswitch;
	static int i =0;
	public InfoNPC (int x, int y, String sprite, String message, String notes) {
	
		super(x, y, sprite, message, notes);
	
	}
	
	public InfoNPC (int x, int y, String message, String notes) {
		super(x, y, switchNPC(), message, notes);	
	}

	private static String switchNPC() {
		if(i == 0){
			i = 1;
			return "Villagers-Split/Boy2.png";
		}
		if(i == 1){
			i = 0;
			return "Villagers-Split/Girl2.png";
		}
		return "";
		
	}

	public InfoNPC (Info info, int[] position) {
	
		super(position[0], position[1], switchNPC(), info.message, info.notes);
	
	}
	
}
