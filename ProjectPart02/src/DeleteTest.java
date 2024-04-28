import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DeleteTest {
    private static final String DELETE_TEST_FILE = "TestCSVFiles/DeleteTest.csv";

    @Test
    public void TestDeleteRowFromCSV() {
        // Getting the last row ID
        int deleteRowID = Read.getLastRowID(DELETE_TEST_FILE);

        // Deleting the rowID
        Delete.deleteRowFromCSV(deleteRowID, DELETE_TEST_FILE);

        // Now the last ID won't be same
        assertNotEquals(deleteRowID, Read.getLastRowID(DELETE_TEST_FILE));
    }

    @Test
    public void TestInvalidDeleteRowFromCSV() {
        assertEquals(-1, Delete.deleteRowFromCSV(-1, DELETE_TEST_FILE));
    }
}
