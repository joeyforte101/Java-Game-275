package edu.udel.cisc275_15s.bigo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import edu.udel.cisc275_15s.bigo.BigOGame;
import edu.udel.cisc275_15s.bigo.MainClass;
import edu.udel.cisc275_15s.bigo.MainMenu;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		new LwjglApplication(new MainClass(), config);
	}
}
