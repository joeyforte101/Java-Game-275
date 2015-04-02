package gameObjects.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/*
 * 	An Entity has a texture, a location, and a hitbox 
 */
public abstract class Entity {
	public int x, y, width, height;
	public Rectangle hitBox;
	public Texture texture;
}
