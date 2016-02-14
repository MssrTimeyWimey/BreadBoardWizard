import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class levelTwo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					levelTwo frame = new levelTwo();
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
	public levelTwo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 922);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("(CREATIVE PROGRESS BUTTON");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				map mapscreen = new map();
				mapscreen.setVisible(true);
			}
		});
		button.setFont(new Font("Gabriola", Font.PLAIN, 50));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(186, 754, 845, 96);
		contentPane.add(button);
		
		JLabel label = new JLabel("INSTRUCTIONS");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Gabriola", Font.PLAIN, 27));
		label.setBounds(15, 302, 1016, 436);
		contentPane.add(label);
		
		JLabel lblCoolStory = new JLabel("<html>You have fixed the activator core of the WEAPON only to realize that the WEAPON'S power setting are stuck on low! The city needs your help! Take the power control stick and repair the broken circuits and save your city! The BAD GUYS are almost upon the castle walls, and without the weapon at full functional power, the walls will crumble!</CWRU>");
		lblCoolStory.setVerticalAlignment(SwingConstants.TOP);
		lblCoolStory.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoolStory.setFont(new Font("Gabriola", Font.PLAIN, 30));
		lblCoolStory.setBounds(15, 16, 1016, 270);
		contentPane.add(lblCoolStory);
		
		JButton btnRestart = new JButton("RESTART");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				main.main(null);
			}
		});
		btnRestart.setFont(new Font("Gabriola", Font.PLAIN, 30));
		btnRestart.setBackground(Color.LIGHT_GRAY);
		btnRestart.setBounds(15, 754, 157, 96);
		contentPane.add(btnRestart);
	}

}
