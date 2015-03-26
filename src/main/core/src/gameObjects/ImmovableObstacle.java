package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ImmovableObstacle extends Obstacle{
//	protected int xCoord, yCoord;
//	protected int xScale, yScale;
//	SpriteBatch batch = new SpriteBatch();
//	public final int WIDTH = Gdx.graphics.getWidth(),HEIGHT = Gdx.graphics.getHeight();
//	protected Texture image;
//  has .draw();
	
	public ImmovableObstacle(int x, int y,String textureName){
		xCoord=x;
		yCoord=y;
		image=new Texture(textureName);
	}
	public int getXCoord(){
		return xCoord;
	}
	public int getYCoord(){
		return yCoord;
	}
	
	
	
}
