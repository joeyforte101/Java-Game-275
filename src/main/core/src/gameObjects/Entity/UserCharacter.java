package gameObjects.Entity;

import java.util.List;

import helperClasses.Directions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;

public class UserCharacter extends Entity {
	public final int WIDTH = Gdx.graphics.getWidth();
	public final int HEIGHT = Gdx.graphics.getHeight();

	public int moveSpeed = 4;
	public int animationCounter = 0;
	public Directions direction;
	public boolean talking = false;
	public boolean movingHorizontally = true;
	private Texture[] upTextures = { new Texture("Game character/up1.png"),
			new Texture("Game character/up2.png"),
			new Texture("Game character/up3.png") };
	private Texture[] rightTextures = {
			new Texture("Game character/right1.png"),
			new Texture("Game character/right2.png"),
			new Texture("Game character/right3.png") };
	private Texture[] leftTextures = { new Texture("Game character/left1.png"),
			new Texture("Game character/left2.png"),
			new Texture("Game character/left3.png") };
	private Texture[] downTextures = { new Texture("Game character/down1.png"),
			new Texture("Game character/down2.png"),
			new Texture("Game character/down3.png") };

	public UserCharacter() {
		super(0, 0, "Game character/up2.png");
	}

	public void setTalking(boolean t) {
		talking = t;
	}

	public void resetPositionAfterIntersection(List<Obstacle> obstacles) {
		if (animationCounter > 2)
			animationCounter = 0;
		for (Obstacle o : obstacles) {
			switch (direction) {
			case UP:
				texture = upTextures[animationCounter];
				if (Intersector.overlaps(hitBox, o.getHitBox())) {
					hitBox.y = o.getY() - getHeight() - 1;
					movingHorizontally = true;
				}
				break;
			case DOWN:
				if (Intersector.overlaps(hitBox, o.getHitBox())) {
					hitBox.y = o.getY() + o.getHeight() + 1;
					movingHorizontally = true;
				}
				break;
			case RIGHT:
				if (Intersector.overlaps(hitBox, o.getHitBox())) {
					hitBox.x = o.getX() - getWidth();
					movingHorizontally = false;
				}
				break;

			case LEFT:
				if (Intersector.overlaps(hitBox, o.getHitBox())) {
					hitBox.x = o.getX() + o.getWidth();
					movingHorizontally = false;
				}
				break;
			}
		}
		switch (direction) {
		case UP:
			texture = upTextures[animationCounter];
			break;
		case DOWN:
			texture = downTextures[animationCounter];
			break;
		case RIGHT:
			texture = rightTextures[animationCounter];
			break;
		case LEFT:
			texture = leftTextures[animationCounter];
			break;
		}
		animationCounter++;
	}
}
