package launcher.utils.menu.impl;

import launcher.utils.menu.IMainMenu;
import launcher.utils.menu.IMenuSubPage;

public abstract class MenuSubPage extends MenuPage implements IMenuSubPage {

	public MenuSubPage() {
	}

	@Override
	public void goBackToMainMenu() {
		IMainMenu mainMenu = new MainMenu();
		mainMenu.exec();
	}

}
