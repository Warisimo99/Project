import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class WriteTest {
    private static final String WRITE_TEST_FILE = "TestCSVFiles/WriteTest.csv";
    private static final String WRITE_TEST_FILE2 = "TestCSVFiles/WriteTest2.csv";

    @Test
    public void TestAddQuestionAndAnswerSingleToCSV() {
        assertEquals(Write.addQandASingleToCSV("How many lines are there in a sonnet?",
                        "14", WRITE_TEST_FILE), Read.getLastRowID(WRITE_TEST_FILE));
    }

    @Test
    public void TestAddBlankQuestionAndAnswerSingleToCSV() {
        assertEquals(-1, Write.addQandASingleToCSV("", "Something", WRITE_TEST_FILE));
    }

    @Test
    public void TestAddQuestionAndBlankAnswerSingleToCSV() {
        assertEquals(-1, Write.addQandASingleToCSV("Something", "", WRITE_TEST_FILE));
    }

    @Test
    public void TestAddQuestionAndAnswerMultipleToCSV() {
        LinkedList<String> questions = new LinkedList<>();
        questions.add("What artist sold a balloon dog for $58.4 million?");
        questions.add("Who created the Angel of the North?");

        LinkedList<String> answers = new LinkedList<>();
        answers.add("Charlie");
        answers.add("Antony Gormley");

        int actualLastRow = Write.addQandAMultipleToCSV(questions, answers, WRITE_TEST_FILE);
        int expectedLastRow = Read.getLastRowID(WRITE_TEST_FILE);

        assertEquals(expectedLastRow, actualLastRow);
    }

    @Test
    public void TestAddBlankQuestionAndBlankAnswerMultipleToCSV() {
        assertEquals(-1, Write.addQandAMultipleToCSV(new LinkedList<>(), new LinkedList<>(), WRITE_TEST_FILE));
    }

    @Test
    public void TestWriteToFile() {
        String data = "This is a test data";
        Write.writeToFile(data + "\n", WRITE_TEST_FILE2);
        assertEquals(data, Read.readLastLine(WRITE_TEST_FILE2));
    }

    @Test
    public void TestWriteBlankToFile() {
        // Getting the last line
        String lastLine = Read.readLastLine(WRITE_TEST_FILE2);
        // Writing blank data
        Write.writeToFile("", WRITE_TEST_FILE2);
        // Last Line remains same
        assertEquals(lastLine, Read.readLastLine(WRITE_TEST_FILE2));
    }
}
