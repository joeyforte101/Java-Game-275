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
	@Override
	public void create () {
		batch = new SpriteBatch();
		mainGuy = new UserCharacter(0,0);
		coords = "start";
		font = new BitmapFont();
		font.setColor(Color.BLACK);
	}
	//this works AGAIN
	//vinn has flower shapped nipples
	//grant test
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainGuy.draw();
		batch.begin();
		font.draw(batch,coords,30,30);
		batch.end();
		if(Gdx.input.isTouched()){
			coords=Integer.toString(Gdx.input.getX())+":"+Integer.toString(Gdx.input.getY());
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY());
		}
	}
}
