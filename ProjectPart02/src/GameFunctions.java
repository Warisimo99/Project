import java.util.LinkedList;
import java.util.Random;

public class GameFunctions {

	Random random = new Random();

	LinkedList<String> pickQuestionAndAnswer(LinkedList<String> questions, LinkedList<String> answers, int randomNum) {
		if (randomNum < 0 || questions.size() <= randomNum || answers.size() <= randomNum) {
			return null;
		}
		LinkedList<String> qna = new LinkedList<>();
		qna.add(questions.get(randomNum));
		qna.add(answers.get(randomNum));
		return qna;
	}

	public int rollDice(int min, int max) {
		if (max < min) {
			return -1;
		}
		return random.nextInt(max - min + 1) + min;
	}

	boolean checkAnswers(String userInput, String realAnswer) {
		return userInput.equalsIgnoreCase(realAnswer);
	}

	/**
	 * This method removes the question
	 * and answer from the given index.
	 *
	 * @param questions		Questions List
	 * @param answers		Answers List
	 * @param randomNum		Random Number
	 */
	public void removeQuestionAndAnswer(LinkedList<String> questions, LinkedList<String> answers, int randomNum) {
		if (questions.size() > randomNum && answers.size() > randomNum) {
			questions.remove(randomNum);
			answers.remove(randomNum);
		}
	}

}
