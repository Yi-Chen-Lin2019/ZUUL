import java.util.EmptyStackException;

public class GameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		//game.play();
		try{
			game.play();
		}catch(Exception e) {
			System.out.println("Try again");
			game.play();
		}
		
		
		
		
	}

}
