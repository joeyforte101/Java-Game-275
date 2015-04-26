package gameObjects.Entity;

import java.util.List;
import helperClasses.Directions;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;

public class UserCharacter extends Entity {
	public final int WIDTH = Gdx.graphics.getWidth();
	public final int HEIGHT = Gdx.graphics.getHeight();

	public int moveSpeed = 4;
	public Directions direction;
	public boolean talking = false;
	public boolean movingHorizontally = true;

	public UserCharacter() {
		super(0, 0, "playerBack.png");
	}

	public void setTalking(boolean t) {
		talking = t;
	}

	public void resetPositionAfterIntersection(List<Obstacle> obstacles) {
		for (Obstacle o : obstacles) {
			switch (direction) {
			case UP:
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
	}
}
