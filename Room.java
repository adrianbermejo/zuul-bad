import java.util.HashMap;

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
    private HashMap <String,Room>habitaciones;
    public String description;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
         HashMap<String,Room> habitaciones = new HashMap<String,Room>();
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
            habitaciones.put("north",north);
        if(east != null)
             habitaciones.put("east",east);
        if(south != null)
             habitaciones.put("south",south);
        if(west != null)
            habitaciones.put("west",west);
        if(southEast != null)
             habitaciones.put("southEast",southEast);
             if(northEast != null)
            habitaciones.put("northEast",northEast);
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
            devolver = habitaciones.get("north");
        }
        if(direction.equals("east")) {
            devolver = habitaciones.get("east");
        }
        if(direction.equals("south")) {
            devolver = habitaciones.get("south");
        }
        if(direction.equals("west")) {
            devolver= habitaciones.get("west");
        }
        if(direction.equals("southEast")) {
            devolver = habitaciones.get("southEast");
        }
         if(direction.equals("northEast")) {
            devolver = habitaciones.get("northEast");
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
         if( habitaciones.get("north") != null) {
            devolver+="north";
        }
        if( habitaciones.get("east") != null) {
            devolver+="east ";
        }
        if(habitaciones.get("south") != null) {
             devolver+="south ";
        }
        if(habitaciones.get("west") != null) {
             devolver+="west ";
        }
          if(habitaciones.get("southEast") != null) {
           devolver+="southEast ";
        }
       
          if(habitaciones.get("northEast") != null) {
           devolver+="northEast ";
        }
        
        return devolver;
    }
}








