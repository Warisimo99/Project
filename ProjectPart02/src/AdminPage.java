import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTextField questionTextField;
	private JTextField answerTextField;
	private JTextField tableNameTextField;
	private JButton btnAddQuestion;
	private JLabel lblAddMultipleQuestion;
	private JLabel lblEnterTheTable_1;
	private JTextField multipleTableNameTextField;
	private JFileChooser fileChooser;
	private JLabel lblEnterTheTable_2;
	private JButton btnSelectCSVFile;
	private JButton btnAddMultipleQuestion;
	private JLabel lblRemoveQuestion;
	private JLabel lblEnterRowId;
	private JTextField textFieldRowID;
	private JLabel lblEnterTheTable_3;
	private JTextField removeTableNameTextField;
	private JButton btnRemoveQuestion;
	private String filePath;
	private JButton btnBack;

	/**
	 * Create the frame.
	 */
	public AdminPage(GameSetup game) {
		setTitle("Trivial Pursuit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 803);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Add Single Question");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 12, 406, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterYourQuestion = new JLabel("Enter the question:");
		lblEnterYourQuestion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterYourQuestion.setBounds(12, 56, 247, 40);
		contentPane.add(lblEnterYourQuestion);
		
		JLabel lblEnterYourAnswer = new JLabel("Enter the answer:");
		lblEnterYourAnswer.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterYourAnswer.setBounds(12, 115, 247, 40);
		contentPane.add(lblEnterYourAnswer);
		
		JLabel lblEnterTheTable = new JLabel("Enter the table Name:");
		lblEnterTheTable.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterTheTable.setBounds(12, 170, 247, 40);
		contentPane.add(lblEnterTheTable);
		
		questionTextField = new JTextField();
		questionTextField.setFont(new Font("Dialog", Font.BOLD, 16));
		questionTextField.setBounds(260, 57, 368, 40);
		contentPane.add(questionTextField);
		questionTextField.setColumns(10);
		
		answerTextField = new JTextField();
		answerTextField.setFont(new Font("Dialog", Font.BOLD, 16));
		answerTextField.setColumns(10);
		answerTextField.setBounds(260, 115, 368, 40);
		contentPane.add(answerTextField);
		
		tableNameTextField = new JTextField();
		tableNameTextField.setFont(new Font("Dialog", Font.BOLD, 16));
		tableNameTextField.setColumns(10);
		tableNameTextField.setBounds(260, 170, 368, 40);
		contentPane.add(tableNameTextField);
		
		btnAddQuestion = new JButton("Add Question");
		btnAddQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String question = questionTextField.getText();
				String answer = answerTextField.getText();
				String tableName = tableNameTextField.getText();
				
				if (question.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Enter the question.");
				} else if (answer.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Enter the answer.");
				} else if (tableName.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Enter the table name.");
				} else {
					boolean response = Write.addQandASingleToTable(question, answer, tableName);
					
					if (response) {
						JOptionPane.showMessageDialog(null, "Data Added Successfully.");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Table Name!!!");
					}
				}
			}
		});
		btnAddQuestion.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAddQuestion.setBounds(260, 232, 155, 40);
		contentPane.add(btnAddQuestion);
		
		lblAddMultipleQuestion = new JLabel("Add Multiple Question");
		lblAddMultipleQuestion.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAddMultipleQuestion.setBounds(12, 305, 406, 34);
		contentPane.add(lblAddMultipleQuestion);
		
		
		lblEnterTheTable_1 = new JLabel("Enter the table Name:");
		lblEnterTheTable_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterTheTable_1.setBounds(12, 417, 247, 40);
		contentPane.add(lblEnterTheTable_1);
		
		multipleTableNameTextField = new JTextField();
		multipleTableNameTextField.setFont(new Font("Dialog", Font.BOLD, 16));
		multipleTableNameTextField.setColumns(10);
		multipleTableNameTextField.setBounds(260, 418, 368, 40);
		contentPane.add(multipleTableNameTextField);
		
		lblEnterTheTable_2 = new JLabel("Select csv file:");
		lblEnterTheTable_2.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterTheTable_2.setBounds(12, 355, 247, 40);
		contentPane.add(lblEnterTheTable_2);
		
		btnSelectCSVFile = new JButton("Select File");
		btnSelectCSVFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser();
				int index = filechooser.showSaveDialog(null);
				
				if (index == JFileChooser.APPROVE_OPTION) {
					filePath = filechooser.getSelectedFile().getAbsolutePath();
					if (filePath.endsWith(".csv")) {
						lblEnterTheTable_2.setText(filePath);
					} else {
						filePath = "";
						JOptionPane.showMessageDialog(null, "Please Select a csv file.");
					}
					
				}
			}
		});
		btnSelectCSVFile.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSelectCSVFile.setBounds(260, 356, 155, 40);
		contentPane.add(btnSelectCSVFile);
		
		btnAddMultipleQuestion = new JButton("Add Multiple Question");
		btnAddMultipleQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableName = multipleTableNameTextField.getText();
				
				if (filePath.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please select a csv file.");
				} else if (tableName.isBlank()) {
					JOptionPane.showMessageDialog(null, "Please Enter the table name.");
				} else {
					Read read = new Read(filePath);
					LinkedList<String> questions = read.readAllQuestionsFromCSV();
					LinkedList<String> answers = read.readAllAnswersFromCSV();
					
					boolean response = Write.addQandAMultipleToTable(questions, answers, tableName);
					
					if (response) {
						JOptionPane.showMessageDialog(null, "Data added Successfully.");
					} else {
						JOptionPane.showMessageDialog(null, "Invalid Table Name or Invalid Data Format!!!");
					}
				}
			}
		});
		btnAddMultipleQuestion.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAddMultipleQuestion.setBounds(260, 477, 237, 40);
		contentPane.add(btnAddMultipleQuestion);
		
		lblRemoveQuestion = new JLabel("Remove Question");
		lblRemoveQuestion.setFont(new Font("Dialog", Font.BOLD, 20));
		lblRemoveQuestion.setBounds(12, 549, 406, 34);
		contentPane.add(lblRemoveQuestion);
		
		lblEnterRowId = new JLabel("Enter row ID:");
		lblEnterRowId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterRowId.setBounds(12, 590, 247, 40);
		contentPane.add(lblEnterRowId);
		
		textFieldRowID = new JTextField();
		textFieldRowID.setFont(new Font("Dialog", Font.BOLD, 16));
		textFieldRowID.setColumns(10);
		textFieldRowID.setBounds(260, 591, 368, 40);
		contentPane.add(textFieldRowID);
		
		lblEnterTheTable_3 = new JLabel("Enter the table Name:");
		lblEnterTheTable_3.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterTheTable_3.setBounds(12, 642, 247, 40);
		contentPane.add(lblEnterTheTable_3);
		
		removeTableNameTextField = new JTextField();
		removeTableNameTextField.setFont(new Font("Dialog", Font.BOLD, 16));
		removeTableNameTextField.setColumns(10);
		removeTableNameTextField.setBounds(260, 643, 368, 40);
		contentPane.add(removeTableNameTextField);
		
		btnRemoveQuestion = new JButton("Remove Question");
		btnRemoveQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int rowID = Integer.parseInt(textFieldRowID.getText());
					if (rowID <= 0) {
						throw new NumberFormatException();
					}
					
					String tableName = removeTableNameTextField.getText();
					
					if (tableName.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please Enter the table name.");
					} else {
						boolean response = Delete.deleteRowFromTable(rowID, tableName);
						
						if (response) {
							JOptionPane.showMessageDialog(null, "Data delete Successfully.");
						} else {
							JOptionPane.showMessageDialog(null, "Invalid Table Name!!!");
						}
					}
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid row ID");
				}
			}
		});
		btnRemoveQuestion.setFont(new Font("Dialog", Font.BOLD, 16));
		btnRemoveQuestion.setBounds(260, 697, 237, 40);
		contentPane.add(btnRemoveQuestion);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new MenuPage(game).setVisible(true);
			}
		});
		btnBack.setBounds(12, 736, 75, 25);
		contentPane.add(btnBack);
	}

}
