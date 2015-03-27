package gameObjects;

import java.util.ArrayList;

import helperClasses.Mapping;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class UserCharacter extends Entity {
	boolean moveHoriz = true;
//	SpriteBatch batch = new SpriteBatch();
	int moveSpeed = 4;
	public final int WIDTH = Gdx.graphics.getWidth();
	public final int HEIGHT = Gdx.graphics.getHeight();
	//for collision
	// private Rectangle boundingRectangle;
	
	public UserCharacter(int xpos, int ypos) {
		x = xpos;
		y = ypos;
		width = 32;
		height = 32;
		texture = new Texture("playerBack.png");
		hitBox = new Rectangle();
	}

//	public void draw() {
//		batch.begin();
//		batch.draw(texture, xCoord, yCoord, xScale, yScale);
//		batch.end();
////		ShapeRenderer shapeRenderer = new ShapeRenderer();
////		shapeRenderer.begin(ShapeType.Filled);
////		shapeRenderer.setColor(Color.RED);
////		shapeRenderer.rect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width,boundingRectangle.height);
////		shapeRenderer.end();
//	}

	//character movement
	public void move(int xTouch, int yTouch, boolean changeDirec,ArrayList<Obstacle> obstacles) {
		yTouch=Mapping.yScreenToText(yTouch);
		int centerxCoord = x+width/2;
		int centeryCoord = y+height/2;
		//boundaries
		boolean rightBound = x+width+moveSpeed<WIDTH;
		boolean leftBound = x-moveSpeed>0;
		boolean topBound =  y+height+moveSpeed<HEIGHT;
		boolean bottomBound = y-moveSpeed>0;
		boolean xBigger = (Math.abs(xTouch - centerxCoord) > Math.abs(yTouch - centeryCoord));
		//makes sure horizontal or vertical movement is done first
		if(!changeDirec){
			if(xBigger) moveHoriz = true;
			else moveHoriz = false;
		}
		for(Obstacle o : obstacles){
			if(Intersector.overlaps(hitBox, o.hitBox))
			{
				// What side of the obstacle am I on
				boolean yCheck = (hitBox.y > o.y-height && hitBox.y<o.y+height+o.height);
				boolean xCheck = (hitBox.x >= o.x-width && hitBox.x<=o.x+width+o.width);
				if(o.hitBox.x > hitBox.x && yCheck)rightBound=false;
				if(o.hitBox.x < hitBox.x+width && yCheck)leftBound=false;
				if(o.hitBox.y-o.hitBox.height+12> hitBox.y && xCheck)topBound=false;
				if(o.hitBox.y +12> hitBox.y-height && hitBox.y>o.hitBox.y&& xCheck)bottomBound=false;
			}
		}
		if (moveHoriz) {
			//move right
			if (xTouch > centerxCoord && !(xTouch-moveSpeed<centerxCoord && xTouch+moveSpeed>centerxCoord) && rightBound)
				x+=moveSpeed;
			//move left
			else if (xTouch < centerxCoord&& !(xTouch-moveSpeed>centerxCoord && xTouch+moveSpeed<centerxCoord) &&leftBound)
				x-=moveSpeed;
			else moveHoriz = false;
		} else if(!moveHoriz) {
			//move up
			if (yTouch > centeryCoord && !(yTouch-moveSpeed<centeryCoord && yTouch+moveSpeed>centeryCoord)&& topBound)
				y+=moveSpeed;
			//move down
			else if (yTouch < centeryCoord&& !(yTouch-moveSpeed>centeryCoord && yTouch+moveSpeed<centeryCoord)&&bottomBound)
				y-=moveSpeed;
			else moveHoriz = true;
		}
		hitBox.set(x, y, width, height);
	}

//	@Override
//	public Rectangle getHitBox() {
//		return hitBox;
//	}
	
}
