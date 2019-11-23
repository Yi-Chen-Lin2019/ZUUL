import java.util.*;


public class Room 
{
    private HashMap<String, Room> exits;
    private String description;
    private ArrayList<Item> items = new ArrayList<Item>();
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, int weight, String itemName) 
    {
        this.description = description;
        exits = new HashMap<>();
        addItems(weight, itemName);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public void addItems(int weight, String itemName)
    {
        items.add(new Item(weight, itemName));
    }
    
    public void addItems(Item item)
    {
        items.add(item);
    }
    
    public void removeItems(Item item)
    {
        items.remove(item);
    }
    
    /**
     * revise part
     * 
     */
    public Room getExit(String direction)
    {
        /**
        Room returnRoom = null;
        switch(direction){
            case "north":
                returnRoom = exits.get("north");
                break;
            case "east":
                returnRoom = exits.get("east");
                break;
            case "south":
                returnRoom = exits.get("south");
                break;
            case "west":
                returnRoom = exits.get("west");
                break;
        }
        return returnRoom;
        */
       
        return exits.get(direction);
    }
    
    public String getExitString()
    {
        String result = "Exits: ";
        Set <String> keys = exits.keySet();
        for(String exit : keys){
            result += " " + exit;
        }
        return result;
    }
    
    public String getLongDescription()
    {
        String itemDescription = "";
        for(Item i : items){
            itemDescription += i.toString() + "\n";
        }
        return "You are "+ description + ".\n"+ getExitString() +
                ".\n" + itemDescription;
    }
    
    public void setExit(String direction, Room neighbor) 
    {
        if(neighbor != null){
            exits.put(direction, neighbor);
        }
    }
    
    public ArrayList<Item> getItems()
    {
        return items;
    }
    
    public Item getItem(String name)
    {
        Item item = null;
        for(Item i : items){
            if(i.getDescription().equals(name)){
                item = i;
            }
        }
        return item;
    }

	public boolean isTransporterRoom() {
		// TODO Auto-generated method stub
		return false;
	}

	protected HashMap<String, Room> getExits()
	{
		return exits;
	}
    
    
   
}
