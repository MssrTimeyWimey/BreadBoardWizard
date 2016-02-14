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
		
		JLabel lblCoolStory = new JLabel("<html>Oh no! Misfortune strikes again, for although you have restored the activator core of the WEAPON, its POWER SETTINGS, weak from years of disuse, refuse to show their true vitality. The castle needs your aid! You must take the power control knob and repair the broken circuits with it, and quickly! The BAD GUYS are nearing the gate, and without the weapon at full power, the walls that have held for centuries will surely fall.</CWRU>");
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
