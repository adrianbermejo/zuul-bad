
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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player jugador2;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        
        parser = new Parser();
        jugador2 =new Player(createRooms());
       
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room asilo,bar,instituto,obrasCalle,cleseYogaAlAireLibre,hospital,parque,salaUrgencias,kiosco;

        asilo  = new Room(" el asilo: aqui solo hay viejas esto es aburrido demos una vuelta");
        bar = new Room(" el bar: que pena no hay partida volvere luego");
        obrasCalle = new Room(" las obras en la calle: �que estara metiendo que ponen unos tubos tan grandes?fijo que lo ponen mal y tienen que volver a levantar");
        cleseYogaAlAireLibre = new Room(" la cleses de Yoga Al Aire Libre: que de mojucas de buen ver hay hoy voy a hacerme el desmayado a ver si me hace el bocaaboca");
        hospital = new Room(" el hospital: como que me duele un poco la cadera voy a urgencias a que me hagan una radiografia");
        instituto = new Room(" el istituto: que de chavalines jugando al futbol y los que no detras de las chicas que recuerdos ");
        parque= new Room("  el parque: voy a dar de comer a las palomas y a ver si hay alguna actividad");
        salaUrgencias= new Room("  urgencias: se le levanto a una ni�a la falda se le vio todo y me dio un infarto.... valio la pena");
        kiosco= new Room(" el  kiosco: me qiede sin tabaco voy hasta el kiosco lo compro hablo con el dependiente que es hijo de Pepe el del estanco");

        asilo.setExit("north",bar);
        asilo.setExit("east",hospital);
        asilo.setExit("south",instituto);
        asilo.setExit("west",hospital);
        bar.setExit("south",asilo);
        obrasCalle.setExit("east",asilo);
        obrasCalle.setExit("south",parque);
        obrasCalle.setExit("northEast",kiosco);
        instituto.setExit("north",asilo);
        instituto.setExit("east",parque);
        hospital.setExit("north",asilo); 
        hospital.setExit("southEast",salaUrgencias);
        parque.setExit("north",obrasCalle);
        parque.setExit("south",cleseYogaAlAireLibre);
        parque.setExit("west",instituto);
        cleseYogaAlAireLibre.setExit("north",parque);
        //A�adir  objetos
        cleseYogaAlAireLibre.addItem("braguitas","braguitas usadas de un coleguala", 200,true);
        bar.addItem("cafelito", "que cafes mas ricos hace paco",200,false);
        bar.addItem("churro","el churro eata un poco frio pero se deja comer", 100,true);
         // start game outside
        return asilo;
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
        System.out.println("este simulador es una peque�a version de lo que hacen los viejos verdes en su dia a dia.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("te deja tu familia que se va de viaje y tu vas  " + jugador2.current().getDescription());
        jugador2.look();
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
            jugador2.goRoom(command);
        }

        else if (commandWord.equals("look")) {
            jugador2.look();
        }
        else if (commandWord.equals("eat")) {
            jugador2.eat();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")){
            jugador2.back();
        }
        else if (commandWord.equals("take")) {
            jugador2.take();
        }
        else if (commandWord.equals("items")){
            jugador2.items();
        }
        else if (commandWord.equals("drop")){
            jugador2.drop(command);
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
        System.out.println("Your command words are:");
        System.out.println(parser.getCommandList());
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

   

    
}
