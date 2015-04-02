package gameObjects.Entity;
//obstacle
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Obstacle extends Entity{
	SpriteBatch batch = new SpriteBatch();
	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
	protected Texture image;
	protected Rectangle intersectingRectangle;
	protected Rectangle boundingRectangle;
	protected Rectangle stoppingRectangle;
	
	public void draw() {
		batch.begin();
		batch.setColor(Color.BLUE);
		batch.draw(image, x, y, width, height);
		batch.end();
	}
	//was using these for collision will probably remove later
	public Rectangle getProximityRect(){
		return intersectingRectangle;
	}
	public Rectangle getStoppingRect(){
		return stoppingRectangle;
	}
	public Rectangle getHitBox(){
		return hitBox;
	}
	public int getWidth(){
		return x;
	}
	public int getHeight(){
		return y;
	}
	public int getXCoord(){
		return width;
	}
	public int getYCoord(){
		return height;
	}
}
