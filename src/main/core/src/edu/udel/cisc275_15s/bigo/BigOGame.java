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
import gameObjects.Entity.Battle;
import gameObjects.Entity.Entity;
import gameObjects.Entity.InfoNPC;
import gameObjects.Entity.NPC;
import gameObjects.Entity.Obstacle;
import gameObjects.Entity.Position;
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
import com.badlogic.gdx.Input.TextInputListener;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/*
 * This creates an instance of the BigOGame
 * The game contains:
 * - A User Character
 * - A current screen
 * - *A current level 
 * 
 * The class renders:
 * - The current screen
 */
public class BigOGame extends ApplicationAdapter implements Screen {
	
	 private Stage stage = new Stage();
	 private Table table = new Table();
	 TextInputListener listener; 
	 private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
	  new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
	  private TextButton NotesButton = (TextButton) new TextButton("Notes",skin).align(Align.topRight);
	
	
	
	int startCount = 0;
	SpriteBatch batch;
	Texture img;
	int x=0,y=0;
	int WIDTH = 0;
	int HEIGHT = 0;
	String debugString;
	UserCharacter player;
	boolean tapLock= false;
	boolean leftDoor = true;
	MainClass mainClass;
	Room currentRoom;
	Screen currentScreen;
	boolean inBattle;
	Battle battle;
	
	
	InfoNPC infoGuy;
	Trainer trainer1;
	Room roomOne;
	Room roomTwo;
	ArrayList<NPC> temp;
	
	/*
	public BigOGame(){
		create();
		render();
	}
	*/
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		player = new UserCharacter(new Position(0,0));
		debugString = "start";
		
		//trainer1 = new Trainer(new Position(300, 300), "trainer.png", "What is the Drop/Add Deadline?", "Hello" ,new String[]{"1 week", "2 weeks", "3 weeks", "4 weeks", "2 weeks"});
		//infoGuy = new InfoNPC(new Position(400, 400), "trainer.png", "Hey Did You know the DeadLine for Drop/Add is 2 Weeks?");
		temp = NPC.generateNPCs(new String[]{"sprite1.pks","sprite2.pks","sprite 3.pks"});
		//temp.add(trainer1);
		//temp.add(infoGuy);
		roomOne = new Room("World map.png", temp);
		roomTwo = new Room("background2.png", new ArrayList<NPC>());
		currentRoom = roomOne;
		
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		
		

	}
	
	@Override
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        checkForBattle();
        if(inBattle){
        	battle.draw();
        }
        else{
		batch.begin();
		//batch.draw(currentScreen);
		batch.draw(currentRoom.background, 0, 0);
		
		for (NPC npc : currentRoom.npcs)
		{
			draw(npc);
		}		
		
		draw(player);
		
		batch.end();
		
		//Player movement needs to be handled by a controller in a separate method/class
		
		//Checks if screen is tapped in a different place so movement direction priority can be calculated
		if(Gdx.input.isTouched() && !tapLock){ 
			player.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,currentRoom.npcs);
			tapLock = true;
		}
		
		//moves as long as screen is tapped
		else if(Gdx.input.isTouched()){
			player.move(Gdx.input.getX(),Gdx.input.getY(),tapLock,currentRoom.npcs);
		}
		
		// this is a bad place but its a rapid prototype
		if (player.getX() > 100 && player.getX() < WIDTH - 45 && player.getY() > 20 && player.getY() < HEIGHT - 45) {
			leftDoor = true;
		}
		if (player.getY() > 380 && player.getY() < 420) {
			if (player.getX() < 100 && leftDoor) {
				currentRoom = roomTwo;
				leftDoor = false;
				player.setX(WIDTH - 40);
			} else if (player.getX() > WIDTH - 45 && leftDoor) {
				currentRoom = roomOne;
				leftDoor = false;	
				player.setX(0);		
			}	
		}
		debugString=Integer.toString((int)player.hitBox.x)+":"+Integer.toString((int)player.hitBox.y) +" "+Integer.toString(WIDTH)+"/"+Integer.toString(HEIGHT);
		if(!Gdx.input.isTouched())tapLock=false;
		
		  stage.act();
	      stage.draw();
        }
	}
	
	void draw(Entity e) {
//		ShapeRenderer shapeRenderer = new ShapeRenderer();
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(Color.RED);
//		shapeRenderer.rect(e.hitBox.x, e.hitBox.y, e.hitBox.width, e.hitBox.height);
//		shapeRenderer.end();
		batch.draw(e.texture, e.position.getX(), e.position.getY(), e.width, e.height);
	}
	
	
	
	private boolean checkForBattle()
	{
		for(NPC npc : currentRoom.npcs)
		{
			if(npc instanceof Trainer && ((Trainer)npc).playerInRange(player)){
				inBattle = true;
				battle = new Battle(npc);
			}
		}
			
		return inBattle;
	}
		
	@Override
	public void show() {
		if (startCount == 0){
			create();
			startCount++;
		}
		
		NotesButton.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 ((Game)Gdx.app.getApplicationListener()).setScreen(new Notes());
				
			 }
		});
		
		   table.add(NotesButton).size(250,50);
		   table.align(Align.bottomRight);
		   table.setFillParent(true);
	       stage.addActor(table);

	        Gdx.input.setInputProcessor(stage);
		
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}