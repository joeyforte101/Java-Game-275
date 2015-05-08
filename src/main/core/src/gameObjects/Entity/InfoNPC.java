package gameObjects.Entity;

public class InfoNPC extends NPC {

	public InfoNPC (int x, int y, String sprite, String message, String notes) {
	
		super(x, y, sprite, message, notes);
	
	}
	
	public InfoNPC (int x, int y, String message, String notes) {
	
		super(x, y, "npc_sprites/trainer.png", message, notes);	
	}
	
}
