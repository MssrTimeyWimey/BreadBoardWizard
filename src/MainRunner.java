import java.util.Scanner;
import com.fazecast.jSerialComm.*;

public class MainRunner {
	public static int stagesUnlocked = 1;
	public static SerialPort arduinoPort;
	
	
	
	public static void main(String[] args) {
		askForArduinoPort();
		main window = new main();
		window.setVisible(true);
		
	}




	private static void askForArduinoPort() {
		boolean connected = false;
		while(!connected){
			// determine which serial port to use
			SerialPort ports[] = SerialPort.getCommPorts();
			System.out.println("Select a port:");
			int i = 1;
			for(SerialPort port : ports) {
				System.out.println(i++ + ". " + port.getSystemPortName() + ", " + port.getDescriptivePortName() + ", " + port.getBaudRate());
			}
			Scanner s = new Scanner(System.in);
			int chosenPort = s.nextInt();
			
			// open and configure the port
			SerialPort port = ports[chosenPort - 1];
			if(port.openPort()) {
				System.out.println("Successfully opened the port.");
				arduinoPort = port;
				connected = true;
			} else {
				System.out.println("Unable to open the port.");
			}
		}
		
	}
}
