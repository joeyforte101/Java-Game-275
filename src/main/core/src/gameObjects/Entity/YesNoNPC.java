package gameObjects.Entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YesNoNPC extends NPC {
	
	String messageYes;
	String messageNo;
	boolean understood;
	
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
		return understood;
	}

	public void setUderstood(boolean yes) {
		this.understood = yes;
	}
	
	public void drawText(SpriteBatch batch, boolean understood){
		Texture textbox = new Texture("speech bubble.png");
		BitmapFont text = new BitmapFont();
		text.setColor(Color.BLACK);
		batch.draw(textbox, 20, 60);
		if(understood)
			text.draw(batch, getMessageYes(), 30, 60 + textbox.getHeight() - 10);
		else{
			text.draw(batch, getMessageNo(), 30, 60 + textbox.getHeight() - 10);
		}
			
	}

}
