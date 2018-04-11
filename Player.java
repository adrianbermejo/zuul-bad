import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class jugador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own

    private Room currentRoom;
    private Stack<Room> stack;
    private ArrayList<Item> bolsillo;
    private int pesoEncima;
    private static final int PESOMAXIMO=1000;
    private Item item;
    /**
     * Constructor for objects of class jugador
     */
    public Player(Room currentRoom)
    {
        // initialise instance variables
        this.currentRoom =currentRoom;
        stack = new Stack<>();
        bolsillo = new ArrayList<>();
        pesoEncima =0;
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {

        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("no sigo que me pierdo !");
        }
        else {
            stack.push(currentRoom);
            currentRoom = currentRoom.getExit(direction);
            System.out.println("por fin llegue  " + currentRoom.getDescription());
            look();
        }
    }

    
    
    public void look() 
    {
        System.out.println(currentRoom.getLongDescription());
        System.out.println(currentRoom.informacionObjetosDeLaSala());
    }

    public void eat() 
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    public void back(){
        if(!stack.empty()){
            currentRoom= stack.pop();
            look();
        }
        else{
            System.out.println("No se puede volver para atras");
        }
    }

    public Room current(){

        return currentRoom;
    }

    public void take() 
    {
        System.out.println("recojigo objeto");
        System.out.println(currentRoom.informacionObjetosDeLaSala());
    }

    private void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("No has indicado el nombre el objeto a cojer");
            return;
        }
        ArrayList<Item>bolsilloActual = null;
        ArrayList<Item> a= currentRoom.getItem();
        if (a.size() > 0){
            bolsilloActual = a;
        }
        String objetoACoger = command.getSecondWord();

        if (bolsilloActual != null && pesoEncima + bolsilloActual.get(Integer.parseInt(objetoACoger)).getPeso() < PESOMAXIMO){
            System.out.println("Has cogido el siguiente objeto:" + "\n");
            //Mostramos la posicion del objeto y la informacion del item
            System.out.println("Posicion: " + Integer.parseInt(objetoACoger) + "\n" + " " 
                + bolsilloActual.get(Integer.parseInt(objetoACoger)).informacionItem());
            //Mostramos el peso de la mochila
            pesoEncima += bolsilloActual.get(Integer.parseInt(objetoACoger)).getPeso();
            bolsillo.add(bolsilloActual.get(Integer.parseInt(objetoACoger)));
            //Eliminamos la posicion del objeto que hemos cogido
            bolsilloActual.remove(Integer.parseInt(objetoACoger));
        }

        else{
            if (bolsilloActual == null){
                System.out.println("No hay objetos en la sala");
            }

        }
      }
      
       public void items(){

        if (!bolsillo.isEmpty()) {
            for (Item itemActual : bolsillo) {
                System.out.println(itemActual.getDescription());
            }
        }
        else {
            System.out.println("Tienes el bolsillo vacío.");
        }
    }

    public void drop(Command command)
    {

        String item = command.getSecondWord();
        Item itemABorrar = null;
        for (Item itemASoltar : bolsillo) {
            if (itemASoltar.getId().equals(item)) {
                itemABorrar= itemASoltar;                    
            }
        }
       bolsillo.remove(itemABorrar);
        if (itemABorrar == null) {
            System.out.println("NO tienes ese objeto!");
        }
        else {
            currentRoom.itemASoltar(itemABorrar);
            pesoEncima -= itemABorrar.getPeso();
            System.out.println("Has soltado " + itemABorrar.getDescription() + ", con un peso de " + itemABorrar.getPeso());
        }
    }
}
