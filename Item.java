
/**
 * Write a description of class item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int itemWeigth;
    private String itemDescription;
    private String id;
    

    /**
     * Constructor for objects of class item
     */
    public Item(String id ,String description , int itemWeigth)
    {
        this.id=id;
        this.itemDescription=description;
        this.itemWeigth = itemWeigth;
        
    }
    
    public String getId(){
        return id;
    }

    public String getDescription(){
        return itemDescription;
    }
    
    public int getPeso(){
         return itemWeigth ;
    }
    
     public String informacionItem(){
         return getId() + " " +getDescription() + " " + ":" + " "+ getPeso() ;
 
      }
}