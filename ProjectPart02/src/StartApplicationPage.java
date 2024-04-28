import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartApplicationPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartApplicationPage frame = new StartApplicationPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartApplicationPage() {
		setTitle("Quizly bears");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 252);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel nameLabel = new JLabel("Enter your name:");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		nameLabel.setBounds(54, 61, 169, 55);
		contentPane.add(nameLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.BOLD, 16));
		textField.setBounds(241, 61, 257, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				
				if (name.isBlank()) {
					JOptionPane.showMessageDialog(null, "Name can not be empty! Please try again!");
				} else {
					GameSetup game = new GameSetup(name, 3, 5);
					dispose();
					new MenuPage(game).setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 16));
		btnNewButton.setBounds(241, 121, 117, 55);
		contentPane.add(btnNewButton);
	}
}
