package gameObjects;

import com.badlogic.gdx.math.Rectangle;

import gameObjects.Entity.UserCharacter;

public class Door {

	public Room roomTo;
	public Rectangle hitbox;
	public Rectangle outPosition;
	public boolean out = false;

	public Door(Room room, Rectangle pos, Rectangle out) {
		roomTo = room;
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
