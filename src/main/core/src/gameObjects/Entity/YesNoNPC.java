package gameObjects.Entity;

import gameObjects.Question.YesNoQuestion;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class YesNoNPC extends NPC {
	
	String correctResponse;
	String incorrectResponse;
	String question;
	boolean answer;
	public boolean answered;
	boolean answeredCorrectly;
	static int i = 0;
	static String npcSwitch;

	public YesNoNPC(YesNoQuestion question, int[] position){
		super(position[0], position[1], switchNPC(), question.message, question.notes);
		this.question = question.question;
		correctResponse = question.correct;
		incorrectResponse = question.incorrect;	
		answer = question.answer;
		answered = false;
	}
	
//	public YesNoNPC(int x, int y, String sprite, String message, String messageY, String messageN, String notes){
//		super(x, y, sprite, message, notes);
//		this.messageYes = messageY;
//		this.messageNo = messageN;	
//	}

//	public YesNoNPC(int[] position, String message, String messageY, String messageN, String notes){
//		super(position[0], position[1], switchNPC(), message, notes);
//		this.messageYes = messageY;
//		this.messageNo = messageN;	
//	}
//	
//	public YesNoNPC(int x, int y, String message, String messageY, String messageN, String notes){
//		super(x, y, switchNPC(), message, notes);
//		this.messageYes = messageY;
//		this.messageNo = messageN;
//	}
	
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
	
//	public String getMessageYes() {
//		return messageYes;
//	}
//
//	public void setMessageYes(String messageYes) {
//		this.messageYes = messageYes;
//	}
//
//	public String getMessageNo() {
//		return messageNo;
//	}
//
//	public void setMessageNo(String messageNo) {
//		this.messageNo = messageNo;
//	}
	
	public String getQuestion(){
		return question;
	}

	public boolean sendAnswer(String msg) {
		answered = true;
		if (msg == "yes" && answer) {
			answeredCorrectly = true;
			return true;
		} else if (msg == "no" && !answer) {
			answeredCorrectly = true;
			return true;
		} else
			answeredCorrectly = false;
		return false;
	}
	
	public void drawText(SpriteBatch batch){
		batch.draw(textboxTexture, 20, 60);
		
		if (answeredCorrectly) {
			textFont.draw(batch, correctResponse, 30, 60 + textboxTexture.getHeight() - 10);
		} else if (answered && !answeredCorrectly) {
			textFont.draw(batch, incorrectResponse, 30, 60 + textboxTexture.getHeight() - 10);
		} else
			textFont.drawWrapped(batch, question, 30, 60 + textboxTexture.getHeight() - 10, textboxTexture.getWidth() - 10);
			
//		switch(this.understood){
//		case 0: textFont.drawWrapped(batch, getQuestion(), 30, 60 + textbox.getHeight() - 10, textbox.getWidth()-10); break;
//		case 1: textFont.draw(batch, getMessageYes(), 30, 60 + textbox.getHeight() - 10); break;
//		case 2: textFont.draw(batch, getMessageNo(), 30, 60 + textbox.getHeight() - 10); break;
//		}
		
	}

}
