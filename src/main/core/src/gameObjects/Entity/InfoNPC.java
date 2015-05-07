package gameObjects.Entity;

public class InfoNPC extends NPC {

	public InfoNPC (int x, int y, String sprite, String message) {
	
		super(x, y, sprite, message);
	
	}
	
	public InfoNPC (int x, int y, String message) {
	
		super(x, y, "npc_sprites/trainer.png", message);	
	}
	
}
