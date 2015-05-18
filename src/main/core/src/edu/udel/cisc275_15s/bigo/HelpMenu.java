package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HelpMenu implements Screen, TextInputListener {

    private Stage stage = new Stage();
    private Table table = new Table();
    private Table table2 = new Table();
    private Table table3 = new Table();
    SpriteBatch batch;
    private Skin skin = new Skin(Gdx.files.internal("skins/menuSkin.json"),
        new TextureAtlas(Gdx.files.internal("skins/menuSkin.pack")));
    
    //Backgrounds, along with i will load them to the array
    private Texture image1 = new Texture("Game Help Button/img1.png");
    private Texture image2 = new Texture("Game Help Button/img2.png");
    private Texture image3 = new Texture("Game Help Button/img3.png");
    private Texture image4 = new Texture("Game Help Button/img4.png");
    private Texture image5 = new Texture("Game Help Button/img5.png");
    private Texture image6 = new Texture("Game Help Button/img6.png");
    private Texture[] images = {image1, image2, image3, image4, image5, image6};
    public int imgindex = 0;
    
    private TextButton buttonNext = new TextButton("Next", skin);
    private TextButton buttonExit = new TextButton("Exit", skin);
    private TextButton buttonPrevious = new TextButton("Previous", skin);
   
    
    @Override
    public void render(float delta) {
    	batch.begin();
    	batch.draw(images[imgindex],0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    	batch.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    	batch = new SpriteBatch();
    	
    	//This will increment images
    	buttonNext.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	if (imgindex == (images.length-1)){
            		imgindex = (images.length-1);
            	}
            	else{
            	imgindex++;}
            }
        });
    	// This will go to previous images
    	buttonPrevious.addListener(new ClickListener(){
    		 @Override
             public void clicked(InputEvent event, float x, float y) {
             	if (imgindex == 0){
             		imgindex = 0;
             	}
             	else{
             	imgindex--;}
             }
    	});
    	
    	buttonExit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
                
            }
        });
    	
    	
    	//Setup the button layouts
        table.add(buttonExit).size(70,35);
        table.align(Align.topLeft);
        table2.add(buttonNext).size(100,40);
        table2.align(Align.bottomRight);
        table3.add(buttonPrevious).size(130,40);
        table3.align(Align.bottomLeft);
        

        table.setFillParent(true);
        table2.setFillParent(true);
        stage.addActor(table);
        stage.addActor(table2);
        stage.addActor(table3);

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
