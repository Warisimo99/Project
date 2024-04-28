import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class GamePage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSubmitAnswer, btnNewButton;
	
	LinkedList<String> qna = null;
	
	int liveLost = 0;
	int correctAnswers = 0;

	/**
	 * Create the frame.
	 */
	public GamePage(GameSetup game) {
		String[] tableNames = new String[] {"art", "geography", "history", "movie", "science", "sport"};
		Read read = new Read(tableNames);
		GameFunctions gameFunctions = new GameFunctions();
		
		LinkedList<LinkedList<String>> questions = read.readQuestionsFromMultipleTables();
		LinkedList<LinkedList<String>> answers = read.readAnswersFromMultipleTables();
		
		
		setTitle("Trivial Pursuit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 600);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JLabel lblPointsEarned = new JLabel("Points Earned : " + correctAnswers);
		lblPointsEarned.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPointsEarned.setBounds(27, 49, 231, 38);
		contentPane.add(lblPointsEarned);
		
		JLabel lblLives = new JLabel("Lives Remainning: " + (game.getTotalLives() - liveLost));
		lblLives.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLives.setBounds(283, 49, 231, 38);
		contentPane.add(lblLives);
		
		JLabel lblCategory = new JLabel("Category: ");
		lblCategory.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCategory.setBounds(27, 99, 487, 38);
		contentPane.add(lblCategory);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 16));
		textField.setBounds(27, 453, 487, 38);
		textField.setColumns(10);
		contentPane.add(textField);
		
		JLabel lblAnswer = new JLabel("Enter your Answer:");
		lblAnswer.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAnswer.setBounds(27, 413, 231, 38);
		contentPane.add(lblAnswer);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Dialog", Font.BOLD, 16));
		textArea.setBounds(27, 231, 487, 170);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		contentPane.add(textArea);
		
		btnSubmitAnswer = new JButton("Submit Answer");
		btnSubmitAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (qna != null) {
						String answer = textField.getText();
						
						if (answer.isBlank()) {
							JOptionPane.showMessageDialog(null, "Please Give an Answer");
						} else {
							if (liveLost == game.getTotalLives() - 1) {
								JOptionPane.showMessageDialog(null, "Sorry!!! You Lost the game!!!");
								dispose();
							} else if (correctAnswers == game.getMaxQuestions() - 1) {
								JOptionPane.showMessageDialog(null, "Congratulations!!! You won the game!!!");
								dispose();
							} else {
								if (gameFunctions.checkAnswers(answer, qna.get(1))) {
									JOptionPane.showMessageDialog(null, "Correct Answer!!!");
									correctAnswers++;
									lblPointsEarned.setText("Points Earned : " + correctAnswers);
								} else {
									JOptionPane.showMessageDialog(null, "Wrong Answer!!!");
									liveLost++;
									lblLives.setText("Lives Remainning : " + (game.getTotalLives() - liveLost));
								}
							}
							
							textArea.setText("");
							textField.setText("");
							
							btnNewButton.setEnabled(true);
							btnSubmitAnswer.setEnabled(false);
							
							qna = null;
						}
					}
				
			}
		});
		btnSubmitAnswer.setFont(new Font("Dialog", Font.BOLD, 16));
		btnSubmitAnswer.setBounds(307, 503, 207, 46);
		btnSubmitAnswer.setEnabled(false);
		contentPane.add(btnSubmitAnswer);
		
		btnNewButton = new JButton("Roll Dice");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				btnSubmitAnswer.setEnabled(true);
					while (qna == null) {
						int randomCategory = gameFunctions.rollDice(1, 6) - 1;
						int randomQuestionNumber = gameFunctions.rollDice(1, 10) - 1;
	
						LinkedList<String> questionCategory = questions.get(randomCategory);
						LinkedList<String> answerCategory = answers.get(randomCategory);
	
						qna = gameFunctions.pickQuestionAndAnswer(questionCategory, answerCategory, randomQuestionNumber);
	
						if (qna != null) {
							lblCategory.setText("Category : " + tableNames[randomCategory]);
							gameFunctions.removeQuestionAndAnswer(questionCategory, answerCategory, randomQuestionNumber);
						}
					}
	
					textArea.setText(("Question : " + qna.get(0)));
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(27, 503, 207, 46);
		contentPane.add(btnNewButton);
		
				
		JLabel lblQuestion = new JLabel("Question:");
		lblQuestion.setFont(new Font("Dialog", Font.BOLD, 16));
		lblQuestion.setBounds(27, 181, 231, 38);
		contentPane.add(lblQuestion);
		
		JLabel lblPlayerName = new JLabel("Player Name : " + game.getPlayerName());
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPlayerName.setBounds(152, -1, 231, 38);
		contentPane.add(lblPlayerName);
	}
}
