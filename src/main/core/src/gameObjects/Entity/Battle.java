package gameObjects.Entity;


import gameObjects.Room;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Battle implements ApplicationListener, InputProcessor{
	
	private Entity Player;
	private NPC opponent;
	private String location;
	private String[] Questions = {"When is the drop/add deadline?", "sqrt(144)", "acrsin(0)?"};
	private String[][] Answers = {{"1 week","2 weeks","3 weeks","4 weeks","2weeks"},{"a","b","12","13","12"},{"dog","cat","angle","none of the above", "angle"}};
	private int[] Correct = {0,0,0};
	boolean qpresented = false;
	int index = 0;
	int ansIndex = 0;
	
	public Battle(NPC opponent){
		this.opponent = opponent;
	}
	
	public Battle(NPC opponent, String location){
		this.opponent = opponent;
		this.location = location;
	}
	
	public boolean battleLoop(){
		return false;
		
	}
	
	public void draw(){
		BitmapFont question = new BitmapFont();
		BitmapFont instructions = new BitmapFont();
		BitmapFont ans = new BitmapFont();
		
		
		SpriteBatch batch = new SpriteBatch();
		Pixmap speechBub = new Pixmap(300,100, Pixmap.Format.Intensity);
		speechBub.setColor(Color.WHITE);
		speechBub.fill();
		Texture oppBub = new Texture(speechBub);
		Texture pcBub = new Texture(speechBub);
		Texture background = new Texture("background2.png");
		Texture opp = new Texture("opponent.png");
		Texture pc = new Texture("player.png");
		Texture next = new Texture("arrow.png");
		batch.begin();
		batch.draw(background, 0, 0);
		//batch.draw(next, 450, 30);
		batch.draw(pc, 0, 0);
		batch.draw(opp, 590, background.getHeight() - opp.getHeight());
		batch.draw(pcBub, 100, 30);
		batch.draw(oppBub, 250, 375);
		question.draw(batch, Questions[0], 275, 400);
		instructions.draw(batch, "Touch the correct answer", 100, 150);
		ans.draw(batch, "A)" + Answers[0][0]  +"  B)" + Answers[0][1]  + "  C)" + Answers[0][2]  + "  D)" + Answers[0][3], 100, 130);
		qpresented = true;
		
				while(index < Questions.length){
					if(Correct[index] == 1){
						index++;
						ansIndex = 0;
					}
					question.draw(batch, Questions[index],275, 400);
					
					
					if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
						
					}
					index++;
					question.dispose();
				}
		
		batch.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		if(qpresented){
			if((screenX > 100 &&screenX < 400) && (screenY > 30 && screenY < 130)){
				if(ansIndex == 3){
					ansIndex = 0;
				}else{
					ansIndex++;
				}
			}
			System.out.println(ansIndex);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}