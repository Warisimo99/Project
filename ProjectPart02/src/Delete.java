import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
	public static int deleteRowFromCSV(int rowID, String csvPath) {
		if (rowID <= 0) {
			return -1;
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(csvPath));
			String line = "";
			String targetText = reader.readLine() + "\n";
			while ((line = reader.readLine()) != null) {
				String[] elements = line.split(",");
				if (Integer.parseInt(elements[0]) != rowID) {
					targetText += line + "\n";
				}
			}
			reader.close();

			BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath));
			System.out.println(targetText);
			writer.write(targetText);
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return rowID;
	}
	
	public static boolean deleteRowFromTable(int rowID, String tableName) {
		if (rowID <= 0 || tableName.isBlank()) {
			return false;
		}
		
		try {
			Connection connection = DBConnector.getConnection();
			
			String sql = "DELETE FROM `" + tableName + "` WHERE id = " + rowID;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.close();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
