package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.Game;

public class MainClass extends Game {
    public static final String TITLE="Game Project"; 
    public static final int WIDTH=480,HEIGHT=800; // used later to set window size
    public static BigOGame Game = null;
    @Override
    public void create() {
            setScreen(new Splash());
    }
}