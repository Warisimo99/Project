import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class ReadTest {
    private static final String READ_TEST = "TestCSVFiles/ReadTest.csv";
    private static final String READ_TEST_BLANK = "TestCSVFiles/ReadTestBlank.csv";
    private final Read readSingleFile;
    private final Read readSingleFileBlank;
    private final Read readMultipleFile;
    private final Read readMultipleFileMissing;

    public ReadTest() {
        readSingleFile = new Read(READ_TEST);
        readSingleFileBlank = new Read(READ_TEST_BLANK);

        LinkedList<String> csvFileNames = new LinkedList<>();
        csvFileNames.add(READ_TEST);
        csvFileNames.add("TestCSVFiles/ReadTest2.csv");
        readMultipleFile = new Read(csvFileNames);

        LinkedList<String> csvMissingFileNames = new LinkedList<>();
        csvMissingFileNames.add("MissingFile.csv");
        csvMissingFileNames.add("MissingFile2.csv");
        readMultipleFileMissing = new Read(csvMissingFileNames);
    }

    @Test
    public void TestReadAllQuestionsFromCSV() {
        LinkedList<String> questions = new LinkedList<>();
        questions.add("What artist sold a balloon dog for $58.4 million?");
        questions.add("Who created the Angel of the North?");
        assertEquals(questions,readSingleFile.readAllQuestionsFromCSV());
    }

    @Test
    public void TestReadAllQuestionsBlankFromCSV() {
        assertEquals(new LinkedList<>(),readSingleFileBlank.readAllQuestionsFromCSV());
    }

    @Test
    public void TestReadAllAnswersFromCSV() {
        LinkedList<String> answers = new LinkedList<>();
        answers.add("Charlie");
        answers.add("Antony Gormley");
        assertEquals(answers,readSingleFile.readAllAnswersFromCSV());
    }

    @Test
    public void TestReadAllAnswersBlankFromCSV() {
        assertEquals(new LinkedList<>(),readSingleFileBlank.readAllAnswersFromCSV());
    }

    @Test
    public void TestReadQuestionsFromMultipleCSVs() {
        LinkedList<String> questions1 = new LinkedList<>();
        questions1.add("What artist sold a balloon dog for $58.4 million?");
        questions1.add("Who created the Angel of the North?");

        LinkedList<String> questions2 = new LinkedList<>();
        questions2.add("Which film did Leonardo diCaprio win an oscar for?");
        questions2.add("What sport is played on a diamond pitch?");

        LinkedList<LinkedList<String>> questions = new LinkedList<>();
        questions.add(questions1);
        questions.add(questions2);
        assertEquals(questions,readMultipleFile.readQuestionsFromMultipleCSVs());
    }

    @Test
    public void TestReadAnswersFromMultipleCSVs() {
        LinkedList<String> answers1 = new LinkedList<>();
        answers1.add("Charlie");
        answers1.add("Antony Gormley");

        LinkedList<String> answers2 = new LinkedList<>();
        answers2.add("The Revenant");
        answers2.add("Basketball");

        LinkedList<LinkedList<String>> answers = new LinkedList<>();
        answers.add(answers1);
        answers.add(answers2);
        assertEquals(answers,readMultipleFile.readAnswersFromMultipleCSVs());
    }

    @Test
    public void TestReadQuestionsFromMultipleMissingCSVs() {
        LinkedList<LinkedList<String>> blank = new LinkedList<>();
        blank.add(new LinkedList<>());
        blank.add(new LinkedList<>());
        assertEquals(blank, readMultipleFileMissing.readQuestionsFromMultipleCSVs());
    }

    @Test
    public void TestReadAnswersFromMultipleMissingCSVs() {
        LinkedList<LinkedList<String>> blank = new LinkedList<>();
        blank.add(new LinkedList<>());
        blank.add(new LinkedList<>());
        assertEquals(blank, readMultipleFileMissing.readAnswersFromMultipleCSVs());
    }

    @Test
    public void TestReadQuestionDataByColumnIndex() {
        LinkedList<String> expectedList = new LinkedList<>();
        expectedList.add("What artist sold a balloon dog for $58.4 million?");
        expectedList.add("Who created the Angel of the North?");

        LinkedList<String> actualList = new LinkedList<>();
        Read.readDataByColumnIndex(1, READ_TEST, actualList);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void TestReadAnswerDataByColumnIndex() {
        LinkedList<String> expectedList = new LinkedList<>();
        expectedList.add("Charlie");
        expectedList.add("Antony Gormley");

        LinkedList<String> actualList = new LinkedList<>();
        Read.readDataByColumnIndex(2, READ_TEST, actualList);
        assertEquals(expectedList, actualList);
    }

    @Test
    public void TestReadLastLine() {
        assertEquals("2,Who created the Angel of the North?,Antony Gormley", Read.readLastLine(READ_TEST));
    }

    @Test
    public void TestReadLastLineBlank() {
        assertEquals("", Read.readLastLine(READ_TEST_BLANK));
    }

    @Test
    public void TestGetLastRowID() {
        assertEquals(2, Read.getLastRowID(READ_TEST));
    }

    @Test
    public void TestGetLastRowIDBlank() {
        assertEquals(0, Read.getLastRowID(READ_TEST_BLANK));
    }
}
