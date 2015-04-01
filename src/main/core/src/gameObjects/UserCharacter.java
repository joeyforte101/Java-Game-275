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
	boolean nottalking = true;
	//for collision
	// private Rectangle hitBox;
	
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
//		batch.draw(texture, x, y, width, height);
//		batch.end();
////		ShapeRenderer shapeRenderer = new ShapeRenderer();
////		shapeRenderer.begin(ShapeType.Filled);
////		shapeRenderer.setColor(Color.RED);
////		shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width,hitBox.height);
////		shapeRenderer.end();
//	}

	//character movement
	public void move(int xTouch, int yTouch, boolean changeDirec,ArrayList<Obstacle> obstacles) {
		yTouch=Mapping.yScreenToText(yTouch);
		int centerx = x+width/2;
		int centery = y+height/2;
		//boundaries
		boolean rightBound = x+width+moveSpeed<WIDTH;
		boolean leftBound = x-moveSpeed>0;
		boolean topBound =  y+height+moveSpeed<HEIGHT;
		boolean bottomBound = y-moveSpeed>0;
		boolean xBigger = (Math.abs(xTouch - centerx) > Math.abs(yTouch - centery));
		//makes sure horizontal or vertical movement is done first
		if(!changeDirec){
			if(xBigger) moveHoriz = true;
			else moveHoriz = false;
		}
		for(Obstacle o : obstacles){
			if(Intersector.overlaps(hitBox, o.getProximityRect()))
			{
				//calculates coordinates for which an intersection would occur
				int botCoord = (int) (o.getHitBox().y-hitBox.height);
				int topCoord = (int) (o.getHitBox().y+o.getHitBox().height);
				int leftCoord = (int)(o.getHitBox().y-hitBox.width);
				int rightCoord = (int) (o.getHitBox().y+o.getHitBox().width);
				//xCheck and yCheck are used to see if the character is on the same plane as the 
				boolean yCheck = (hitBox.y > botCoord&&hitBox.y<topCoord);
				boolean xCheck = (hitBox.x >leftCoord &&hitBox.x<rightCoord);
				//movement stopping logic
				
				
				
				if(hitBox.x+width<o.getHitBox().x && yCheck)rightBound=false;
				else if(hitBox.x>o.getHitBox().x+o.getHitBox().width && yCheck)leftBound=false;
				if(o.getHitBox().y-o.getHitBox().height> hitBox.y && xCheck) topBound=false;
				else if(o.getHitBox().y> hitBox.y-height && hitBox.y>o.getHitBox().y&& xCheck)bottomBound=false;
				if((!rightBound || !leftBound||!topBound||!bottomBound) && (o instanceof InfoNPC)){
			
					((InfoNPC) o).displayprompttrue();
				}
			}
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
	if(nottalking){
		if (moveHoriz) {
			//move right
			if (xTouch > centerx && !(xTouch-moveSpeed<centerx && xTouch+moveSpeed>centerx) && rightBound)
				x+=moveSpeed;
			//move left
			else if (xTouch < centerx&& !(xTouch-moveSpeed>centerx && xTouch+moveSpeed<centerx) &&leftBound)
				x-=moveSpeed;
			else moveHoriz = false;
		} else if(!moveHoriz) {
			//move up
			if (yTouch > centery && !(yTouch-moveSpeed<centery && yTouch+moveSpeed>centery)&& topBound)
				y+=moveSpeed;
			//move down
			else if (yTouch < centery&& !(yTouch-moveSpeed>centery && yTouch+moveSpeed<centery)&&bottomBound)
				y-=moveSpeed;
			else moveHoriz = true;
		}
		hitBox.set(x, y, width, height);
		
	}
	}
	public void settalkingtrue(){
		nottalking = false;
	}
	public void settalkingfalse(){
		nottalking = true;
	}
	public boolean gettalking(){
		return !nottalking;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	
	
//	@Override
//	public Rectangle getHitBox() {
//		return hitBox;
//	}
	
}
