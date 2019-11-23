import java.util.ArrayList;
import java.util.Random;

public class RoomOrganizer {
	
	private Room outside, theater, pub, lab, office, transport1;
    private Room currentRoom;
    private Random random = new Random();
    private ArrayList<Room> rooms = new ArrayList<Room>();

	
	public RoomOrganizer() {
    // create the rooms
	    outside = new Room("outside the main entrance of the university", 5, "grass");
	    theater = new Room("in a lecture theater", 500, "microphone");
	    pub = new Room("in the campus pub", 200, "beer");
	    lab = new Room("in a computing lab", 1000, "equipment");
	    office = new Room("in the computing admin office", 1000, "desk");
	    transport1 = new TransportRoom("in the transport room", 10, "flash");
	    
	    rooms.add(outside);
	    rooms.add(theater);
	    rooms.add(pub);
	    rooms.add(lab);
	    rooms.add(office);
	    
	    
	    
	    currentRoom = outside;   // start game outside
	    setExit();
	    setItem();
	}
    
	private void setExit() {
    // Initialize room exits
	    outside.setExit("east", theater); 
	    outside.setExit("south", lab);
	    outside.setExit("west", pub);
	    
	    theater.setExit("west", outside);
	    
	    pub.setExit("east", outside);
	    
	    lab.setExit("north", outside);
	    lab.setExit("east", office);
	    
	    office.setExit("west", lab);
	    office.setExit("south", transport1);
	            
	    transport1.setExit(((TransportRoom) transport1).getRandomDirection(), setRandomRoom());

	}
    
    private void setItem() {
	    outside.addItems(50, "flower");
	    theater.addItems(20000, "chair");
	    pub.addItems(100, "wine");
	    lab.addItems(100000, "toxic chemical");
	    lab.addItems(600 , "cookie");
	    office.addItems(5, "pen");
	    office.addItems(3, "paper");
	}
    
    private Room setRandomRoom()
    {
    	return rooms.get(random.nextInt(rooms.size()));    	
    	
    }
    
    public Room getCurrent()
    {
    	return currentRoom;
    }
    
    public void setCurrent(Room newRoom)
    {
    	
    	this.currentRoom = newRoom;
    }
    
    public void getCurrentDescrip()
    {
    	System.out.println(currentRoom.getLongDescription());
    }
}
