

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room asilo,bar,instituto,obrasCalle,cleseYogaAlAireLibre,hospital,parque,salaUrgencias,kiosco;
      
               asilo  = new Room("al asilo: aqui solo hay viejas esto es aburrido demos una vuelta");
               bar = new Room(" al bar: que pena no hay partida volvere luego");
                       obrasCalle = new Room("a las obras en la calle: ¿que estara metiendo que ponen unos tubos tan grandes?fijo que lo ponen mal y tienen que volver a levantar");
              cleseYogaAlAireLibre = new Room(" a cleses de Yoga Al Aire Libre: que de mojucas de buen ver hay hoy voy a hacerme el desmayado a ver si me hace el bocaaboca");
              hospital = new Room("al hospital: como que me duele un poco la cadera voy a urgencias a que me hagan una radiografia");
              instituto = new Room("al istituto: que de chavalines jugando al futbol y los que no detras de las chicas que recuerdos ");
              parque= new Room("al parque: voy a dar de comer a las palomas y a ver si hay alguna actividad");
        salaUrgencias= new Room("a urgencias: se le levanto a una niña la falda se le vio todo y me dio un infarto.... valio la pena");
        kiosco= new Room("al  kiosco: me qiede sin tabaco voy hasta el kiosco lo compro hablo con el dependiente que es hijo de Pepe el del estanco");
        // initialise room exits
         asilo.setExits(bar, hospital, instituto,obrasCalle ,null,null);
         bar.setExits(null, null, asilo, null,null,null);
         obrasCalle.setExits(null, asilo, parque,null,null,kiosco);
        instituto.setExits(asilo, parque, null, null,null,null);
       hospital.setExits(asilo, null, null, null,salaUrgencias,null);
      parque.setExits(obrasCalle,null,cleseYogaAlAireLibre,instituto,null,null);
      cleseYogaAlAireLibre.setExits(parque,null,null,null,null,null);
      salaUrgencias.setExits(null,null,null,null,null,null);
       kiosco.setExits(null,null,null,null,null,null);
        currentRoom =asilo;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("buenos dias que lo pases bien simulando ser un viejo un poco verde ");
        System.out.println("este simulador es una pequeña version de lo que hacen los viejos verdes en su dia a dia.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("te deja tu familia que se va de viaje y tu vas  " + currentRoom.getDescription());
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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
            currentRoom = nextRoom;
            System.out.println("por fin llegue  " + currentRoom.getDescription());
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private void printLocationInfo(){
         System.out.println(currentRoom.getExitString());
        System.out.println();
        
    }
}
