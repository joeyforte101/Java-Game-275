package edu.udel.cisc275_15s.bigo;

import java.util.ArrayList;

import gameObjects.ImmovableObstacle;
import gameObjects.Obstacle;
import gameObjects.UserCharacter;
import helperClasses.Mapping;

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
	Texture mapBackground;
	ImmovableObstacle someGuy;
	ArrayList<Obstacle> obstacles;
	@Override
	public void create () {
		batch = new SpriteBatch();
		mainGuy = new UserCharacter(0,0);
		coords = "start";
		font = new BitmapFont();
		someGuy = new ImmovableObstacle(300, 300, 32, 32, "playerBack.png");
	    mapBackground = new Texture("background.png");
		final int WIDTH = Gdx.graphics.getWidth();
		final int HEIGHT = Gdx.graphics.getHeight();
		font.setColor(Color.BLACK);
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(someGuy);
	}
	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mapBackground, 0,0);
		//draws most recent mouse click coordinate to the screen for testing
		font.draw(batch,coords,30,30);
		batch.end();
		
		mainGuy.draw();
		someGuy.draw();
		//Checks if screen is tapped in a different place so movement direction priority can be calculated
		if(Gdx.input.isTouched() && !tapLock){ 
			coords=Integer.toString((int)mainGuy.getRect().y)+":"+Integer.toString((int)someGuy.getHitBox().y);
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,obstacles);
			tapLock = true;
		}
		//moves as long as screen is tapped
		else if(Gdx.input.isTouched()){
			coords=Integer.toString((int)mainGuy.getRect().y)+":"+Integer.toString((int)someGuy.getHitBox().y);
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,obstacles);
		}
		if(!Gdx.input.isTouched())tapLock=false;
	}
}
