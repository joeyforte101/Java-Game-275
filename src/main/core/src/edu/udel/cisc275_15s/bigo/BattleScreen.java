package edu.udel.cisc275_15s.bigo;

import gameObjects.Entity.Battle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
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
    private Table ansTable = new Table();
    
    TextInputListener listener; 
    
    private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
    	      new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
    
    // Button that allows you to run from battles
    private TextButton RunButton = (TextButton) new TextButton("Run",skin).align(Align.bottomRight);
    
    // Button for selecting answer A
    private TextButton ansA = (TextButton) new TextButton("A",skin).align(Align.topLeft);
    // Button for selecting answer B
    private TextButton ansB = (TextButton) new TextButton("B",skin).align(Align.topRight);
    // Button for selecting answer C
    private TextButton ansC = (TextButton) new TextButton("C",skin).align(Align.bottomLeft);
    // Button for selecting answer D
    private TextButton ansD = (TextButton) new TextButton("D",skin).align(Align.bottomLeft);
   
    
    // Battle that the screen is modeling
	private Battle battle;
	private SpriteBatch batch;
	
	public BattleScreen(Battle battle){
		this.battle = battle;
	}

	@Override
	public void show() {
		ansA.setName("Sol");
		
		RunButton.getLabel().setFontScale((float) 0.8);
		batch = new SpriteBatch();
		RunButton.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 ((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.Game);
				
			 }
		});
		ansA.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 battle.answerGiven = battle.questions[battle.questionIndex].getanswer1();
				 if(!battle.giveAnswer(battle.answerGiven)){
					 ansA.setColor(Color.RED);;
				 }
			 }
		});
		ansB.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 battle.answerGiven = battle.questions[battle.questionIndex].getanswer2();
				 if(!battle.giveAnswer(battle.answerGiven)){
					 ansB.setColor(Color.RED);
				 }
			 }
		});
		ansC.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 battle.answerGiven = battle.questions[battle.questionIndex].getanswer3();
				 if(!battle.giveAnswer(battle.answerGiven)){
					 ansC.setColor(Color.RED);
				 }
			 }
		});
		ansD.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 battle.answerGiven = battle.questions[battle.questionIndex].getanswer4();
				 if(!battle.giveAnswer(battle.answerGiven)){
					 ansD.setColor(Color.RED);
				 }
			 }
		});
		
		 
	
		
		
			ansTable.add(ansA).size(100,50);
			ansTable.add(ansB).size(100,50);
			ansTable.add(ansC).size(100,50);
			ansTable.add(ansD).size(100,50);
			ansTable.align(Align.center);
			ansTable.setFillParent(true);
		    stage.addActor(ansTable);
		    
		   table.add(RunButton).size(75,200);
		   table.align(Align.bottomRight);
		   table.setFillParent(true);
	       stage.addActor(table);

	        Gdx.input.setInputProcessor(stage);
		

	}

	@Override
	public void render(float delta) {
		
		if(battle.battleOver()){
			((Game)Gdx.app.getApplicationListener()).setScreen(MainClass.Game);
		}
		
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
