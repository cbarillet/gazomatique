package org.com.cyrilBarillet.gazomatique.model;

public enum InstructionEnum {
	RIGHT("D"),
	LEFT("G"),
	MOVE("A");
	
	private final String name;       

    private InstructionEnum(String s) {
        name = s;
    }

    public static InstructionEnum getInstruction(final String instruction)
    {
    	switch(instruction)
		{
		case "A":
			return InstructionEnum.MOVE;
		case "G":
			return InstructionEnum.LEFT;
		case "D":
			return InstructionEnum.RIGHT;
			default:
				throw new RuntimeException("Unexpected instruction : " + instruction);
		}
    }
    
    public boolean equalsName(final String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString(){
       return name;
    }
}
