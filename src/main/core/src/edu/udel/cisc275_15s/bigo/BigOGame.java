package edu.udel.cisc275_15s.bigo;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import gameObjects.AdvisementQuestion;
import gameObjects.DropAddQuestion;
import gameObjects.Entity;
//import gameObjects.ImmovableObstacle;
import gameObjects.Obstacle;
import gameObjects.Question;
import gameObjects.Trainer;
import gameObjects.UDSISQuestion;
import gameObjects.UserCharacter;
import gameObjects.QuestionFactory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class BigOGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int x=0,y=0;
	String debugString;
	UserCharacter mainGuy;
	BitmapFont font;
	boolean tapLock= false;
	Texture mapBackground;
	Trainer someGuy;
	ArrayList<Obstacle> obstacles;
	ArrayList<AdvisementQuestion> AdvisementQuestionList;
	ArrayList<DropAddQuestion> DropAddQuestionList;
	ArrayList<UDSISQuestion> UDSISQuestionList;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		mainGuy = new UserCharacter(0,0);
		debugString = "start";
		font = new BitmapFont();
		someGuy = new Trainer("trainer.png", 300, 300);
//		someGuy = new ImmovableObstacle(300, 300, 32, 32, "playerBack.png");
	    mapBackground = new Texture("background.png");
//		final int WIDTH = Gdx.graphics.getWidth();
//		final int HEIGHT = Gdx.graphics.getHeight();
		font.setColor(Color.BLACK);
		obstacles = new ArrayList<Obstacle>();
		obstacles.add(someGuy);
		buildquestionlists();
	}
	
	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(mapBackground, 0,0);
		//draws most recent mouse click coordinate to the screen for testing
		font.draw(batch,debugString,30,30);
		draw(mainGuy);
		draw(someGuy);
		batch.end();
		//Checks if screen is tapped in a different place so movement direction priority can be calculated
		if(Gdx.input.isTouched() && !tapLock){ 
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,obstacles);
			tapLock = true;
		}
		//moves as long as screen is tapped
		else if(Gdx.input.isTouched()){
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,obstacles);
		}
		debugString=Integer.toString((int)mainGuy.hitBox.x)+":"+Integer.toString((int)mainGuy.hitBox.y);
		if(!Gdx.input.isTouched())tapLock=false;
	}
	
	void draw(Entity e) {
//		ShapeRenderer shapeRenderer = new ShapeRenderer();
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
//		shapeRenderer.rect(e.hitBox.x, e.hitBox.y, e.hitBox.width, e.hitBox.height);
//		shapeRenderer.end();
		batch.draw(e.texture, e.x, e.y, e.width, e.height);
	}
	private void buildquestionlists()
	{
		  String fileName = "test.txt";
		  String line = null;
		  Question testquestion;
		  try{
		  FileReader fileReader = new FileReader(fileName);
		  BufferedReader bufferedReader = new BufferedReader(fileReader);
		  
		    while((line = bufferedReader.readLine()) != null) {
		        testquestion = QuestionFactory.getQuestion(line);
		        if(testquestion.type() == "UDSIS")
		        	UDSISQuestionList.add((UDSISQuestion) testquestion);
		        else if(testquestion.type() == "Drop Add")
		        	DropAddQuestionList.add((DropAddQuestion) testquestion);
		        else
		        	AdvisementQuestionList.add((AdvisementQuestion) testquestion);
		        
		    }
		  }
		  catch(FileNotFoundException ex) {
			    System.out.println(
			        "Unable to open file '" + 
			        fileName + "'");                
			}
			catch(IOException ex) {
			    System.out.println(
			        "Error reading file '" 
			        + fileName + "'");                   
			    
			}
		  //System.out.println(UDSISQuestionList.get(0));
		  //System.out.println(DropAddQuestionList.get(0));
		  //System.out.println(AdvisementQuestionList.get(0));
	}
}
