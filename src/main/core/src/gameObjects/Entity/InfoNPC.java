package gameObjects.Entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import gameObjects.Question.Info;

public class InfoNPC extends NPC {
	static String npcswitch;
	static int i = 0;
	
	public InfoNPC (int x, int y, String sprite, String message, String notes) {	
		super(x, y, sprite, message, notes);	
	}
	
	public InfoNPC (int x, int y, String message, String notes) {
		super(x, y, switchNPC(), message, notes);	
	}

	private static String switchNPC() {
		if(i == 0){
			i = 1;
			return "Villagers-Split/Boy2.png";
		}
		if(i == 1){
			i = 0;
			return "Villagers-Split/Girl2.png";
		}
		return "";
		
	}

	public InfoNPC (Info info, int[] position) {	
		super(position[0], position[1], switchNPC(), info.message, info.notes);	
	}
	
	public void drawText(SpriteBatch batch) {
		
		batch.draw(textboxTexture, 20, 60);
		
		String line = "";
		for (int i = 0; i < message.length(); i = i + 50) {
			int k = i + 50;
			while (k < message.length()) {
				if (message.substring(k, k + 1).equals(" "))
					break;
				k++;
			}
			if (this.message.length() >= i + 50) {
				if (i + 50 < message.length())
					line = message.substring(i, k);
				else
					line = message.substring(i) + "\n";
			} else
				line = message.substring(i) + "\n";

			textFont.draw(batch, line, 30, 60 + textboxTexture.getHeight() - 10 - i / 3);

			i = k - 50;
		}
	}
	
}
