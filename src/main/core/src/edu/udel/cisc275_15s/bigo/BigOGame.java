package edu.udel.cisc275_15s.bigo;

import gameObjects.Door;
import gameObjects.Room;
import gameObjects.Entity.Battle;
import gameObjects.Entity.Entity;
import gameObjects.Entity.InfoNPC;
import gameObjects.Entity.NPC;
import gameObjects.Entity.Trainer;
import gameObjects.Entity.UserCharacter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
	private TextButton NotesButton = (TextButton) new TextButton("Notes", skin)
			.align(Align.topRight);

	int startCount = 0;
	SpriteBatch batch;
	Texture img;
	int x = 0, y = 0;
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
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		
		player = new UserCharacter();
		
		debugString = "start";
		
		roomOne = new Room("World map.png", NPC.generateNPCs(new String[]{"sprite1.pks","sprite2.pks","sprite 3.pks"}));
		roomTwo = new Room("insideHouse.png");
		roomOne.addDoor(roomTwo, new int[]{50,300,60,60}, new int[]{405, 380});
		roomTwo.addDoor(roomOne, new int[]{400, 380, 60, 60}, new int[]{50,300});

		currentRoom = roomOne;		
		
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}	
	
	public void Update() {
        checkForBattle();
        
        if(Gdx.input.isTouched() && !tapLock) { 
			currentRoom = currentRoom.move(player, tapLock, Gdx.input.getX(), Gdx.input.getY());
			tapLock = true;
		}		
		else if(Gdx.input.isTouched()) {
			//moves as long as screen is tapped
			currentRoom = currentRoom.move(player, tapLock, Gdx.input.getX(), Gdx.input.getY());
		}
		else {
			tapLock = false;
		}
		
        debugString = Integer.toString((int)player.hitBox.x)+":"+Integer.toString((int)player.hitBox.y) +" "+Integer.toString(WIDTH)+"/"+Integer.toString(HEIGHT);	
	}
	
	@Override
	public void render (float deltaTime) {
		
		Update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (inBattle) {
			battle.draw();
		} else {
			batch.begin();
			// batch.draw(currentScreen);
			batch.draw(currentRoom.background, 0, 0);

			for (NPC npc : currentRoom.npcs) {
				draw(npc);
			}
			
			draw(player);

			batch.end();
			
			for (Door d : currentRoom.doors) {
				drawDebug(d.hitbox);
			}

//			batch.end();

			stage.act();
			stage.draw();
		}
	}
	
	void draw(Entity e) {
		batch.draw(e.texture, e.getX(), e.getY(), e.getWidth(), e.getHeight());
	}
	
	void drawDebug(Rectangle r) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		shapeRenderer.rect(r.x, r.y, r.width, r.height);
		shapeRenderer.end();
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
		//NotesButton.setColor(Color.RED);
		NotesButton.getLabel().setFontScale((float) 0.8);
		NotesButton.addListener(new ClickListener(){
			 public void clicked(InputEvent event, float x, float y) {
				 ((Game)Gdx.app.getApplicationListener()).setScreen(new Notes());
				
			 }
		});
		
		   table.add(NotesButton).size(WIDTH/8,HEIGHT/7);
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