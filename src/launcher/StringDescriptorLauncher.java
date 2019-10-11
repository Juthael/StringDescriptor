package launcher;

import launcher.utils.menu.IStringDescriptorCmd;
import launcher.utils.menu.impl.StringDescriptorCmd;

public class StringDescriptorLauncher {

	public StringDescriptorLauncher() {
	}

	public static void main(String[] args) {
		IStringDescriptorCmd cmd = new StringDescriptorCmd(); 
		cmd.mainMenu();
	}

}
