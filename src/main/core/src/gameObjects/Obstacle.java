package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Obstacle {
	protected int xCoord, yCoord;
	protected int xScale, yScale;
	SpriteBatch batch = new SpriteBatch();
	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
	protected Texture image;
	protected Rectangle boundingRectangle;
	
	public void draw() {
		batch.begin();
		batch.setColor(Color.BLUE);
		batch.draw(image, xCoord, yCoord, xScale, yScale);
		batch.end();
	}
	public Rectangle getHitBox(){
		return boundingRectangle;
	}
	public int getWidth(){
		return xScale;
	}
	public int getHeight(){
		return yScale;
	}
	public int getXCoord(){
		return xCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
}
