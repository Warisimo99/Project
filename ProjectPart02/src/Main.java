import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner keyBoard = new Scanner(System.in);
		System.out.print("Enter player name : ");
		String playerName = keyBoard.nextLine();

		System.out.print("Enter number of player lives : ");
		int lives = Integer.parseInt(keyBoard.nextLine());

		System.out.print("Enter number of maximum correct questions : ");
		int numQuestions =  Integer.parseInt(keyBoard.nextLine());

		GameSetup gs = new GameSetup(playerName, lives, numQuestions);
		gs.startGame();
		
	}

}
