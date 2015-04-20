package gameObjects.Entity;

import java.util.ArrayList;
import java.util.List;

import helperClasses.Directions;
import helperClasses.Mapping;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class UserCharacter extends Entity {
	boolean moveHoriz = true;
	// SpriteBatch batch = new SpriteBatch();
	int moveSpeed = 4;
	public final int WIDTH = Gdx.graphics.getWidth();
	public final int HEIGHT = Gdx.graphics.getHeight();
	boolean nottalking = true;
	Position oldPosition = new Position(0,0);
	Directions direction;

	// for collision
	// private Rectangle hitBox;

	public UserCharacter(Position position) {

		this.position = position;
		oldPosition.setX(position.getX());
		oldPosition.setY(position.getY());
		width = 32;
		height = 32;
		texture = new Texture("playerBack.png");
		hitBox = new Rectangle();
	}

	// public void draw() {
	// batch.begin();
	// batch.draw(texture, x, y, width, height);
	// batch.end();
	// // ShapeRenderer shapeRenderer = new ShapeRenderer();
	// // shapeRenderer.begin(ShapeType.Filled);
	// // shapeRenderer.setColor(Color.RED);
	// // shapeRenderer.rect(hitBox.x, hitBox.y, hitBox.width,hitBox.height);
	// // shapeRenderer.end();
	// }

	// character movement
	public void move(int xTouch, int yTouch, boolean changeDirec, List<NPC> npcs) {

		yTouch = Mapping.yScreenToText(yTouch);
		oldPosition.setX(position.getX());
		oldPosition.setY(position.getY());
		int centerx = position.getX() + width / 2;
		int centery = position.getY() + height / 2;

		// boundaries
		boolean rightBound = (position.getX() + width + moveSpeed) < WIDTH;
		boolean leftBound = (position.getX() - moveSpeed) > 0;
		boolean topBound = (position.getY() + height + moveSpeed) < HEIGHT;
		boolean bottomBound = (position.getY() - moveSpeed) > 0;
		boolean xBigger = (Math.abs(xTouch - centerx) > Math.abs(yTouch
				- centery));

		// makes sure horizontal or vertical movement is done first
		if (!changeDirec) {
			if (xBigger)
				moveHoriz = true;
			else
				moveHoriz = false;
		}

		// for(NPC o : npcs){
		//
		// if(Intersector.overlaps(hitBox, o.getProximityRect()))
		// {
		// //calculates coordinates for which an intersection would occur
		// int botCoord = (int) (o.getHitBox().y - hitBox.height);
		// int topCoord = (int) (o.getHitBox().y + o.getHitBox().height);
		// int leftCoord = (int)(o.getHitBox().y - hitBox.width);
		// int rightCoord = (int) (o.getHitBox().y + o.getHitBox().width);
		//
		// //xCheck and yCheck are used to see if the character is on the same
		// plane as the
		// boolean yCheck = (hitBox.y > botCoord && hitBox.y < topCoord);
		// boolean xCheck = (hitBox.x >leftCoord && hitBox.x < rightCoord);
		//
		//
		// //movement stopping logic
		// if(hitBox.x + width < o.getHitBox().x && yCheck){
		// rightBound=false;
		// }
		// else if(hitBox.x > o.getHitBox().x + o.getHitBox().width && yCheck){
		// leftBound=false;
		// }
		// if(o.getHitBox().y - o.getHitBox().height > hitBox.y && xCheck){
		// topBound=false;
		// }
		// else if(o.getHitBox().y > hitBox.y - height && hitBox.y >
		// o.getHitBox().y && xCheck){
		// bottomBound=false;
		// }
		// }
		// }

		// for(Obstacle o : npcs){
		// if(Intersector.overlaps(hitBox, o.hitBox)){
		// // What side of the obstacle am I on
		// boolean yCheck = (hitBox.y > o.position.getY()-height && hitBox.y <
		// o.position.getY() + height + o.height);
		// boolean xCheck = (hitBox.x >= o.position.getX()-width && hitBox.x <=
		// o.position.getX() + width + o.width);
		//
		// if(o.hitBox.x > hitBox.x && yCheck){
		// rightBound=false;
		// }
		// if(o.hitBox.x < hitBox.x+width && yCheck){
		// leftBound=false;
		// }
		// if(o.hitBox.y-o.hitBox.height+12> hitBox.y && xCheck){
		// topBound=false;
		// }
		// if(o.hitBox.y +12> hitBox.y-height && hitBox.y>o.hitBox.y&& xCheck){
		// bottomBound=false;
		// }
		//
		// }
		// }

		if (nottalking) {
			if (moveHoriz) {
				// move right
				if (xTouch > centerx
						&& !(xTouch - moveSpeed < centerx && xTouch + moveSpeed > centerx)
						&& rightBound) {
					position.setX(position.getX() + moveSpeed);
					direction = Directions.RIGHT;
				}

				// move left
				else if (xTouch < centerx
						&& !(xTouch - moveSpeed > centerx && xTouch + moveSpeed < centerx)
						&& leftBound) {
					position.setX(position.getX() - moveSpeed);
					direction = Directions.LEFT;
				} else
					moveHoriz = false;
			} else if (!moveHoriz) {
				// move up
				if (yTouch > centery
						&& !(yTouch - moveSpeed < centery && yTouch + moveSpeed > centery)
						&& topBound) {
					position.setY(position.getY() + moveSpeed);
					direction = Directions.UP;
				}
				// move down
				else if (yTouch < centery
						&& !(yTouch - moveSpeed > centery && yTouch + moveSpeed < centery)
						&& bottomBound) {
					position.setY(position.getY() - moveSpeed);
					direction = Directions.DOWN;
				} else
					moveHoriz = true;
			}
			hitBox.set(position.getX(), position.getY(), width, height);
			updateSpot(npcs);
		}

	}

	public void setTalkingTrue() {
		nottalking = false;
	}

	public void setTalkingFalse() {
		nottalking = true;
	}

	public boolean getTalking() {
		return !nottalking;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public void setX(int x) {
		position.setX(x);
	}

	public void setY(int y) {
		position.setY(y);
	}

	public void updateSpot(List<NPC> npcs) {
		for (Obstacle o : npcs) {
			switch (direction) {
			case UP:
				if(Intersector.overlaps(hitBox,o.getHitBox())) {
					position.setY(o.getYCoord()-height-1);
					moveHoriz=true;
				}
				break;
			case DOWN:
				if(Intersector.overlaps(hitBox,o.getHitBox())) {
					position.setY(o.getYCoord()+o.height+1);
					moveHoriz=true;
				}
				break;
			case RIGHT:
				if(Intersector.overlaps(hitBox,o.getHitBox())) {
					position.setX(o.getXCoord()-width);
					moveHoriz=false;
				}
				break;

			case LEFT:
				if(Intersector.overlaps(hitBox,o.getHitBox())) {
					position.setX(o.getXCoord()+o.width);
					moveHoriz=false;

				}
				break;
			}
		}
	}

//	}
	// @Override
	// public Rectangle getHitBox() {
	// return hitBox;
	// }

}
