package dao.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.DAOException;

public class DAOKeyboardInputManager {

	public DAOKeyboardInputManager() {
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
	
	public static int readInt() throws DAOException {
		int n = 0;
		try {
			String stringRead = readString();
			n = Integer.parseInt(stringRead);
		}
		catch (NumberFormatException err) {
			throw new DAOException("Error : illegal input.");
		}
		return n;
	}
}
