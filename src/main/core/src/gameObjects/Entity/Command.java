package gameObjects.Entity;

public enum Command {
	
	MESSAGE("MESSAGE"),
	QUESTION("QUESTION"),
	ANSWER("ANSWER"),
	SOLUTION("SOLUTION");
	
	private String command;
	
	
	Command(String command){
		this.command =  command;
	}
	
	public String getCommand(){
		return this.command;
	}
	
	public static Command fromString(String text){
		if(text != null){
			for(Command com: Command.values()){
				if(text.equals(com.command)){
					return com;
				}
			}
		}
		
		return null;
	}

}
