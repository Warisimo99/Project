import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class GameSetupTest {
    private final LinkedList<String> csvFileNames;
    private final GameSetup gameSetup, gameSetupInvalid;

    public GameSetupTest() {
        csvFileNames = GameSetup.loadFileNames();
        gameSetup = new GameSetup("Albert", 5, 12);
        gameSetupInvalid = new GameSetup("Albert", -6, -17);
    }

    @Test
    public void TestGameSetupInstanceData() {
        assertEquals("Albert,5,12", gameSetup.toString());
    }

    @Test
    public void TestGameSetupInvalidInstanceData() {
        assertEquals("Albert,2,5", gameSetupInvalid.toString());
    }

    @Test
    public void TestCSVFilesCount() {
        assertEquals(6, csvFileNames.size());
    }

    @Test
    public void TestCSVFilesName() {
        assertEquals("Questions/movie.csv", csvFileNames.get(2));
    }

    @Test
    public void TestCategoryName() {
        assertEquals("history", GameSetup.getCategoryName(3, csvFileNames));
    }

    @Test
    public void TestInvalidCategoryName() {
        assertEquals(null, GameSetup.getCategoryName(99, csvFileNames));
    }
}
