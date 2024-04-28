import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class GameFunctionsTest {
    private GameFunctions gameFunctions;
    private LinkedList<String> questions;
    private LinkedList<String> answers;

    public GameFunctionsTest() {
        gameFunctions = new GameFunctions();

        questions = new LinkedList<>();
        questions.add("What artist sold a balloon dog for $58.4 million?");
        questions.add("Who created the Angel of the North?");
        questions.add("Which film did Leonardo diCaprio win an oscar for?");
        questions.add("What sport is played on a diamond pitch?");

        answers = new LinkedList<>();
        answers.add("Charlie");
        answers.add("Antony Gormley");
        answers.add("The Revenant");
        answers.add("Basketball");
    }

    @Test
    public void TestPickQuestionAndAnswer() {
        LinkedList<String> qna = gameFunctions.pickQuestionAndAnswer(questions, answers, 2);
        assertEquals("Which film did Leonardo diCaprio win an oscar for?", qna.get(0));
        assertEquals("The Revenant", qna.get(1));
    }

    @Test
    public void TestPickInvalidQuestionAndAnswer() {
        LinkedList<String> qna = gameFunctions.pickQuestionAndAnswer(questions, answers, -2);
        assertNull(qna);
    }

    @Test
    public void TestRollDice() {
        int randomNumber = gameFunctions.rollDice(5, 10);
        assertTrue(randomNumber > 5 && randomNumber < 10);
    }

    @Test
    public void TestRollDiceInvalid() {
        assertEquals(-1, gameFunctions.rollDice(15, 10));
    }

    @Test
    public void TestCheckAnswerTrue() {
        assertTrue(gameFunctions.checkAnswers("shawshank redemption", "Shawshank Redemption"));
    }

    @Test
    public void TestCheckAnswerFalse() {
        assertFalse(gameFunctions.checkAnswers("shawshank", "Shawshank Redemption"));
    }

    @Test
    public void removeQuestionAndAnswer() {
        LinkedList<String> qna = gameFunctions.pickQuestionAndAnswer(questions, answers, 2);
        gameFunctions.removeQuestionAndAnswer(questions, answers, 2);
        // Both will not be same after removing data
        assertNotEquals(questions.get(2), qna.get(0));
    }

    @Test
    public void removeQuestionAndAnswerInvalid() {
        LinkedList<String> qna = gameFunctions.pickQuestionAndAnswer(questions, answers, 2);
        gameFunctions.removeQuestionAndAnswer(questions, answers, 99);
        // Both will be same for invalid data
        assertEquals(questions.get(2), qna.get(0));
    }
}
