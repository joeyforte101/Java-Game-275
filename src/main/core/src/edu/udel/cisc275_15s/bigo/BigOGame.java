package edu.udel.cisc275_15s.bigo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import gameObjects.Room;
import gameObjects.TextBox;
import gameObjects.UDSISQuestion;
import gameObjects.Entity.Entity;
import gameObjects.Entity.InfoNPC;
import gameObjects.Entity.NPC;
import gameObjects.Entity.Obstacle;
import gameObjects.Entity.Trainer;
import gameObjects.Entity.UserCharacter;
import gameObjects.Question.AdvisementQuestion;
import gameObjects.Question.DropAddQuestion;
import gameObjects.Question.Question;
import gameObjects.Question.QuestionFactory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class BigOGame extends ApplicationAdapter implements Screen {
	int x = 0, y = 0, WIDTH = 0, HEIGHT = 0;
	SpriteBatch batch;
	BitmapFont font;
	String debugString;
	boolean tapLock = false;
	boolean leftDoor = true;
	
	MainClass mainClass;
	UserCharacter mainGuy;
	
	Room currentRoom;
	Room roomOne;
	Room roomTwo;
	
	TextBox currenttext = null;
	
	ArrayList<AdvisementQuestion> AdvisementQuestionList = new ArrayList<AdvisementQuestion>();
	ArrayList<DropAddQuestion> DropAddQuestionList = new ArrayList<DropAddQuestion>();
	ArrayList<UDSISQuestion> UDSISQuestionList = new ArrayList<UDSISQuestion>();
	
	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);		
		debugString = "start";
		
		mainGuy = new UserCharacter(0,0);
		roomOne = new Room("background.png", new LinkedList<NPC>());
		roomOne.npcs.add(new Trainer("trainer.png", 300, 300));
		roomOne.npcs.add(new InfoNPC("trainer.png",400, 400, "Hey Did You know the DeadLine for Drop/Add is 2 Weeks?"));
		
		roomTwo = new Room("background2.png", new LinkedList<NPC>());
		currentRoom = roomOne;
		
		buildquestionlists();
	}
	
	@Override
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
		batch.begin();
		batch.draw(currentRoom.background, 0,0);
		
		font.draw(batch,debugString,30,30);
		
		for (NPC guy : currentRoom.npcs)
		{
			draw(guy);
		}		
		draw(mainGuy);		
		
		batch.end();
		drawcurrenttexts();
		//Checks if screen is tapped in a different place so movement direction priority can be calculated
		if(Gdx.input.isTouched() && !tapLock){ 
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,currentRoom.npcs);
			tapLock = true;
		}
		//moves as long as screen is tapped
		else if(Gdx.input.isTouched()){
			mainGuy.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,currentRoom.npcs);
		}
		// this is a bad place but its a rapid prototype
		if (mainGuy.x > 100 && mainGuy.x < WIDTH - 45 && mainGuy.y > 20 && mainGuy.y < HEIGHT - 45) {
			leftDoor = true;
		}
		if (mainGuy.y > 380 && mainGuy.y < 420) {
			if (mainGuy.x < 100 && leftDoor) {
				currentRoom = roomTwo;
				leftDoor = false;
				mainGuy.x = WIDTH - 40;
			} else if (mainGuy.x > WIDTH - 45 && leftDoor) {
				currentRoom = roomOne;
				leftDoor = false;	
				mainGuy.x = 0;		
			}	
		}
		debugString=Integer.toString((int)mainGuy.hitBox.x)+":"+Integer.toString((int)mainGuy.hitBox.y) +" "+Integer.toString(WIDTH)+"/"+Integer.toString(HEIGHT);
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
		  int x;
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
		  System.out.println(UDSISQuestionList.get(0));
 		  System.out.println(DropAddQuestionList.get(0));
		  System.out.println(AdvisementQuestionList.get(0));
	}
	
	private void drawcurrenttexts()
	{
		for(Obstacle o : currentRoom.npcs)
		{
			
			if(o instanceof InfoNPC)
			{
				boolean inrange =	Math.abs(o.getWidth() - mainGuy.getx()) < 75 &&
						Math.abs(o.getHeight() - mainGuy.gety()) < 75;
				
				if(((InfoNPC) o).getjusttalked() && inrange)
				{
					mainGuy.settalkingfalse();
					((InfoNPC) o).displaypromptfalse();
				}
				else
					((InfoNPC) o).setjusttalkedfalse();
				
				if(((InfoNPC) o).getprompt() && !((InfoNPC) o).getjusttalked())
				{
				mainGuy.settalkingtrue();
				((InfoNPC) o).displaypromptfalse();
				currenttext = new TextBox(1,1,((InfoNPC)o).getinfo());
				}					
					
				 if(Gdx.input.justTouched() && !((InfoNPC) o).getjusttalked() && mainGuy.gettalking())
				{
						mainGuy.settalkingfalse();
						((InfoNPC) o).setjusttalkedtrue();
				}					
			}
		}
			if(mainGuy.gettalking() == true)
				currenttext.displaytextbox();
	}
	@Override
	public void show() {
		create();
		
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}