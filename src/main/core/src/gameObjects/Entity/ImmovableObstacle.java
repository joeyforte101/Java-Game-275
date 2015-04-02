package gameObjects.Entity;
//imovable
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class ImmovableObstacle extends Obstacle{
//	protected int x, y;
//	protected int width, height;
//	SpriteBatch batch = new SpriteBatch();
//	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
//	protected Texture image;
//  has .draw();
	public ImmovableObstacle(int xLoc, int yLoc,int width,int height,String textureName){
		x = xLoc;
		y = yLoc;
		image = new Texture(textureName);
		super.width = width;
		super.width = height;
		int rectScaler = 7;
		int stoppingScaler = 4;
		intersectingRectangle = new Rectangle(x-rectScaler,y-rectScaler,width+2*rectScaler,height+2*rectScaler);
		stoppingRectangle = new Rectangle(x-stoppingScaler,y-stoppingScaler,width+2*stoppingScaler,height+2*stoppingScaler);
		boundingRectangle = new Rectangle (x,y,width,height);
	}

	public void draw() {
		batch.begin();
		batch.draw(image, x, y, width, height);
		batch.end();
//		ShapeRenderer shapeRenderer = new ShapeRenderer();
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
//		shapeRenderer.rect(intersectingRectangle.x, intersectingRectangle.y, intersectingRectangle.width,intersectingRectangle.height);
//		shapeRenderer.setColor(Color.BLUE);
//		shapeRenderer.rect(stoppingRectangle.x, stoppingRectangle.y, stoppingRectangle.width,stoppingRectangle.height);
//		shapeRenderer.setColor(Color.GREEN);
//		shapeRenderer.rect(boundingRectangle.x, boundingRectangle.y, boundingRectangle.width,boundingRectangle.height);
//		shapeRenderer.end();
	}

}
