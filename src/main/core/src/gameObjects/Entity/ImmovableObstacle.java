package gameObjects.Entity;
//imovable

import com.badlogic.gdx.math.Rectangle;

public class ImmovableObstacle extends Obstacle{
//	protected int x, y;
//	protected int width, height;
//	SpriteBatch batch = new SpriteBatch();
//	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
//	protected Texture image;
//  has .draw();
	public ImmovableObstacle(Position position,int width,int height,String sprite){

		super(position, sprite);
		boundingRectangle = new Rectangle (position.getX(), position.getY(), width, height);
		
	}

	public void draw() {
		
		batch.begin();
		batch.draw(texture, position.getX(), position.getY(), width, height);
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
