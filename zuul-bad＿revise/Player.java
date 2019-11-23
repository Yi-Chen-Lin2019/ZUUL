import java.util.*;
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private String name;
    private ArrayList<Item> bag = new ArrayList<Item>();
    private int maxWeight = 600;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name, Room currentRoom)
    {
        // initialise instance variables
        this.name = name;
        this.currentRoom = currentRoom;
    }
    
    public void setRoom(Room room)
    {
        currentRoom = room;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void collect(Item item)
    {
        bag.add(item);
        if(!isAllowed()){
            decollect(item);
            System.out.println("It's to heavy");
        }else{
            System.out.println("It's in your bag now");
        }
    }
    
    public void decollect(Item item)
    {
        bag.remove(item);
    }
    
    public Item getItem(String name)
    {
        Item item = null;
        for(Item i : bag){
            if(i.getDescription().equals(name)){
                item = i;
            }
        }
        return item;
    }
    
    public void showBag()
    {
        String result = "";
        for(Item i : bag){
            result += i.getDescription() + "\n";
        }
        System.out.println(" ");
        System.out.println("~~Here is your bag: ~~" );
        System.out.println(""+result);
        System.out.println(" ");
        System.out.println("Total weight: "+ getTotalWeight());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    public boolean isAllowed()
    {
        if(getTotalWeight() > maxWeight){
            return false;
        }
        return true;
    }
    
    public int getTotalWeight()
    {
        int weight = 0;
        for(Item i : bag){
            weight += i.getWeight();
        }
        return weight;
    }
    
    public void setWeight(int plus)
    {
        maxWeight += plus;
    }
    
    public int getMaxWeight()
    {
        return maxWeight;
    }
    
    public ArrayList<Item> getItems()
    {
        ArrayList<Item> result = null;
        if(bag.size()!=0){
            result = bag;
        }
        return result;
    }
}
