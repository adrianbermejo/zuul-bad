/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    public String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
private Room northEastExit;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west,Room southEast,Room northEast) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null)
            southEastExit = southEast;
             if(northEast != null)
           northEastExit = northEast;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public Room getExit(String direction){
        Room devolver = null;
        if(direction.equals("north")) {
            devolver = northExit;
        }
        if(direction.equals("east")) {
            devolver = eastExit;
        }
        if(direction.equals("south")) {
            devolver = southExit;
        }
        if(direction.equals("west")) {
            devolver= westExit;
        }
        if(direction.equals("southEast")) {
            devolver = southEastExit;
        }
         if(direction.equals("northEast")) {
            devolver = northEastExit;
        }
        return devolver;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        
        String devolver ="Exit: ";
         if(northExit != null) {
            devolver+="north";
        }
        if(eastExit != null) {
            devolver+="east ";
        }
        if(southExit != null) {
             devolver+="south ";
        }
        if(westExit != null) {
             devolver+="west ";
        }
          if(southEastExit != null) {
           devolver+="southEast ";
        }
       
          if(northEastExit != null) {
           devolver+="northEast ";
        }
        
        return devolver;
    }
}








