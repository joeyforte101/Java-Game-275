package edu.udel.cisc275_15s.bigo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import gameObjects.Door;
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
import gameObjects.Entity.YesNoNPC;
import gameObjects.Question.AdvisementQuestion;
import gameObjects.Question.DropAddQuestion;
import gameObjects.Question.Question;
import gameObjects.Question.QuestionFactory;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This creates an instance of the BigOGame The game contains: - A User
 * Character - A current screen - *A current level
 * 
 * The class renders: - The current screen
 */
public class BigOGame extends ApplicationAdapter implements Screen {

	private Stage stage = new Stage();
	private Stage YNstage = new Stage();
	private Stage talkStage = new Stage();
	private Stage exitStage = new Stage();
	private Table table = new Table();
	private Table YNTable = new Table();
	private Table talkTable = new Table();
	private Table exitTable = new Table();
	

	TextInputListener listener;
	private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
			new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
	private TextButton NotesButton = (TextButton) new TextButton("Notes", skin)
			.align(Align.bottomRight);
	private TextButton YesButton = (TextButton) new TextButton("Yes", skin)
			.align(Align.topLeft);
	private TextButton NoButton = (TextButton) new TextButton("No", skin)
			.align(Align.topRight);
	private TextButton ExitButton = (TextButton) new TextButton("X", skin)
	.align(Align.topRight);
	private TextButton TalkButton = (TextButton) new TextButton("Talk", skin)
	.align(Align.topRight);
	InputMultiplexer inputMultiplexer = new InputMultiplexer();

	int startCount = 0;
	SpriteBatch batch;
	Texture img;
	int x = 0, y = 0;
	int WIDTH = 0;
	int HEIGHT = 0;
	String debugString;
	UserCharacter player;
	boolean tapLock = false;
	boolean leftDoor = true;
	MainClass mainClass;
	Screen currentScreen;
	boolean inBattle;
	boolean talking = false;
	Battle battle;
	
	ArrayList<Room> rooms;
	Room roomOne;
	Room roomTwo;
	Room currentRoom;
	
	NPC focusNPC;

	@Override
	public void create() {
		batch = new SpriteBatch();

		player = new UserCharacter();
		
//		rooms = ContentLoader.load();
//		currentRoom = rooms.get(0);
		roomOne = new Room("room_backgrounds\\overworld_background.png", NPC.generateNPCs(new String[] {"sprite1.pks", "sprite2.pks", "sprite 3.pks" }));
		roomOne.hash = "roomOne";
		roomTwo = new Room("room_backgrounds\\room_two_background.png");
		roomTwo.hash = "roomTwo";
		roomOne.addDoor("roomTwo", new int[] { 62, 320, 36, 40 }, new int[] {	405, 380 });
		// house one
		roomOne.addObstacle(new Obstacle(32,320,30,112));
		roomOne.addObstacle(new Obstacle(62,360,36,72));
		roomOne.addObstacle(new Obstacle(98,320,62,112));
		// house two
		roomOne.addObstacle(new Obstacle(32,129,128,112));
		// house three
		roomOne.addObstacle(new Obstacle(480,129,128,112));
		// house four
		roomOne.addObstacle(new Obstacle(480,320,128,112));
		// big building
		roomOne.addObstacle(new Obstacle(255,64,128,160));
		//roomTwo.addDoor(roomOne, new int[] { 400, 380, 60, 60 }, new int[] { 50, 300 });
		roomTwo.addDoor("roomOne", new int[] { 0, 0, 60, 60 }, new int[] { 50, 300 });
		currentRoom = roomOne;

		rooms = new ArrayList<Room>();
		rooms.add(roomOne);
		rooms.add(roomTwo);

		debugString = "start";

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
	}

	public void update() {
		//checkForBattle();

		if (Gdx.input.isTouched() && !tapLock) {
			changeRoom(currentRoom.move(player, tapLock, Gdx.input.getX(),
					Gdx.input.getY()));
			tapLock = true;
		} else if (Gdx.input.isTouched()) {
			changeRoom(currentRoom.move(player, tapLock, Gdx.input.getX(),
					Gdx.input.getY()));
		} else {
			tapLock = false;
		}

		debugString = Integer.toString((int) player.hitBox.x) + ":"
				+ Integer.toString((int) player.hitBox.y) + " "
				+ Integer.toString(WIDTH) + "/" + Integer.toString(HEIGHT);
	}

