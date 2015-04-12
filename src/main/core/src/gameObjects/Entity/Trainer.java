package gameObjects.Entity;

public class Trainer extends NPC {
	
	private String question;
	private String[] answers;
	private final int sightRange = 50;
	
	public Trainer (Position position, String sprite, String question, String[] answers){
		
		super(position, sprite);
		this.question = question;
		this.answers = answers;
		
	}
	
	public boolean playerInRange(UserCharacter player){
		
		if((this.position.getX() == player.position.getX()) && (Math.abs(this.position.getY() - player.position.getY()) < this.sightRange)){
			return true;
		}
		else if((this.position.getY() == player.position.getY()) && (Math.abs(this.position.getX() - player.position.getX()) < this.sightRange)){
			return true;
		}
		
		return false;
	}
	
}
