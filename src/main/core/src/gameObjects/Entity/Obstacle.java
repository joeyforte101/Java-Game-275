package gameObjects.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Obstacle extends Entity{
	
	SpriteBatch batch = new SpriteBatch();
	public final int WIDTH = Gdx.graphics.getWidth();
	public final int HEIGHT = Gdx.graphics.getHeight();
	
	protected Rectangle intersectingRectangle;
	protected Rectangle boundingRectangle;
	protected Rectangle stoppingRectangle;
	
	public Obstacle(Position position, String sprite){
		
		this.position = position;
		texture = new Texture(sprite);
		width = texture.getWidth();
		height = texture.getHeight();
		int rectScaler = 7;
		int stoppingScaler = 4;
		intersectingRectangle = new Rectangle(position.getX()-rectScaler, position.getY()-rectScaler, width+2*rectScaler, height+2*rectScaler);
		stoppingRectangle = new Rectangle(position.getX()-stoppingScaler, position.getY()-stoppingScaler, width+2*stoppingScaler, height+2*stoppingScaler);
		hitBox = new Rectangle(this.position.getX(), this.position.getY(), width, height);
	}
	
	public void draw() {
		batch.begin();
		batch.setColor(Color.BLUE);
		batch.draw(texture, position.getX(), position.getY(), width, height);
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
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getXCoord(){
		return position.getX();
	}
	
	public int getYCoord(){
		return position.getY();
	}
	
}
