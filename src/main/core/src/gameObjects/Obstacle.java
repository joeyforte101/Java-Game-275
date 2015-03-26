package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Obstacle {
	protected int xCoord, yCoord;
	protected int xScale, yScale;
	SpriteBatch batch = new SpriteBatch();
	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
	protected Texture image;
	
	public void draw() {
		batch.begin();
		batch.setColor(Color.BLUE);
		batch.draw(image, xCoord, yCoord, xScale, yScale);
		batch.end();
	}

}
