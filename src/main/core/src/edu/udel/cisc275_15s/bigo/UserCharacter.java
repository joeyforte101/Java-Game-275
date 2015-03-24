package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserCharacter {
	private int xCoord, yCoord;
	private int xScale = 32, yScale = 32;
	SpriteBatch batch = new SpriteBatch();
	private int moveSpeed = 5;
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
		int centerxCoord = xCoord+xScale/2;
		int centeryCoord = yCoord+yScale/2;
		boolean xBigger = (Math.abs(xTouch - centerxCoord) > Math.abs(yTouch - centeryCoord));

		if (xBigger) {
			if (xTouch > centerxCoord && !(xTouch-moveSpeed<centerxCoord && xTouch+moveSpeed>centerxCoord))
				xCoord+=moveSpeed;
			else if (xTouch < centerxCoord&& !(xTouch-moveSpeed>centerxCoord && xTouch+moveSpeed<centerxCoord))
				xCoord-=moveSpeed;
		} else {
			if (yTouch > centeryCoord && !(yTouch-moveSpeed<centeryCoord && yTouch+moveSpeed>centeryCoord))
				yCoord+=moveSpeed;
			else if (yTouch < centeryCoord&& !(yTouch-moveSpeed>centeryCoord && yTouch+moveSpeed<centeryCoord))
				yCoord-=moveSpeed;
		}
	}
}
