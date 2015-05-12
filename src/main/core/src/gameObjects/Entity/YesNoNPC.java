package gameObjects.Entity;

import gameObjects.Question.YesNoQuestion;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YesNoNPC extends NPC {
	
	String messageYes;
	String messageNo;
	String question;
	int understood;
	static int i = 0;
	static String npcSwitch;

	public YesNoNPC(YesNoQuestion question, int[] position){
		super(position[0], position[1], switchNPC(), question.message, question.notes);
		this.question = question.question;
		this.messageYes = question.yes;
		this.messageNo = question.no;	
	}
	
	public YesNoNPC(int x, int y, String sprite, String message, String messageY, String messageN, String notes){
		super(x, y, sprite, message, notes);
		this.messageYes = messageY;
		this.messageNo = messageN;	
	}

	public YesNoNPC(int[] position, String message, String messageY, String messageN, String notes){
		super(position[0], position[1], switchNPC(), message, notes);
		this.messageYes = messageY;
		this.messageNo = messageN;	
	}
	
	public YesNoNPC(int x, int y, String message, String messageY, String messageN, String notes){
		super(x, y, switchNPC(), message, notes);
		this.messageYes = messageY;
		this.messageNo = messageN;
	}
	private static String switchNPC() {
		if(i == 0){
			i = 1;
			return "Villagers-Split/Boy5.png";
		}
		if(i == 1){
			i = 0;
			return "Villagers-Split/Girl7.png";
		}
		return npcSwitch;
		
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
	
	public String getQuestion(){
		return question;
	}

	public int isYes() {
		return understood;
	}

	public void setUnderstood(int msg) {
		this.understood = msg;
	}
	
	public void drawText(SpriteBatch batch){
		Texture textbox = new Texture("speech bubble.png");
		BitmapFont text = new BitmapFont();
		text.setColor(Color.BLACK);
		batch.draw(textbox, 20, 60);
		
		switch(this.understood){
		case 0: text.drawWrapped(batch, getQuestion(), 30, 60 + textbox.getHeight() - 10, textbox.getWidth()-10); break;
		case 1: text.draw(batch, getMessageYes(), 30, 60 + textbox.getHeight() - 10); break;
		case 2: text.draw(batch, getMessageNo(), 30, 60 + textbox.getHeight() - 10); break;
		}
		
	}

}
