package gameObjects.Entity;

public class InfoNPC extends NPC {
	
private String message;

	public InfoNPC (Position position, String sprite, String message) {
	
		super(position, sprite);
		this.message = message;
	
	}
	
	public String talk(){
		return message;
	}
	
	
}
