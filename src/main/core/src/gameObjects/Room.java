package gameObjects;

import gameObjects.Entity.NPC;
import gameObjects.Entity.Obstacle;
import gameObjects.Entity.Trainer;
import gameObjects.Entity.UserCharacter;
import helperClasses.Directions;
import helperClasses.Mapping;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Room {
	public final int WIDTH = Gdx.graphics.getWidth();
	public final int HEIGHT = Gdx.graphics.getHeight();

	public Texture background;
	public boolean completed;
	public String subject;
	public int xOffset, yOffset;
	public List<NPC> npcs;
	public List<Door> doors;
	public List<Obstacle> obstacles;

	public Room() {
		this.npcs = new ArrayList<NPC>();
		this.obstacles = new ArrayList<Obstacle>();
		this.doors = new ArrayList<Door>();
		xOffset = 0;
		yOffset = 0;
	}
	
	public Room(String background) {
		this.background = new Texture(background);
		this.npcs = new ArrayList<NPC>();
		this.obstacles = new ArrayList<Obstacle>();
		this.doors = new ArrayList<Door>();
		xOffset = 0;
		yOffset = 0;
	}

	public Room(String background, List<NPC> npcs) {
		this.background = new Texture(background);
		this.npcs = npcs;
		this.obstacles = new ArrayList<Obstacle>();
		this.doors = new ArrayList<Door>();
		xOffset = 0;
		yOffset = 0;
	}

	public Room(String background, List<NPC> npcs, List<Door> doors) {
		this.background = new Texture(background);
		this.npcs = npcs;
		this.obstacles = new ArrayList<Obstacle>();
		this.doors = doors;
		xOffset = 0;
		yOffset = 0;
	}

	public void addDoor(String roomTo, int[] position, int[] out) {
		doors.add(new Door(roomTo, new Rectangle(position[0], position[1],
				position[2], position[3]), new Rectangle(out[0], out[1], 0, 0)));
	}

	public void addNPC(NPC npc) {
		npcs.add(npc);
	}

	public void addObstacle(Obstacle obs) {
		obstacles.add(obs);
	}

	public Door move(UserCharacter c, boolean t, int x, int y) {
		y = Mapping.yScreenToText(y);
		int centerx = c.getX() + c.getWidth() / 2;
		int centery = c.getY() + c.getHeight() / 2;

		// boundaries
		boolean rightBound = (c.getX() + c.getWidth() + c.moveSpeed) < WIDTH;
		boolean leftBound = (c.getX() - c.moveSpeed) > 0;
		boolean topBound = (c.getY() + c.getHeight() + c.moveSpeed) < HEIGHT;
		boolean bottomBound = (c.getY() - c.moveSpeed) > 0;
		boolean xBigger = (Math.abs(x - centerx) > Math.abs(y - centery));

		// makes sure horizontal or vertical movement is done first
		if (!t) {
			c.movingHorizontally = xBigger;
		}

		if (!c.talking) {
			if (c.movingHorizontally) {
				// move right
				if (x > centerx
						&& !(x - c.moveSpeed < centerx && x + c.moveSpeed > centerx)
						&& rightBound) {
					c.setX(c.getX() + c.moveSpeed);
					c.direction = Directions.RIGHT;
				}
				// move left
				else if (x < centerx
						&& !(x - c.moveSpeed > centerx && x + c.moveSpeed < centerx)
						&& leftBound) {
					c.setX(c.getX() - c.moveSpeed);
					c.direction = Directions.LEFT;
				} else{
					//makes sure the guy isnt running in place and changes to standing still animation
					c.setDeltaTime(0);
					c.setAnimationCounter(1);
					c.movingHorizontally = false;
				}
			} else if (!c.movingHorizontally) {
				// move up
				if (y > centery
						&& !(y - c.moveSpeed < centery && y + c.moveSpeed > centery)
						&& topBound) {
					c.setY(c.getY() + c.moveSpeed);
					c.direction = Directions.UP;
				}
				// move down
				else if (y < centery
						&& !(y - c.moveSpeed > centery && y + c.moveSpeed < centery)
						&& bottomBound) {
					c.setY(c.getY() - c.moveSpeed);
					c.direction = Directions.DOWN;
				} else{
					c.movingHorizontally = true;
					//makes sure the guy isnt running in place and changes to standing still animation
					c.setAnimationCounter(1);
					c.setDeltaTime(0);
				}
			}
			c.resetPositionAfterIntersection(getObstacles());
			c.animate();
		}

		// check door interaction
		for (Door d : doors) {
			if (d.inMe(c)) {
				if (d.out) {
					return d;
				}
			} else {
				d.out = true;
			}
		}
		return null;
	}

	public List<Obstacle> getObstacles() {
		ArrayList<Obstacle> result = new ArrayList<Obstacle>();
		result.addAll(npcs);
		result.addAll(obstacles);
		return result;
	}
	
	public List<Trainer> getTrainers() {
		ArrayList<Trainer> result = new ArrayList<Trainer>();
		for (NPC npc : npcs) {
			if (npc instanceof Trainer)
				result.add((Trainer)npc);
		}
		return result;
	}
}
