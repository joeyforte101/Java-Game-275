package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class ImmovableObstacle extends Obstacle{
//	protected int xCoord, yCoord;
//	protected int xScale, yScale;
//	SpriteBatch batch = new SpriteBatch();
//	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
//	protected Texture image;
//  has .draw();
	public ImmovableObstacle(int xLoc, int yLoc,int width,int height,String textureName){
		xCoord = xLoc;
		yCoord = yLoc;
		image = new Texture(textureName);
		xScale = width;
		yScale = height;
		int rectScaler = 4;
		boundingRectangle = new Rectangle(xCoord-rectScaler,yCoord-rectScaler,xScale+2*rectScaler,yScale+2*rectScaler);
	}

	public void draw() {
		batch.begin();
		batch.draw(image, xCoord, yCoord, xScale, yScale);
		batch.end();
//		ShapeRenderer shapeRenderer = new ShapeRenderer();
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
//		shapeRenderer.rect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width,boundingRectangle.height);
//		shapeRenderer.end();
	}

}
