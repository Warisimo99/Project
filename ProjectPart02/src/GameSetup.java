import java.util.LinkedList;
import java.util.Scanner;

public class GameSetup {

	private String playerName;
	private int totalLives;
	private int maxQuestions;

	public GameSetup(String name, int lives, int numQuestions) {
		this.playerName = name;
		if (lives > 0) {
			this.totalLives = lives;
		} else {
			// Default values set for
			// invalid total lives
			this.totalLives = 2;
		}

		if (numQuestions > 0) {
			this.maxQuestions = numQuestions;
		} else {
			// Default values set for
			// invalid maximum questions
			this.maxQuestions = 5;
		}
	}

	void startGame() {
		Scanner keyBoard = new Scanner(System.in);

		GameFunctions gameFunctions = new GameFunctions();

		LinkedList<String> csvFileNames = loadFileNames();

		Read read = new Read(csvFileNames);

		LinkedList<LinkedList<String>> questions = new LinkedList<LinkedList<String>>();
		LinkedList<LinkedList<String>> answers = new LinkedList<LinkedList<String>>();

		questions = read.readQuestionsFromMultipleCSVs();
		answers = read.readAnswersFromMultipleCSVs();

		// create the game here
		int liveLost = 0;
		int correctAnswers = 0;

		while (liveLost < totalLives && correctAnswers < maxQuestions) {
			System.out.print("Type \"Roll\" to roll a dice : ");
			String roll = keyBoard.nextLine();

			while (!roll.equalsIgnoreCase("roll")) {
				System.out.print("Invalid Input! Please try again : ");
				roll = keyBoard.nextLine();
			}

			LinkedList<String> qna = null;

			while (qna == null) {
				int randomCategory = gameFunctions.rollDice(1, 6) - 1;
				int randomQuestionNumber = gameFunctions.rollDice(1, 10) - 1;

				LinkedList<String> questionCategory = questions.get(randomCategory);
				LinkedList<String> answerCategory = answers.get(randomCategory);

				qna = gameFunctions.pickQuestionAndAnswer(questionCategory, answerCategory, randomQuestionNumber);

				if (qna != null) {
					System.out.println("Category : " + getCategoryName(randomCategory, csvFileNames));
					gameFunctions.removeQuestionAndAnswer(questionCategory, answerCategory, randomQuestionNumber);
				}
			}

			System.out.println("Question : " + qna.get(0));
			System.out.print("Write the Answer : ");
			String userInput = keyBoard.nextLine();

			boolean result = gameFunctions.checkAnswers(userInput, qna.get(1));

			if (result) {
				correctAnswers++;
				System.out.println("Correct Answer!!! Score : " + correctAnswers);
			} else {
				liveLost++;
				System.out.println("Wrong Answer!!! Remaining Lives : " + (totalLives - liveLost));
			}
		}

		if (correctAnswers == maxQuestions) {
			System.out.println("Congratulations!!! You won the game");
		} else {
			System.out.println("Sorry!!! You lost the game");
		}
	}

	public static LinkedList<String> loadFileNames() {
		LinkedList<String> csvFileNames = new LinkedList<>();

		csvFileNames.add("Questions/sport.csv");
		csvFileNames.add("Questions/science.csv");
		csvFileNames.add("Questions/movie.csv");
		csvFileNames.add("Questions/history.csv");
		csvFileNames.add("Questions/geography.csv");
		csvFileNames.add("Questions/art.csv");

		return csvFileNames;
	}

	public static String getCategoryName(int index, LinkedList<String> csvFileNames) {
		if (index >= csvFileNames.size()) {
			return null;
		}
		String category = csvFileNames.get(index);
		return category.substring(category.lastIndexOf("/") + 1,
				category.lastIndexOf("."));
	}

	@Override
	public String toString() {
		return playerName + "," + totalLives+","+maxQuestions;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public int getTotalLives() {
		return this.totalLives;
	}
	
	public int getMaxQuestions() {
		return this.maxQuestions;
	}
}
