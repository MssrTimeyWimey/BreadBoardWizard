import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class map extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					map frame = new map();
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
	public map() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 804);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton onebutt = new JButton("STORY TEXT");
		onebutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				levelOne onescreen = new levelOne();
			}
		});
		onebutt.setFont(new Font("Gabriola", Font.PLAIN, 30));
		onebutt.setBounds(102, 118, 176, 89);
		contentPane.add(onebutt);
		
		JButton twobutt = new JButton("STORY TEXT");
		
		if(MainRunner.stagesUnlocked < 2)
			twobutt.setEnabled(false);
		
		twobutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				levelTwo twoscreen = new levelTwo();
				twoscreen.setVisible(true);
			}
		});
		twobutt.setFont(new Font("Gabriola", Font.PLAIN, 30));
		twobutt.setBounds(526, 319, 176, 89);
		contentPane.add(twobutt);
		
		JButton threebutt = new JButton("STORY TEXT");
		
		if(MainRunner.stagesUnlocked < 3)
			threebutt.setEnabled(false);
		
		threebutt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				levelThree threescreen = new levelThree();
				threescreen.setVisible(true);
			}
		});
		threebutt.setFont(new Font("Gabriola", Font.PLAIN, 30));
		threebutt.setBounds(323, 545, 176, 89);
		contentPane.add(threebutt);
	}

}
