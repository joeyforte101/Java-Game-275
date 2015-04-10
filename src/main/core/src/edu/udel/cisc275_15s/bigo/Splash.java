
package edu.udel.cisc275_15s.bigo;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Splash implements Screen {
    private Texture texture = new Texture(Gdx.files.internal("MainMenu.png"));
    private Image splashImage = new Image(texture);
    private Stage stage = new Stage();
    private SpriteBatch batch;
    private Animation animation;
    private float time;
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //stage.act();
        //stage.draw();
        batch.begin();
        batch.draw(animation.getKeyFrame((time += delta), true), 0, 0);
        batch.end();
        
    }

    @Override
    public void resize(int width, int height) {     
    }

    @Override
    public void show() {
        //stage.addActor(splashImage);
    	batch = new SpriteBatch();
        animation = new Animation(1/18f, new TextureRegion(new Texture("bigOTitle/TheBigO#1.jpg")), new TextureRegion(new Texture("bigOTitle/TheBigO#2.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#3.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#4.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#5.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#6.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#7.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#8.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#9.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#10.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#11.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#12.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#13.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#14.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#15.jpg")),new TextureRegion(new Texture("bigOTitle/TheBigO#16.jpg")),
        		new TextureRegion(new Texture("bigOTitle/TheBigO#17.jpg")));
        
        
        //((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
        /*//animation.setPlayMode(Animation);
        splashImage.addAction(Actions.sequence(Actions.alpha(0)
                       ,Actions.fadeIn(.5f),Actions.delay(6),Actions.run(new Runnable() {
            @Override
            public void run() {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        })));*/
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
        texture.dispose();
        stage.dispose();
        
    }

}