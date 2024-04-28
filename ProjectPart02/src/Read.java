import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Read {

	private String path;
	private LinkedList<String> csvFileNames;
	private String[] tableNames;

	public Read(String constructorPath) { // use this constructor when you just want to use
		this.path = constructorPath;
	}

	public Read(LinkedList<String> constructorCSVFilePaths) {
		this.csvFileNames = constructorCSVFilePaths;
	}

	public Read(String[] constructorTableNames) { // this is for the MySQL part. Can't use LinkedList as we have already
													// a constructor which has a linkedList for the csv version
		this.tableNames = constructorTableNames;
	}

	public LinkedList<String> readAllQuestionsFromCSV() {
		LinkedList<String> questions = new LinkedList<>();
		readDataByColumnIndex(1, this.path, questions);
		return questions;

	}

	public LinkedList<String> readAllAnswersFromCSV() {
		LinkedList<String> answers = new LinkedList<>();
		readDataByColumnIndex(2, this.path, answers);
		return answers;

	}

	public LinkedList<LinkedList<String>> readQuestionsFromMultipleCSVs() {
		LinkedList<LinkedList<String>> result = new LinkedList<>();
		for (String fileName : csvFileNames) {
			LinkedList<String> questions = new LinkedList<>();
			readDataByColumnIndex(1, fileName, questions);
			result.add(questions);
		}
		return result;
	}

	public LinkedList<LinkedList<String>> readAnswersFromMultipleCSVs() {
		LinkedList<LinkedList<String>> result = new LinkedList<>();
		for (String fileName : csvFileNames) {
			LinkedList<String> answers = new LinkedList<>();
			readDataByColumnIndex(2, fileName, answers);
			result.add(answers);
		}
		return result;
	}

	/**
	 * Using additional method to reduce
	 * the use of duplicate code. This
	 * method reads data from given column index.
	 *
	 * @param index			Index of the data
	 * @param path			Path of the csv file
	 * @param collection   	Collection to store data
	 */
	public static void readDataByColumnIndex(int index, String path, LinkedList<String> collection) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = "";
			// Skipping the first line
			// that has column names
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] elements = line.split(",");
				collection.add(elements[index]);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Additional method to read the
	 * last line from the given file path.
	 *
	 * @param filePath 		Given File Path
	 * @return Last line
	 */
	public static String readLastLine(String filePath) {
		String line = "";
		String lastLine = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			// Skipping the first line
			// that has column names
			reader.readLine();
			while (line != null) {
				line = reader.readLine();
				if (line != null) {
					lastLine = line;
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return lastLine;
	}

	/**
	 * Additional method to read the
	 * last row id from the given file path.
	 *
	 * @param filePath		Given File Path
	 * @return Last Row ID
	 */
	public static int getLastRowID(String filePath) {
		String lastRow = Read.readLastLine(filePath);
		if (lastRow.isBlank()) {
			return 0;
		} else {
			return Integer.parseInt(lastRow.split(",")[0]);
		}
	}
	
	public LinkedList<LinkedList<String>> readQuestionsFromMultipleTables() {
		LinkedList<LinkedList<String>> list = new LinkedList<>();
		try {
			Connection connection = DBConnector.getConnection();
			
			for (String tableName : this.tableNames) {
				String sql = "SELECT question FROM `" + tableName + "`";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				
				LinkedList<String> questions = new LinkedList<String>();
				while (result.next()) {
					questions.add(result.getString("question"));
				}
				list.add(questions);
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public LinkedList<LinkedList<String>> readAnswersFromMultipleTables() {
		LinkedList<LinkedList<String>> list = new LinkedList<>();
		try {
			Connection connection = DBConnector.getConnection();
			
			for (String tableName : this.tableNames) {
				String sql = "SELECT answer FROM `" + tableName + "`";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet result = statement.executeQuery();
				
				LinkedList<String> answers = new LinkedList<String>();
				while (result.next()) {
					answers.add(result.getString("answer"));
				}
				list.add(answers);
			}
			
			connection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
