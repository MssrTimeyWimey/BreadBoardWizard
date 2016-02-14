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

	private JPanel contentPane;
	private boolean taskCompleted = false;
	private JButton button;

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
		

		MainRunner.arduinoPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0, 0);
		InputStream inputStream = MainRunner.arduinoPort.getInputStream();
		Scanner data = new Scanner(inputStream);
		
		
		//tell the arduino what program we're on: specifically, #1, "button"
		PrintWriter output = new PrintWriter(MainRunner.arduinoPort.getOutputStream());
		output.write("one\n");
		output.flush();
		System.out.println("done writing");

		//I am a motherfucking badass
		
		System.out.println(MainRunner.arduinoPort.isOpen());
		System.out.println(MainRunner.arduinoPort.getBaudRate());

		//wait for a button response from the user
		while(true) {
			try {
				if (inputStream.available() > 0) {
					int number = inputStream.read() - 48;
					if(number > 0){
						MainRunner.stagesUnlocked++;
						button.setEnabled(true);
						//button.update(getGraphics());
						System.out.println("VICTORY");
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
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
		
		button = new JButton("CREATIVE PROGRESS BUTTON");
		if(MainRunner.stagesUnlocked < 2){
			button.setEnabled(false);
		}
		
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
		
		JLabel lblfarOutIn = new JLabel("<html>Far out in the uncharted backwaters of the unfashionable end of the Western spiral arm of he realm lies a small unregarded yellow castle. Its magnificent beams stand tall and proud, and nothing had ever laid seige upon it since the second ages. Now the terrifying BAD GUYS have returned, and the castle's defenses are old and disfunctional. You must fix the WEAPON! Use your knowledge of electricity to repair the activator button and launch the counterattack!</html>");
		lblfarOutIn.setVerticalAlignment(SwingConstants.TOP);
		lblfarOutIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblfarOutIn.setFont(new Font("Gabriola", Font.PLAIN, 30));
		lblfarOutIn.setBounds(15, 16, 1016, 270);
		contentPane.add(lblfarOutIn);
		
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
		
//		this.setVisible(true);
		Thread thread = new Thread(){
			@Override public void run() {
				checkCompletion();
			}
		};
		thread.start();
	}

}
