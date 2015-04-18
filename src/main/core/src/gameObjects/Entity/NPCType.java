package gameObjects.Entity;

public enum NPCType {

	INFO("info"),
	YSNO("ysno"),
	TRNR("trnr");
	
	private String text;
	
	NPCType(String text){
		this.text = text;
	}
	
	public static NPCType fromString(String text){
		if(text != null){
			for(NPCType type: NPCType.values()){
				if(text.equalsIgnoreCase(type.text)){
					return type;
				}
			}
		}
		
		return null;
	}
	
}
