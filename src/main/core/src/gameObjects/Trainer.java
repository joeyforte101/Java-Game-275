package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Trainer extends Obstacle {
	
	//placeholder for the question class or w/e
	String question = "";
	
	public Trainer (String t, int xpos, int ypos) {
		x = xpos;
		y = ypos;
		texture = new Texture(t);
		width = texture.getWidth();
		height = texture.getHeight();
		hitBox = new Rectangle(x, y, width, height);
		int rectScaler = 7;
		int stoppingScaler = 4;
		intersectingRectangle = new Rectangle(x-rectScaler,y-rectScaler,width+2*rectScaler,height+2*rectScaler);
		stoppingRectangle = new Rectangle(x-stoppingScaler,y-stoppingScaler,width+2*stoppingScaler,height+2*stoppingScaler);
	}
}
