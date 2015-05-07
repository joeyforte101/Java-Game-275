package gameObjects;

import com.badlogic.gdx.math.Rectangle;

import gameObjects.Entity.UserCharacter;

public class Door {

	public String roomToHash;
	public Rectangle hitbox;
	public Rectangle outPosition;
	public boolean out = false;

	public Door(String hash, Rectangle pos, Rectangle out) {
		roomToHash = hash;
		hitbox = pos;
		outPosition = out;
	}

	public boolean inMe(UserCharacter c) {
		return c.getX() >= hitbox.x
				&& c.getX() + c.getWidth() <= hitbox.x + hitbox.width
				&& c.getY() >= hitbox.y
				&& c.getY() + c.getHeight() <= hitbox.y + hitbox.height;
	}
}
