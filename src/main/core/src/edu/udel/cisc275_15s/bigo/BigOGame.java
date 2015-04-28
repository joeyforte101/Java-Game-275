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
	private Table table = new Table();
	private Table YNTable = new Table();
	TextInputListener listener;
	private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
			new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
	private TextButton NotesButton = (TextButton) new TextButton("Notes", skin)
			.align(Align.bottomRight);
	private TextButton YesButton = (TextButton) new TextButton("Yes", skin)
			.align(Align.topLeft);
	private TextButton NoButton = (TextButton) new TextButton("No", skin)
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
	Room currentRoom;
	Screen currentScreen;
	boolean inBattle;
	Battle battle;
	Room roomOne;
	Room roomTwo;
	ArrayList<NPC> temp;
	NPC focusNPC;

	@Override
	public void create() {
		batch = new SpriteBatch();

		player = new UserCharacter();

		debugString = "start";

		roomOne = new Room("World map.png", NPC.generateNPCs(new String[] {"sprite1.pks", "sprite2.pks", "sprite 3.pks" }));
		roomTwo = new Room("insideHouse.png");
		roomOne.addDoor(roomTwo, new int[] { 62, 320, 36, 40 }, new int[] {	405, 380 });
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
		roomTwo.addDoor(roomOne, new int[] { 400, 380, 60, 60 }, new int[] { 50, 300 });

		currentRoom = roomOne;

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

	}

	public void update() {
		//checkForBattle();

		if (Gdx.input.isTouched() && !tapLock) {
			currentRoom = currentRoom.move(player, tapLock, Gdx.input.getX(),
					Gdx.input.getY());
			tapLock = true;
		} else if (Gdx.input.isTouched()) {
			// moves as long as screen is tapped
			currentRoom = currentRoom.move(player, tapLock, Gdx.input.getX(),
					Gdx.input.getY());
		} else {
			tapLock = false;
		}

		debugString = Integer.toString((int) player.hitBox.x) + ":"
				+ Integer.toString((int) player.hitBox.y) + " "
				+ Integer.toString(WIDTH) + "/" + Integer.toString(HEIGHT);
	}

	@Override
	public void render(float deltaTime) {

		update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(currentRoom.background, 0, 0);

		for (NPC npc : currentRoom.npcs) {
			draw(npc);
		}

		draw(player);

		for (NPC npc : currentRoom.npcs) {
			if (npc.playerInRange(player)) {
				focusNPC = npc;
				npc.drawText(batch);
				if (npc instanceof YesNoNPC) {

					YNstage.draw();

				} else {
					npc.drawText(batch);
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

		batch.end();

//		Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
//		for(Obstacle obs : currentRoom.getObstacles()) {
//			drawHitbox(obs);
//		}
//		
//		for(Door d : currentRoom.doors) {
//			drawDoor(d);
//		}
		
		YNstage.act();

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

		YesButton.getLabel().setFontScale((float) 0.8);
		YesButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				batch.begin();
				((YesNoNPC) focusNPC).drawText(batch, false);
				System.out.println("YES");
				batch.end();
			}
		});

		NoButton.getLabel().setFontScale((float) 0.8);
		NoButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				batch.begin();
				((YesNoNPC) focusNPC).drawText(batch, false);
				System.out.println("NO");
				batch.end();
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

		InputProcessor inputProcessorOne = stage;
		InputProcessor inputProcessorTwo = YNstage;

		// Gdx.input.setInputProcessor(stage);
		// Gdx.input.setInputProcessor(YNstage);

		inputMultiplexer.addProcessor(inputProcessorOne);
		inputMultiplexer.addProcessor(inputProcessorTwo);
		Gdx.input.setInputProcessor(inputMultiplexer);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}