package edu.udel.cisc275_15s.bigo;

import gameObjects.Entity.Battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BattleScreen implements Screen, TextInputListener {
	
	private Stage stage = new Stage();
    private Table table = new Table();
    
    TextInputListener listener; 
    
    private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
    	      new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
    
    private TextButton RunButton = (TextButton) new TextButton("Run",skin).align(Align.topRight);
    
	private Battle battle;
	private SpriteBatch batch;
	
	public BattleScreen(Battle battle){
		this.battle = battle;
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		RunButton.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.Game);
				
			 }
		});
			
	
		
		
			
		   table.add(RunButton).size(50,50);
		   table.align(Align.bottomRight);
		   table.setFillParent(true);
	       stage.addActor(table);

	        Gdx.input.setInputProcessor(stage);
		

	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch = new SpriteBatch();
        //batch.begin();
        battle.draw();
        //batch.end();
        
        stage.act();
        stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
        skin.dispose();

	}

	@Override
	public void input(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}

}
