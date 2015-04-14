package gameObjects.Entity;

public class YesNoNPC extends NPC {
	
	String message;
	String messageYes;
	String messageNo;
	boolean yes;
	
	//new npc
	public YesNoNPC(Position position, String sprite,String message, String messageY, String messageN){
		
		super(position, sprite);
		this.message = message;
		this.messageYes = messageY;
		this.messageNo = messageN;
		
		
	}

}
