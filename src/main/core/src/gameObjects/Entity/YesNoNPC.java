package gameObjects.Entity;

public class YesNoNPC extends NPC {
	
	String messageYes;
	String messageNo;
	boolean yes;
	
	//new npc
	public YesNoNPC(Position position, String sprite,String message, String messageY, String messageN){
		
		super(position, sprite, message);
		this.messageYes = messageY;
		this.messageNo = messageN;
		
		
	}

	public String getMessageYes() {
		return messageYes;
	}

	public void setMessageYes(String messageYes) {
		this.messageYes = messageYes;
	}

	public String getMessageNo() {
		return messageNo;
	}

	public void setMessageNo(String messageNo) {
		this.messageNo = messageNo;
	}

	public boolean isYes() {
		return yes;
	}

	public void setYes(boolean yes) {
		this.yes = yes;
	}

}
