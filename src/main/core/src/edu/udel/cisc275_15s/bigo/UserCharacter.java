package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UserCharacter {
	private int xCoord, yCoord;
	private int xScale = 32, yScale = 32;
	private boolean moveHoriz = true;
	SpriteBatch batch = new SpriteBatch();
	private int moveSpeed = 5;
	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
	Texture userText = new Texture("playerBack.png");
	Texture mapBackground = new Texture("background.png");

	public UserCharacter(int x, int y) {
		xCoord = x;
		yCoord = y;
	}

	public void draw() {
		batch.begin();
		batch.draw(mapBackground, 0,0);
		batch.draw(userText, xCoord, yCoord, xScale, yScale);
		batch.end();
	}

	//character movement
	public void move(int xTouch, int yTouch, boolean changeDirec) {
		yTouch=Mapping.yScreenToText(yTouch);
		int centerxCoord = xCoord+xScale/2;
		int centeryCoord = yCoord+yScale/2;
		//boundaries
		boolean rightBound = xCoord+xScale+moveSpeed<WIDTH;
		boolean leftBound = xCoord-moveSpeed>0;
		boolean topBound =  yCoord+yScale+moveSpeed<HEIGHT;
		boolean bottomBound = yCoord-moveSpeed>0;
		boolean xBigger = (Math.abs(xTouch - centerxCoord) > Math.abs(yTouch - centeryCoord));
		//makes sure horizontal or vertical movement is done first
		if(!changeDirec){
			if(xBigger) moveHoriz = true;
			else moveHoriz = false;
		}
		if (moveHoriz) {
			//move right
			if (xTouch > centerxCoord && !(xTouch-moveSpeed<centerxCoord && xTouch+moveSpeed>centerxCoord) && rightBound)
				xCoord+=moveSpeed;
			//move left
			else if (xTouch < centerxCoord&& !(xTouch-moveSpeed>centerxCoord && xTouch+moveSpeed<centerxCoord) &&leftBound)
				xCoord-=moveSpeed;
			else moveHoriz = false;
		} else {
			//move up
			if (yTouch > centeryCoord && !(yTouch-moveSpeed<centeryCoord && yTouch+moveSpeed>centeryCoord)&& topBound)
				yCoord+=moveSpeed;
			//move down
			else if (yTouch < centeryCoord&& !(yTouch-moveSpeed>centeryCoord && yTouch+moveSpeed<centeryCoord)&&bottomBound)
				yCoord-=moveSpeed;
			else moveHoriz = true;
		}
	}
}
