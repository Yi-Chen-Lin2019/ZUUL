import java.util.*;
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
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    //private Room currentRoom;
    //private Room previousRoom;
    private Stack<Room> stacks = new Stack<Room>();
    private Player player;
    private RoomOrganizer roomOrganizer = new RoomOrganizer();
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        createPlayer();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
    	stacks.push(roomOrganizer.getCurrent());        
    }
    
    private void createPlayer()
    {
        player = new Player("YCLin", roomOrganizer.getCurrent());
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
        System.out.println("Hello " + player.getName() + " <3");
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(roomOrganizer.getCurrent().getLongDescription());
        
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
        else if (commandWord.equals("back")){
            back();
        }
        else if (commandWord.equals("take")){
            take(command);
        }
        else if (commandWord.equals("drop")){
            drop(command);
        }
        else if (commandWord.equals("eat")){
            eat(command);
        }
        else if (commandWord.equals("show")){
            showBag();
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
        System.out.println(parser.showCommands());
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
        Room nextRoom = roomOrganizer.getCurrent().getExit(direction);
        if(nextRoom == null) {
            System.out.println("There is no door!");
            System.out.println(roomOrganizer.getCurrent().getLongDescription());
            roomOrganizer.setCurrent(roomOrganizer.getCurrent());
        }if(roomOrganizer.getCurrent() instanceof TransportRoom) { 
        	roomOrganizer.setCurrent(nextRoom);
        	System.out.println(roomOrganizer.getCurrent().getLongDescription());
        }
        else{
        	roomOrganizer.setCurrent(nextRoom);
        	System.out.println(roomOrganizer.getCurrent().getLongDescription());
        }
                
               
        stacks.push(nextRoom);
        System.out.println(stacks);
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
    
    private void back()
    {
        if(stacks.size() != 0 ){
        	stacks.pop();
            Room backRoom = stacks.pop();
            roomOrganizer.setCurrent(backRoom);
            roomOrganizer.getCurrentDescrip();            
        }else{            
            printHelp();
        }
    }
    
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }
        
        String request = command.getSecondWord();
        //request = " "+ command.getThirdWord();
        Item item = roomOrganizer.getCurrent().getItem(request);
        if(item != null){
            player.collect(item);
            roomOrganizer.getCurrent().removeItems(item);
            player.showBag();
        }else{
            System.out.println("There is no "+ request);
        }
    }
    
    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to deop...
            System.out.println("Drop what?");
            return;
        }
        
        String request = command.getSecondWord();
        //request += command.getThirdWord();
        Item item = player.getItem(request);
        if(item != null){
            player.decollect(item);
            roomOrganizer.getCurrent().addItems(item);
            System.out.println(request + " on the ground");
            player.showBag();
        }else{
            System.out.println("There is no "+ request);
        }
    }
    
    private void eat(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to eat...
            System.out.println("Eat what?");
            return;
        }
        
        String request = command.getSecondWord();
        if(request.equals("cookie")){
            Item item = roomOrganizer.getCurrent().getItem(request);
            if(item != null){
            player.setWeight(item.getWeight());
            System.out.println("You can take up to "+ 
                                player.getMaxWeight() +" now.");
            }else{
                System.out.println("There is no cookie!");
            }
        }else{
        System.out.println("You cannot eat this!");
        }
 
    }
    
    private void showBag()
    {
        if(player.getItems()!=null){
            player.showBag();
        }else{
            System.out.println("Take something first");
        }
    }
    
    
}
