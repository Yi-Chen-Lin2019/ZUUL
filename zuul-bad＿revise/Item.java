
/**
 * Write a description of class Item here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weight;
    private String description;

    /**
     * Constructor for objects of class Item
     */
    public Item(int weight, String description)
    {
        // initialise instance variables
        this.weight = weight;
        this.description = description;
    }
    
    public void setItem(int weight, String description)
    {
        this.weight = weight;
        this.description = description;
    }
    
    public int getWeight()
    {
        return weight;
    }
    
    public String getDescription()
    {
        return description;
    }

    public String toString()
    {
        return "Item: "+ description + " Weight: " + weight;
    }
}
