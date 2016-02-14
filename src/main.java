import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class main {

	private JFrame homescreen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.homescreen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setVisible(boolean b){
		homescreen.setVisible(b);
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		homescreen = new JFrame();
		homescreen.setBounds(100, 100, 993, 913);
		homescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		homescreen.getContentPane().setLayout(null);
		
		JButton Start = new JButton("PLAY");
		Start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				homescreen.dispose();
				map mapscreen = new map();
				mapscreen.setVisible(true);
			}
		});
		Start.setFont(new Font("Gabriola", Font.PLAIN, 40));
		Start.setBackground(Color.LIGHT_GRAY);
		Start.setBounds(323, 716, 269, 79);
		homescreen.getContentPane().add(Start);
		
		JLabel label = new JLabel("BREADBOARD WIZARD");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(85, 107, 47));
		label.setFont(new Font("Gabriola", Font.PLAIN, 67));
		label.setBackground(new Color(154, 205, 50));
		label.setBounds(32, 16, 535, 130);
		homescreen.getContentPane().add(label);
		
		JButton Boob = new JButton("INSTRUCTIONS");
		Boob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homescreen.dispose();
				Instructions instructionscreen = new Instructions();
				instructionscreen.setVisible(true);
			}
		});
		Boob.setFont(new Font("Gabriola", Font.PLAIN, 40));
		Boob.setBackground(Color.LIGHT_GRAY);
		Boob.setBounds(607, 716, 269, 79);
		homescreen.getContentPane().add(Boob);
		
		JLabel lblDrawSomethingHere = new JLabel(" ");
		lblDrawSomethingHere.setIcon(new ImageIcon(main.class.getResource("/img/merlin.jpg")));
		lblDrawSomethingHere.setBounds(0, 0, 971, 857);
		homescreen.getContentPane().add(lblDrawSomethingHere);
	}

}
