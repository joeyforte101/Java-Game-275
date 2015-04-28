package gameObjects.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/*
 * 	An Entity has a texture and a hitbox/location
 */
public abstract class Entity {
	
	public Rectangle hitBox;
	public Texture texture;
	
	public Entity(int x, int y, int w, int h) {
		hitBox = new Rectangle(x, y, w, h);		
	}
	
	public Entity (int x, int y, String sprite) {
		texture = new Texture(sprite);
		hitBox = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
	}

	public int getX() {
		return (int) hitBox.x;
	}

	public int getY() {
		return (int) hitBox.y;
	}

	public int getHeight() {
		return (int) hitBox.height;
	}
	
	public int getWidth() {
		return (int) hitBox.width;
	}
	
	public void setX(int x) {
		hitBox.x = x;
	}

	public void setY(int y) {
		hitBox.y = y;
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
}