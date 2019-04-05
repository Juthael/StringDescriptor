package launcher.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.StringFormatException;

public class DescriptionKeyboardInputManager {

	public DescriptionKeyboardInputManager() {
	}
	
	public static String readString() {
		String stringInput = null;
		try {
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(reader);
			stringInput = input.readLine();
		}
		catch (IOException err) {
			System.exit(-1);
		}
		return stringInput;
	}
	
	public static int readInt() throws StringFormatException {
		int n = 0;
		try {
			String stringRead = readString();
			n = Integer.parseInt(stringRead);
		}
		catch (NumberFormatException err) {
			throw new StringFormatException("Error : illegal input.");
		}
		return n;
	}
}
