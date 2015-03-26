package gameObjects;


import java.util.ArrayList;

import helperClasses.Mapping;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class UserCharacter {
	private int xCoord, yCoord;
	private int xScale = 32, yScale = 32;
	private boolean moveHoriz = true;
	SpriteBatch batch = new SpriteBatch();
	private int moveSpeed = 4;
	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
	Texture userText = new Texture("playerBack.png");
	//for collision
	private Rectangle boundingRectangle;
	
	public UserCharacter(int x, int y) {
		xCoord = x;
		yCoord = y;
		boundingRectangle = new Rectangle();
	}

	public void draw() {
		batch.begin();
		batch.draw(userText, xCoord, yCoord, xScale, yScale);
		batch.end();
//		ShapeRenderer shapeRenderer = new ShapeRenderer();
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
//		shapeRenderer.rect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width,boundingRectangle.height);
//		shapeRenderer.end();
	}
	public Rectangle getRect(){
		return boundingRectangle;
	}
	//character movement
	public void move(int xTouch, int yTouch, boolean changeDirec,ArrayList<Obstacle> obstacles) {
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
		for(Obstacle o : obstacles){
			if(Intersector.overlaps(boundingRectangle, o.getHitBox()))
			{
				boolean yCheck = (boundingRectangle.y > o.getYCoord()-yScale && boundingRectangle.y<o.getYCoord()+yScale+o.getHeight());
				boolean xCheck = (boundingRectangle.x >= o.getXCoord()-xScale && boundingRectangle.x<=o.getXCoord()+xScale+o.getWidth());
				if(o.getHitBox().x > boundingRectangle.x && yCheck)rightBound=false;
				if(o.getHitBox().x < boundingRectangle.x+xScale && yCheck)leftBound=false;
				if(o.getHitBox().y-o.getHitBox().height+12> boundingRectangle.y && xCheck) topBound=false;
				if(o.getHitBox().y +12> boundingRectangle.y-yScale && boundingRectangle.y>o.getHitBox().y&& xCheck)bottomBound=false;
			}
		}
		if (moveHoriz) {
			//move right
			if (xTouch > centerxCoord && !(xTouch-moveSpeed<centerxCoord && xTouch+moveSpeed>centerxCoord) && rightBound)
				xCoord+=moveSpeed;
			//move left
			else if (xTouch < centerxCoord&& !(xTouch-moveSpeed>centerxCoord && xTouch+moveSpeed<centerxCoord) &&leftBound)
				xCoord-=moveSpeed;
			else moveHoriz = false;
		} else if(!moveHoriz) {
			//move up
			if (yTouch > centeryCoord && !(yTouch-moveSpeed<centeryCoord && yTouch+moveSpeed>centeryCoord)&& topBound)
				yCoord+=moveSpeed;
			//move down
			else if (yTouch < centeryCoord&& !(yTouch-moveSpeed>centeryCoord && yTouch+moveSpeed<centeryCoord)&&bottomBound)
				yCoord-=moveSpeed;
			else moveHoriz = true;
		}
		boundingRectangle.set(xCoord,yCoord,xScale, yScale);
	}
	
}
