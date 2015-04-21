package gameObjects.Entity;

public class YesNoNPC extends NPC {
	
	String message;
	String messageYes;
	String messageNo;
	boolean yes;
	
	public YesNoNPC(int x, int y, String sprite,String message, String messageY, String messageN){
		super(x, y, sprite);
		this.message = message;
		this.messageYes = messageY;
		this.messageNo = messageN;		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
