import java.util.Random;
public class TransportRoom extends Room {
	
	private Random random = new Random();
	
	public TransportRoom(String description, int weight, String itemName) {
		super(description, weight, itemName);
		// TODO Auto-generated constructor stub
	}
	
	
/*
* Choose a random room.
* @return A random room. */
    
    
    public boolean isTransporterRoom() {
		// TODO Auto-generated method stub
		return true;
	}

    
    
    public String getRandomDirection()
    {
    	int i = random.nextInt(3);
    	String direction = null;
    	switch(i)
    	{
    	case 0:
    		direction = "north";
    		break;
    		
    	case 1:
    		direction = "south";
    		break;
    		
    	case 2:
    		direction = "east";
    		break;
    		
    	case 3:
    		direction = "west";
    		break;
    		  	
    	}
    	
    	return direction;
    	
    }
}
