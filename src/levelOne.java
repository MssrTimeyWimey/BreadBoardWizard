import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.fazecast.jSerialComm.SerialPort;

public class levelOne extends JFrame {
	
	private int screen = 0;

	private JPanel contentPane;
	private JButton button;
	private Board board;
	private JLabel label;
	private JLabel lblfarOutIn;
	private long lastTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					levelOne frame = new levelOne();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void checkCompletion() {
		

		MainRunner.arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		InputStream inputStream = MainRunner.arduinoPort.getInputStream();
		Scanner data = new Scanner(inputStream);
		
		
		//tell the arduino what program we're on: specifically, #1, "button"
		PrintWriter output = new PrintWriter(MainRunner.arduinoPort.getOutputStream());
		output.write("one\n");
		output.flush();
		System.out.println("done writing");

		//wait for a button response from the user
		while(true) {
			try {
				if (data.hasNext()) {
					String line = data.nextLine();
					System.out.print("line = ");
					System.out.println(line);
					if (line.charAt(1) == '=') {
						String varname = line.substring(0, 1);
						int value = Integer.parseInt(line.substring(2));
						if (varname.equals("b")) {
							if (value == 1) {
								board.shoot();
							}
						} else if (varname.equals("p")) {
							board.setAngle(value-90);
						} else if (varname.equals("s")) {
							board.scream();
						} else {
							System.out.println("uhh problemm?");
							System.out.println(line);
						}
						
						MainRunner.stagesUnlocked++;
						button.setEnabled(true);
						System.out.println("VICTORY");
						//break;
					} else {
						System.out.println("problem 2");
						System.out.println(line);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (System.currentTimeMillis() - lastTime > 100) {
				lastTime = System.currentTimeMillis();
				if (screen >= 5 && Math.random() < 0.1) {
					board.makeEnemy();
				}
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public levelOne() {
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 922);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		button = new JButton("Continue");
		if(MainRunner.stagesUnlocked < 2){
			button.setEnabled(false);
		}
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen++;
				switch (screen) {
				case 1:
					label.setVisible(false);
					lblfarOutIn.setVisible(false);
					board.setVisible(true);
					break;
				case 2:
					lblfarOutIn.setText("<html>Oh no! Misfortune strikes again, for although you have restored the activator core of the WEAPON, its POWER SETTINGS, weak from years of disuse, refuse to show their true vitality. The castle needs your aid! You must take the power control knob and repair the broken circuits with it, and quickly! The BAD GUYS are nearing the gate, and without the weapon at full power, the walls that have held for centuries will surely fall.</html>");
					label.setText("<html>To repair the power gauge, you will use the A0 port on the arduino. Again, you will be using four jumper wires, a connector cable, and the potentiometer. You've already fixed the button, so this should be a piece of cake! Again connect the A0 port to the breadboard and the connector cable. Then, connect the red and black wires to the power rails of the breadboard, and twist the lever all the way to the right for max firepower!</html>");
					label.setVisible(true);
					lblfarOutIn.setVisible(true);
					board.setVisible(false);
					break;
				case 3:
					label.setVisible(false);
					lblfarOutIn.setVisible(false);
					board.setVisible(true);
					break;
				case 4:
					lblfarOutIn.setText("<html>Now that the WEAPON is fully functional, you are ready to take out the BAD GUYS. Inspire your comrades and lead them in the fight against the evil beyond the wall! By repairing the microphone for the castle's PA system you can rally your fellows, providing them with the bravery that exemplifies a legendary warrior. Nothing can inspire your brothers more than the amplified sound of a war cry. Let those vocal cords vibrate freely and fiercely!</html>");
					label.setText("<html>The most important part... repair the PA system! Now that you've fixed both the button and the lever, I won't even need to tell you have to do this. It's the exact same process, except now we will be using the A1 port on the arduino instead of the A0 port. When ready, let the world hear your war cry!</html>");
					label.setVisible(true);
					lblfarOutIn.setVisible(true);
					board.setVisible(false);
					break;
				case 5:
					label.setVisible(false);
					lblfarOutIn.setVisible(false);
					board.setVisible(true);
					break;
				case 6:
					break;
				default:
					break;
				}
			}
		});
		button.setFont(new Font("Gabriola", Font.PLAIN, 50));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(16, 754, 1015, 96);
		contentPane.add(button);
		
		board = new Board();
		board.setBounds(115, 100, 800, 600);
		board.setVisible(false);
		contentPane.add(board);
		
		label = new JLabel("<html>To rewire the button, you will need the button sensor, the connector cable, and four jumper cables. In this challenge, you will be using port 13 of the Digital pin ports of the arduino. You will connect port 13 to the breadboard, and then connect that breadboard to the yellow wire of the connector cable. The other two jumper wires will be used to connect the power and the ground wires, connecting the red wire to the positive rail and the black wire to the blue channel. Press the button when ready!</html>");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Gabriola", Font.PLAIN, 27));
		label.setBounds(15, 302, 1016, 270);
		contentPane.add(label);
		
		lblfarOutIn = new JLabel("<html>Far out in the uncharted backwaters of the unfashionable end of the Western spiral arm of the realm lies a small unregarded yellow castle. Its magnificent buttresses stand tall and proud, its walls boasting six centuries of integrity. Now the BAD GUYS have descended once again from the north, and the castle's defenses have aged to a sorry state of dysfunction, turned soft from years of fighting only rain and wind. Now a new class of enemy stands at your doorstep, and you must restore the WEAPON's functionality. Use your knowledge of circuitry to repair the ACTIVATOR SWITCH and destroy the BAD GUYS knocking at your door!</html>");
		lblfarOutIn.setVerticalAlignment(SwingConstants.TOP);
		lblfarOutIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblfarOutIn.setFont(new Font("Gabriola", Font.PLAIN, 30));
		lblfarOutIn.setBounds(15, 16, 1016, 270);
		contentPane.add(lblfarOutIn);
		
//		this.setVisible(true);
		Thread thread = new Thread(){
			@Override public void run() {
				checkCompletion();
			}
		};
		thread.start();
	}

}
