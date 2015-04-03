package gameObjects;

import gameObjects.Entity.NPC;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class Room {
	
	public Texture background;
	public List<NPC> npcs;
	
	public Room (String tex, List<NPC> fellas) {
		background = new Texture(tex);
		npcs = fellas;
	}
}
