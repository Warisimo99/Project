import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MenuPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MenuPage(GameSetup game) {
		setTitle("Trivial Pursuit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		setContentPane(contentPane);
		
		JButton playGameButton = new JButton("Play Game");
		playGameButton.setFont(new Font("Dialog", Font.BOLD, 16));
		playGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new GamePage(game).setVisible(true);
			}
		});
		playGameButton.setBounds(191, 55, 150, 50);
		contentPane.add(playGameButton);
		
		JButton adminButton = new JButton("Admin");
		adminButton.setFont(new Font("Dialog", Font.BOLD, 16));
		adminButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminPage(game).setVisible(true);
			}
		});
		adminButton.setBounds(38, 55, 125, 50);
		contentPane.add(adminButton);
		
	}

}
