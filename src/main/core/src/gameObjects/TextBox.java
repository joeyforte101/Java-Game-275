package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class TextBox {
	
	private int width;
	private int height;
	private String text;
	public BitmapFont font;
	public SpriteBatch batch;
	
	public TextBox(int w, int h, String t){
		width = w;
		height = h;
		text = t;
		font = new BitmapFont();
		//font.setScale((float) 1.5, 5);
		batch = new SpriteBatch();
	}
	
	public int getwidth(){
		return width;
	}
	
	public int getheight(){
		return height;
	}
	
	public void displaytextbox(){
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		batch.begin();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), 100);
		shapeRenderer.end();
		font.draw(batch, text, Gdx.graphics.getWidth()/16, (float) (Gdx.graphics.getHeight()*.2));
		batch.end();
	}

}
