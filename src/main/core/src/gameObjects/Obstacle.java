package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Obstacle extends Entity {
	protected int xCoord, yCoord;
	protected int xScale, yScale;
	SpriteBatch batch = new SpriteBatch();
	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
//	protected Texture image;
//	protected Rectangle boundingRectangle;
	
	public void draw() {
		batch.begin();
		batch.setColor(Color.BLUE);
		batch.draw(texture, xCoord, yCoord, xScale, yScale);
		batch.end();
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
