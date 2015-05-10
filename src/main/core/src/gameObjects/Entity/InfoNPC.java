package gameObjects.Entity;

import gameObjects.Question.Info;

public class InfoNPC extends NPC {

	public InfoNPC (int x, int y, String sprite, String message, String notes) {
	
		super(x, y, sprite, message, notes);
	
	}
	
	public InfoNPC (int x, int y, String message, String notes) {
	
		super(x, y, "npc_sprites/trainer.png", message, notes);	
	}

	public InfoNPC (Info info, int[] position) {
	
		super(position[0], position[1], "npc_sprites/trainer.png", info.message, info.notes);
	
	}
	
}
