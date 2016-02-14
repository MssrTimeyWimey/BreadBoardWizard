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

public class Instructions extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Instructions frame = new Instructions();
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
	public Instructions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1105, 913);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("<html><li>Welcome to Breadboard Wizard! You're a wizard, kid! You must use your electric bending powers to survive in the hostile universe you call home. In your posession, you have only one microcontroller arduino, some wires, and the almighty breadboard of spells.</li><li>You must first set up your sorcery pack before embarking on your perilous journey. Start by using a wire to connect the GND Hole on your microcontroller to slot 30 on the negative line of your breadboard of spells. Then, use another wire to connect that 5V hole on the microcontroller to slot 30 of the positive rail on your breadboard of spells.</li><li>Now plug your microcontroller into a usb port on your computer and watch the lights turn on! You are ready to begin your journey!</li><li>Lol.</li></html>");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Gabriola", Font.PLAIN, 35));
		label.setBounds(15, 89, 1053, 670);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("INSTRUCTIONS");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Gabriola", Font.PLAIN, 70));
		label_1.setBounds(15, 16, 1053, 123);
		contentPane.add(label_1);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				main.main(null);
			}
		});
		button.setFont(new Font("Gabriola", Font.PLAIN, 50));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(15, 747, 1053, 94);
		contentPane.add(button);
	}

}
