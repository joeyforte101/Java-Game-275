package gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextBox {
	int width;
	int height;
	String text;
	BitmapFont font;
	SpriteBatch batch;
public TextBox(int w, int h, String t){
	width = w;
	height = h;
	text = t;
	font = new BitmapFont();
	font.setScale((float) 1.5, 5);
	batch = new SpriteBatch();
}
public int getwidth(){
	return width;
}
public int getheight(){
	return height;
}
public void displaytextbox(){
	batch.begin();
	font.draw(batch, text, Gdx.graphics.getWidth()/16, (float) (Gdx.graphics.getHeight()*.2));
	batch.end();
}


}
