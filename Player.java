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
    private static final int PESOMAXIMO=500;
    private Item item;
    private int resistencia;
    /**
     * Constructor for objects of class jugador
     */
    public Player(Room currentRoom)
    {
        // initialise instance variables
        this.currentRoom =currentRoom;
        stack = new Stack<>();
        bolsillo = new ArrayList<Item>();
        pesoEncima =0;
        resistencia = 5;
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
        
        else if(resistencia ==0 ){
             System.out.println("estoy muy cansado paremos un rato !");
        }
        else {
            stack.push(currentRoom);
            currentRoom = currentRoom.getExit(direction);
            System.out.println("por fin llegue  " + currentRoom.getDescription());
            look();
            reducirResistencia();
        }
    }

    public void resistencia()
    {
          System.out.println(  "que de energia tengo con esto podre anda " + resistencia +  "veces");
          if(resistencia<3){
              System.out.println("estoy empezando a cansarme deberia beeber un cafelito en el bar de paco");
              
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



    public void take(String item){

        Item itemACoger = currentRoom.itemACoger(item);

        if(itemACoger == null){
            System.out.println("No hay objetos");
        }
        else{
            if(itemACoger.getCogerObjeto()){
                if(itemACoger.getPeso() + pesoEncima > PESOMAXIMO){
                    System.out.println("Peso maximo superado" + "deje  algun objeto");
                    currentRoom.itemASoltar(itemACoger);
                }
                else{
                    bolsillo.add(itemACoger);
                    pesoEncima += itemACoger.getPeso() ;
                    System.out.println("Has recogido" + itemACoger.getDescription());
                    
                }
            }
            else{
                System.out.println("NO PUEDES COGER ES OBJETO  ROBAR ESTA MAL");
                currentRoom.itemASoltar(itemACoger);
            }

        }
    }
    

    public void items(){
        if (!bolsillo.isEmpty()) {
            for (Item itemActual : bolsillo) {
                System.out.println(itemActual.getId() + " " + itemActual.getDescription());
            }
        }
        else {
            System.out.println("Tienes el bolsillo vacía.");
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
    
    public void beber()
    {
       
        Item hayCafe = null;
        for (Item itemASoltar : bolsillo) {
            if (itemASoltar.getId().equals("cafelito")) {
                 hayCafe= itemASoltar; 
                 resistencia += 5;                 
            }
        }
        if (hayCafe!=null){
        bolsillo.remove(hayCafe);
         System.out.println("te has bebido un cafetito muy rico tienes mucha energia para pasear " + resistencia);  
      }
        else{
          System.out.println("no tienes nada que beber ");  
        }
        
    }
    
    public void reducirResistencia()
    {
     resistencia -=1;   
        
    }
}
