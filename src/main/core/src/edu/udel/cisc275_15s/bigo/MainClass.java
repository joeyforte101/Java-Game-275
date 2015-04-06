package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MainClass extends Game implements ApplicationListener {
	private BigOGame gameScreen;
    private MainMenu menuScreen;
    
	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}
	
    void setBigOGame()
    {
        gameScreen=new BigOGame(this);
        setScreen(gameScreen);
    }
    void setMainMenu()
    {
        menuScreen=new MainMenu(this);
        setScreen(menuScreen);
    }



    @Override
    public void dispose() {

        super.dispose();
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        super.render();
    }

    @Override
    public void resize(int width, int height) {

        super.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}

