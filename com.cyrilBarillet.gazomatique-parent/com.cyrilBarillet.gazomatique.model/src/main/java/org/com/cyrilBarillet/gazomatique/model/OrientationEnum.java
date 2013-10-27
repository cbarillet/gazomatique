package org.com.cyrilBarillet.gazomatique.model;

public enum OrientationEnum 
{
	NORTH("N"),
	SOUTH("S"),
	EAST("E"),
	WEST("W");
	
	private final String name;       

    private OrientationEnum(String s) {
        name = s;
    }

    public static OrientationEnum getInstruction(final String orientation)
    {
    	switch(orientation)
		{
		case "N":
			return OrientationEnum.NORTH;
		case "S":
			return OrientationEnum.SOUTH;
		case "E":
			return OrientationEnum.EAST;
		case "W":
			default:
				throw new RuntimeException("Unexpected orientation : " + orientation);
		}
    }
    
    public boolean equalsName(final String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString(){
       return name;
    }
}
