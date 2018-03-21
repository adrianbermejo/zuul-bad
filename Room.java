import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{

    private HashMap <String,Room>habitaciones;
    public String description;
    private Item item;
    private ArrayList<Item> listaItems;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {

        habitaciones = new HashMap<>();
        this.description = description;

        listaItems = new ArrayList<Item>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){

        habitaciones.put(direction, neighbor);

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

        return habitaciones.get(direction);

    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){

        String devolver ="Exit: ";
        Set<String>direccion=habitaciones.keySet();
        for(String actual:direccion){

            devolver+=actual +" ";

        }

        return devolver;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){

        String descripcion = "Estás en " + description + "Salidas: " + getExitString();
        if (item != null){

            descripcion += "Tu item :" + item.getDescription() + "tiene un peso:" + item.getPeso();
        }
        return descripcion;

     
    }

    public void addItem(String itemDescription, int itemWeigth){
        Item itemAñadido = new Item (itemDescription,itemWeigth);
        listaItems.add(itemAñadido);

    }

    public String informacionObjetosDeLaSala(){
        String informacionObjetos = "";
        if(listaItems.size() < 0){
            informacionObjetos = "No hay ningun de objeto";
        }

        else{
            for(Item objetosDeLaLista  :listaItems){
                informacionObjetos += objetosDeLaLista.informacionItem();

            }

        }

        return informacionObjetos;
    }

    
}


