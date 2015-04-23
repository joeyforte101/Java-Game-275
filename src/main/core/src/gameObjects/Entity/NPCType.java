package gameObjects.Entity;

public enum NPCType {

	INFO("INFO"),
	YSNO("YSNO"),
	TRNR("TRNR");
	
	private String text;
	
	NPCType(String text){
		this.text = text;
	}
	
	public static NPCType fromString(String text){
		if(text != null){
			for(NPCType type: NPCType.values()){
				if(text.equals(type.text)){
					return type;
				}
			}
		}
		
		return null;
	}
	
}
