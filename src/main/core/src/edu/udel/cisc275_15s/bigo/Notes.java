package edu.udel.cisc275_15s.bigo;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Notes implements Screen, TextInputListener {
	private Stage stage = new Stage();
	private Table table = new Table();
	public static int progress = 0;
	TextInputListener listener;
	public static int starcount = 0;
	private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
			new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
	private TextButton exitButton = (TextButton) new TextButton("X", skin)
			.align(Align.topRight);
	SpriteBatch batch;
	private static ArrayList<String> notes = new ArrayList<String>();
	BitmapFont text = new BitmapFont();
	Screen previous;

	public Notes(Screen prev) {
		previous = prev;
		batch = new SpriteBatch();
		exitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener()).setScreen(previous);
			}
		});

		table.add(exitButton).size(50, 50);
		table.align(Align.topRight);
		table.setFillParent(true);
		stage.addActor(table);
	}

	public void show() {
        Gdx.input.setInputProcessor(stage);
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch = new SpriteBatch();
		batch.begin();
		Texture img;

		for (float i = 0; i < 5; i++) {

			if (i < starcount)
				img = new Texture("star1.png");
			else
				img = new Texture("star2.png");
			batch.draw(img, ((float) .2 * Gdx.graphics.getWidth() * i),
					(float) (Gdx.graphics.getHeight() * (-.05)), 125, 125);

		}

		text.setColor(Color.WHITE);
		for (float i = 0; i < notes.size(); i++)
			text.drawWrapped(batch, notes.get((int) i), 30,
					Gdx.graphics.getHeight() - i * 20 - 10,
					Gdx.graphics.getWidth() - 100);

		batch.end();
		stage.act();
		stage.draw();

	}

	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
		// dispose();

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

	public void increaseprogress() {
		progress++;
	}

	public static void addnote(String anote) {
		if (!notes.contains(anote))
			notes.add(anote);
	}

}
