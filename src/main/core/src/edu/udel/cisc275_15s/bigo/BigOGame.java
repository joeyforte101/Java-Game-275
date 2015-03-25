package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BigOGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int x=0,y=0;
	String coords;
	UserCharacter mainGuy;
	private BitmapFont font;
	boolean tapLock= false;
	@Override
	public void create () {
		batch = new SpriteBatch();
		mainGuy = new UserCharacter(0,0);
		coords = "start";
		font = new BitmapFont();
		final int WIDTH = Gdx.graphics.getWidth();
		final int HEIGHT = Gdx.graphics.getHeight();
		font.setColor(Color.BLACK);
	}
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainGuy.draw();
		
		//draws most recent mouse click coordinate to the screen for testing
		batch.begin();
		font.draw(batch,coords,30,30);
		batch.end();
		//^testing logic ends here
		
		//Checks if screen is tapped in a different place so movement direction priority can be calculated
		if(Gdx.input.isTouched() && !tapLock){ 
			coords=Integer.toString(Gdx.input.getX())+":"+Integer.toString(Mapping.yScreenToText(Gdx.input.getY()));
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock);
			tapLock = true;
		}
		//moves as long as screen is tapped
		else if(Gdx.input.isTouched()){
			coords=Integer.toString(Gdx.input.getX())+":"+Integer.toString(Mapping.yScreenToText(Gdx.input.getY()));
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock);
		}
		if(!Gdx.input.isTouched())tapLock=false;
	}
}
