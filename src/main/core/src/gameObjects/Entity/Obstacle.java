package gameObjects.Entity;

public class Obstacle extends Entity {

	public Obstacle(int[] bounds) {
		super (bounds[0], bounds[1], bounds[2], bounds[3]);
	}	
	
	public Obstacle(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public Obstacle(int x, int y, String sprite) {
		super(x, y, sprite);
	}

}