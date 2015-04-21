package gameObjects.Entity;

public class InfoNPC extends NPC {

	private String message;

	public InfoNPC(int x, int y, String sprite, String message) {
		super(x, y, sprite);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
