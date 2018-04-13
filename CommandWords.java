import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{

    // a constant array that holds all valid command words
    private HashMap<String,CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        validCommands.put("go", CommandWord.GO);
        validCommands.put("help", CommandWord.HELP);
        validCommands.put("quit", CommandWord.QUIT);
        validCommands.put("look", CommandWord.LOOK);
        validCommands.put("eat", CommandWord.EAT);
        validCommands.put("back", CommandWord.BACK);
        validCommands.put("take", CommandWord.TAKE);
        validCommands.put("drop", CommandWord.DROP);
        validCommands.put("items", CommandWord.ITEMS);
        validCommands.put("resistencia", CommandWord.RESISTENCIA);
        validCommands.put("beber", CommandWord.BEBER);
        validCommands.put("unknown", CommandWord.UNKNOWN);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
       
        // if we get here, the string was not found in the commands
        return validCommands.containsKey(aString);
    }

    /**
     * Imprime por pantalla todos los comandos válidos
     */
    public String getCommandList()
    {
        String a =" ";
        a += validCommands.keySet();
        return   a;
    }

   
}