	@Override
	public void render(float deltaTime) {
		player.setDeltaTime(deltaTime);
		TalkButton.setColor(Color.RED);
		
		if(focusNPC == null){
		update();
		}else if(!focusNPC.isTalking()){
			update();
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(currentRoom.background, 0, 0);

		for (NPC npc : currentRoom.npcs) {
			draw(npc);
		}
		
		draw(player);

		batch.end();
		
		for (NPC npc : currentRoom.npcs) {
			if (npc.playerInRange(player)) {
				
				focusNPC = npc;
				TalkButton.setColor(Color.GREEN);
				
				if(!npc.isTalking()){
					talkStage.act();
					talkStage.draw();
					
				}
				
				
				if (npc instanceof YesNoNPC && npc.isTalking()) {
					batch.begin();
					npc.drawText(batch);
					batch.end();
					YNstage.act();
					YNstage.draw();
					exitStage.act();
					exitStage.draw();	
				} 
				else if(npc.isTalking()) {
					batch.begin();
					npc.drawText(batch);
					batch.end();
					exitStage.draw();
					exitStage.act();
				}
			}
			if (npc instanceof Trainer && npc.playerInRange(player)
					&& ((Trainer) npc).hasBattled() == false) {
				((Trainer) npc).setHasBattled(true);
				inBattle = true;
				battle = new Battle(player, npc);
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new BattleScreen(battle));
			}
		}
		

//		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
//		for(Obstacle obs : currentRoom.getObstacles()) {
//			drawHitbox(obs);
//		}
//		
//		for(Door d : currentRoom.doors) {
//			drawDoor(d);
//		}
		stage.act();
		stage.draw();
	}

	void draw(Entity e) {		
		batch.draw(e.texture, e.getX(), e.getY(), e.getWidth(),
				e.getHeight());
	}
	
	void drawHitbox(Entity e) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(1, 0, 0, .4f));
		shapeRenderer.rect(e.getX(), e.getY(), e.getWidth(), e.getHeight());
		shapeRenderer.end();
	}
	
	void drawDoor(Door d) {
		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(new Color(0, 0, 1, .4f));
		shapeRenderer.rect(d.hitbox.x, d.hitbox.y, d.hitbox.width, d.hitbox.height);
		shapeRenderer.end();
	}

	private boolean checkForBattle() {
		for (NPC npc : currentRoom.npcs) {
			if (npc instanceof Trainer && ((Trainer) npc).playerInRange(player)) {
				inBattle = true;
				battle = new Battle(player, npc);
			}
		}

		return inBattle;
	}
	
	@Override
	public void show() {
		if (startCount == 0) {
			create();
			startCount++;
		}
		// NotesButton.setColor(Color.RED);
		
		TalkButton.getLabel().setFontScale((float) 0.8);
		TalkButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if(focusNPC != null){
					focusNPC.setTalking(true);
					Notes.addnote(focusNPC.getNotes());
				}
			}
		});
		
		ExitButton.getLabel().setFontScale((float) 0.8);
		ExitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				focusNPC.setTalking(false);
				if(focusNPC instanceof YesNoNPC)
					((YesNoNPC) focusNPC).setUnderstood(0);
			}
		});

		YesButton.getLabel().setFontScale((float) 0.8);
		YesButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if(focusNPC instanceof YesNoNPC)
					((YesNoNPC) focusNPC).setUnderstood(1);
			}
		});

		NoButton.getLabel().setFontScale((float) 0.8);
		NoButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if(focusNPC instanceof YesNoNPC)
					((YesNoNPC) focusNPC).setUnderstood(2);
			}
		});

		NotesButton.getLabel().setFontScale((float) 0.8);
		NotesButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new Notes());

			}
		});

		table.add(NotesButton).size(WIDTH / 8, HEIGHT / 7);
		table.align(Align.bottomRight);
		table.setFillParent(true);
		stage.addActor(table);

		YNTable.add(NoButton).size(100, 50);
		YNTable.add(YesButton).size(100, 50);
		YNTable.align(Align.bottom);
		YNTable.setFillParent(true);
		YNstage.addActor(YNTable);

		table.add(TalkButton).size(100, 50);
		talkTable.align(Align.bottomLeft);
		talkTable.setFillParent(true);
		talkStage.addActor(talkTable);
		
		exitTable.add(ExitButton).size(50, 50);
		exitTable.align(Align.topRight);
		exitTable.setFillParent(true);
		exitStage.addActor(exitTable);

		InputProcessor inputProcessorOne = stage;
		InputProcessor inputProcessorTwo = YNstage;
		InputProcessor inputProcessor3 = talkStage;
		InputProcessor inputProcessor4 = exitStage;

		// Gdx.input.setInputProcessor(stage);
		// Gdx.input.setInputProcessor(YNstage);

		inputMultiplexer.addProcessor(inputProcessorOne);
		inputMultiplexer.addProcessor(inputProcessorTwo);
		inputMultiplexer.addProcessor(inputProcessor3);
		inputMultiplexer.addProcessor(inputProcessor4);
		
		Gdx.input.setInputProcessor(inputMultiplexer);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}
	
	void changeRoom(String hash) {
		for (Room room : rooms) {
			if (room.hash == hash)
				currentRoom = room;
		}
	}
}