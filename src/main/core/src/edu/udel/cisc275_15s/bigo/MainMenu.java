package edu.udel.cisc275_15s.bigo;

import javax.swing.GroupLayout.Alignment;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenu implements Screen, TextInputListener {

    private Stage stage = new Stage();
    private Table table = new Table();
    TextInputListener listener;
    Music music = Gdx.audio.newMusic(Gdx.files.internal("mymusic.mp3"));
    

    private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
        new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));

    private TextButton buttonPlay = new TextButton("Play", skin),
        buttonExit = new TextButton("Exit", skin),
        buttonHelp = new TextButton("Help", skin);
    private TextField userName = new TextField("", skin);
    private Label title = new Label("Big O Game",skin);
    private Label hint = new Label("Hit the 'Help' button for first time players",skin);
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    	userName.setAlignment(1);
    	userName.setMessageText(" Enter Username");
    	music.play(); 
    	//Gdx.input.getTextInput(listener, "Enter Username", "", null);
        buttonPlay.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Same way we moved here from the Splash Screen
                //We set it to new Splash because we got no other screens
                //otherwise you put the screen there where you want to go
                ((Game)Gdx.app.getApplicationListener()).setScreen(new BigOGame());
                
            }
        });
        buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                // or System.exit(0);
            }
        });
        buttonHelp.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
                //We will save this help button for our tutorial
            }
        });

        //The elements are displayed in the order you add them.
        //The first appear on top, the last at the bottom.
        title.setFontScale(1.3f);
        table.add(title).padBottom(40).row();
        //esttable.add(hint).padBottom(50).row(); 
        table.add(userName).size(250,60).padBottom(20).row();
       //userName.setPosition(150, 50);
        //mStage.addActor(userName);
        table.add(buttonPlay).size(150,60).padBottom(20).row();
        table.add(buttonHelp).size(150,60).padBottom(20).row();
        table.add(buttonExit).size(150,60).padBottom(30).row();
        

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        music.dispose();
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