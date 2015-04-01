package gameObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class InfoNPC extends Obstacle {
private String info;
private boolean talking = false;
private boolean justrecentlytalked = false;
public InfoNPC (String t, int xpos, int ypos, String text) {
	x = xpos;
	y = ypos;
	info = text;
	texture = new Texture(t);
	width = texture.getWidth();
	height = texture.getHeight();
	hitBox = new Rectangle(x, y, width, height);
	int rectScaler = 7;
	int stoppingScaler = 4;
	intersectingRectangle = new Rectangle(x-rectScaler,y-rectScaler,width+2*rectScaler,height+2*rectScaler);
	stoppingRectangle = new Rectangle(x-stoppingScaler,y-stoppingScaler,width+2*stoppingScaler,height+2*stoppingScaler);
}
	
	public String getinfo(){
		return info;
	}

	public void displayprompttrue()
	{
		talking = true;
	}
	public void displaypromptfalse()
	{
		talking = false;
	}
	public boolean getprompt(){
		return talking;
	}
	public boolean getjusttalked(){
		return justrecentlytalked;
	}
	public void setjusttalkedtrue(){
		justrecentlytalked = true;
	}
	public void setjusttalkedfalse(){
		justrecentlytalked = false;
	}
}
