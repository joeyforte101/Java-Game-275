package edu.udel.cisc275_15s.bigo;

import java.util.ArrayList;

import gameObjects.Door;
import gameObjects.Room;
import gameObjects.Entity.Battle;
import gameObjects.Entity.Entity;
import gameObjects.Entity.InfoNPC;
import gameObjects.Entity.NPC;
import gameObjects.Entity.Trainer;
import gameObjects.Entity.UserCharacter;
import gameObjects.Entity.YesNoNPC;
import gameObjects.Question.DialogBank;
import gameObjects.Question.Info;
import gameObjects.Question.Question;
import gameObjects.Question.ScenarioQuestion;
import gameObjects.Question.YesNoQuestion;
import helperClasses.CsvWriting;

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
	BitmapFont yourBitmapFontName;

	TextInputListener listener;
	private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
			new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
	private TextButton NotesButton = (TextButton) new TextButton("Menu", skin)
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

	int asfa = 1000;
	Texture completedRoom;

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

	boolean interacting;
	boolean battling;

	DialogBank dialogFactory;
	ArrayList<Room> rooms;
	Room currentRoom;

	NPC nearestNPC;

	@Override
	public void create() {
		batch = new SpriteBatch();
		yourBitmapFontName = new BitmapFont();
		player = new UserCharacter();
		
		completedRoom = new Texture("completed_room.png");

		dialogFactory = new DialogBank();
		Database.bank = dialogFactory;
		rooms = ContentLoader.load(dialogFactory);		
		changeRoom("overworld");
		//need this for game progression
		currentRoom.npcs.add(new InfoNPC(287, 33, "You need to defeat all the gym bosses before you can take on the final gym!", ""));

		debugString = "start";

		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		
		table.add(NotesButton).size(WIDTH / 8, HEIGHT / 10);
		table.align(Align.bottomRight);
		table.setFillParent(true);
		stage.addActor(table);

		YNTable.add(NoButton).size(100, 50);
		YNTable.add(YesButton).size(100, 50);
		YNTable.align(Align.bottom);
		YNTable.setFillParent(true);
		YNstage.addActor(YNTable);

		table.add(TalkButton).size(100, 50);
		// talkTable.add(TalkButton).size(100, 50);
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
	}

	public void update(float deltaTime) {
		player.setDeltaTime(deltaTime);

		// get nearest npc
		if (!interacting) {

			if (!(Gdx.input.getX() >= 460 & Gdx.input.getY() >= 430)) {
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
			}
			
			// Battle Start
			if (updateNearestNPC()) {
				battling = true;
				((Trainer)nearestNPC).hasBattled = true;
				((Game) Gdx.app.getApplicationListener()).setScreen(new BattleScreen(new Battle((Trainer) nearestNPC, batch)));
			}
		}

		debugString = Integer.toString((int) player.hitBox.x) + ":"
				+ Integer.toString((int) player.hitBox.y) + " "
				+ Integer.toString(WIDTH) + "/" + Integer.toString(HEIGHT);
	}

	boolean updateNearestNPC() {
		nearestNPC = null;
		double smallestDistance = Integer.MAX_VALUE;
		for (NPC npc : currentRoom.npcs) {
			double distance = distanceBetween(npc, player);
			if (distance < smallestDistance && distance <= npc.SIGHT_RANGE) {
				nearestNPC = npc;
				smallestDistance = distance;
				if (npc instanceof Trainer && !((Trainer) npc).hasBattled)
					return true;
			}
		}
		return false;
	}

	double distanceBetween(Entity a, Entity b) {
		int x = a.getCenter()[0] - b.getCenter()[0];
		int y = a.getCenter()[1] - b.getCenter()[1];
		return Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), .5);
	}

	@Override
	public void render(float deltaTime) {

		update(deltaTime);

		TalkButton.setColor(Color.RED);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		yourBitmapFontName.draw(batch, mouseX + ", " + (480 - mouseY), 25, 100);

		batch.end();
		// if we are in a battle
		if (!battling)
			drawRoom();

		/*
		 * Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND); for(Obstacle obs :
		 * currentRoom.getObstacles()) { drawHitbox(obs); }
		 * 
		 * for(Door d : currentRoom.doors) { drawDoor(d); }
		 */
		stage.act();
		stage.draw();
	}

	void drawRoom() {
		batch.begin();
		batch.draw(currentRoom.background, 0, 0);
		
		if (currentRoom.subject.equals("overworld")) {
			for(Room gym : rooms) {
				if (gym.completed) {
					if (gym.subject.equals("dates")) 
						batch.draw(completedRoom, 32, 321);
					else if (gym.subject.equals("resources"))
						batch.draw(completedRoom, 32, 130);
					else if (gym.subject.equals("advisor"))
						batch.draw(completedRoom, 480, 321);
					else if (gym.subject.equals("udsis"))
						batch.draw(completedRoom, 480, 130);
				}
			}
		}

		for (NPC npc : currentRoom.npcs) {
			draw(npc);
		}

		draw(player);
		batch.end();

		if (interacting) {

			batch.begin();
			if (nearestNPC instanceof YesNoNPC) {
				((YesNoNPC) nearestNPC).drawText(batch);
				batch.end();
				if (!((YesNoNPC) nearestNPC).answered) {
					YNstage.act();
					YNstage.draw();
				}
			} else {
				((InfoNPC) nearestNPC).drawText(batch);
				batch.end();
			}

			exitStage.act();
			exitStage.draw();
		} else {
			if (nearestNPC != null) {
				TalkButton.setColor(Color.GREEN);
				stage.act();
				stage.draw();
			}
		}
	}

	void draw(Entity e) {
		batch.draw(e.texture, e.getX(), e.getY(), e.getWidth(), e.getHeight());
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
		shapeRenderer.rect(d.hitbox.x, d.hitbox.y, d.hitbox.width,
				d.hitbox.height);
		shapeRenderer.end();
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
				if (nearestNPC != null && !(nearestNPC instanceof Trainer)) {
					interacting = true;
					if (nearestNPC instanceof YesNoNPC)
						((YesNoNPC) nearestNPC).answered = false;
					else {
						String notes = nearestNPC.getNotes();
						if (notes != "")
							Notes.addnote(nearestNPC.getNotes());
					}
				}
				else if(nearestNPC instanceof Trainer && !((Trainer) nearestNPC).defeated){
					battling = true;
					((Game) Gdx.app.getApplicationListener()).setScreen(new BattleScreen(new Battle((Trainer) nearestNPC, batch)));
				}
				
			}
		});

		ExitButton.getLabel().setFontScale((float) 0.8);
		ExitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				interacting = false;
			}
		});

		YesButton.getLabel().setFontScale((float) 0.8);
		YesButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (nearestNPC != null && nearestNPC instanceof YesNoNPC) {
					if (!((YesNoNPC) nearestNPC).question.completed) {
						((YesNoNPC) nearestNPC).question.attempts = 1;
						if (((YesNoNPC) nearestNPC).sendAnswer("yes")) {
							((YesNoNPC) nearestNPC).question.completed = true;
							Notes.addnote(nearestNPC.getNotes());
						}
					}
				}
			}
		});
		
		NoButton.getLabel().setFontScale((float) 0.8);
		NoButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				if (nearestNPC != null && nearestNPC instanceof YesNoNPC) {
					if (!((YesNoNPC) nearestNPC).question.completed) {
						((YesNoNPC) nearestNPC).question.attempts = 1;
						if (((YesNoNPC) nearestNPC).sendAnswer("no")) {
							Notes.addnote(nearestNPC.getNotes());
						}
					}
				}
			}
		});

		NotesButton.getLabel().setFontScale((float) 0.8);
		NotesButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.input.setInputProcessor(null);
				((Game) Gdx.app.getApplicationListener()).setScreen(new OverworldMenu());
			}
		});

		

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
		
	}

	void changeRoom(Door door) {
		if (door != null) {
			for (Room room : rooms) {
				if (room.subject.equals(door.roomToHash) && !room.completed) {
					player.setX((int) door.outPosition.x);
					player.setY((int) door.outPosition.y);
					door.out = false;
					currentRoom = room;
				}
			}
		}
	}
	
	void changeRoom(String destination) {
		for (Room room : rooms) {
			if (room.subject.equals(destination)) {
				currentRoom = room;
			}
		}
	}
}