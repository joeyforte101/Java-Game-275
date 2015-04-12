package gameObjects;

import gameObjects.Entity.NPC;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;

public class Room {
	
	public Texture background;
	public List<NPC> npcs;
	
	public Room (String background, List<NPC> npcs) {
		this.background = new Texture(background);
		this.npcs = npcs;
	}
}
