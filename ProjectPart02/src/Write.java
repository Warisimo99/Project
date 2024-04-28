import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;


public class Write {
	public static int addQandASingleToCSV(String question, String answer, String CSVPath) {
		if (question.isBlank() || answer.isBlank()) {
			return -1;
		}

		int rowID = Read.getLastRowID(CSVPath) + 1;

		writeToFile((rowID + "," + question + "," + answer + "\n"), CSVPath);

		return rowID;
	}

	public static int addQandAMultipleToCSV(LinkedList<String> questions, LinkedList<String> answers, String CSVPath) {
		if (questions.size() != answers.size() || questions.isEmpty()) {
			return -1;
		}

		int lastRowID = Read.getLastRowID(CSVPath);

		String line = "";
		for (int i = 0; i < questions.size(); i++) {
			String question = questions.get(i);
			String answer = answers.get(i);

			if (!question.isBlank() && !answer.isBlank()) {
				lastRowID++;
				line += (lastRowID + "," + question + "," + answer + "\n");
			}
		}
		writeToFile(line, CSVPath);

		return lastRowID;
	}

	/**
	 * This method writes the given data
	 * to the file at the given path. This
	 * reduces duplicate code.
	 *
	 * @param data		Given Data
	 * @param CSVPath	Given CSV File Path
	 */
	public static void writeToFile(String data, String CSVPath) {
		if (data.isBlank() || data.equals("\n")) {
			return;
		}
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(CSVPath, true));
			writer.append(data);
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static boolean addQandASingleToTable(String question, String answer, String tableName) {
		try {
			Connection connection = DBConnector.getConnection();
			String sql = "INSERT INTO `" + tableName + "` (`question`, `answer`) values ('" + question + "', '" + answer + "')";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.close();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean addQandAMultipleToTable(LinkedList<String> questions, LinkedList<String> answers, String tableName) {
		if (questions.size() != answers.size() || questions.isEmpty() || tableName.isBlank()) {
			return false;
		}
		
		try {
			Connection connection = DBConnector.getConnection();
			
			for (int i = 0; i < questions.size(); i++) {
				String sql = "INSERT INTO `" + tableName + "` (`question`, `answer`) values ('" + questions.get(i) + "', '" + answers.get(i) + "')";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.execute();
			}
			connection.close();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
