package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserCharacter {
	private int xCoord, yCoord;
	private int xScale = 32, yScale = 32;
	SpriteBatch batch = new SpriteBatch();
	Texture userText = new Texture("badlogic.jpg");

	public UserCharacter(int x, int y) {
		xCoord = x;
		yCoord = y;
	}

	public void draw() {
		batch.begin();
		batch.draw(userText, xCoord, yCoord, xScale, yScale);
		batch.end();
	}

	public void move(int xTouch, int yTouch) {
		yTouch=Mapping.yScreenToText(yTouch);
		boolean xBigger = (Math.abs(xTouch - xCoord) > Math.abs(yTouch - yCoord));

		if (xBigger) {
			if (xTouch > xCoord)
				xCoord+=5;
			else if (xTouch < xCoord)
				xCoord-=5;
		} else {
			if (yTouch > yCoord)
				yCoord+=5;
			else if (yTouch < yCoord)
				yCoord-=5;
		}
	}
}
