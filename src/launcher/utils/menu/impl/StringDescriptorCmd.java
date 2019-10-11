package launcher.utils.menu.impl;

import fca.exception.LMLogger;
import fca.gui.util.constant.LMIcons;
import fca.gui.util.constant.LMImages;
import launcher.utils.menu.IMainMenu;
import launcher.utils.menu.IMenuText;
import launcher.utils.menu.IStringDescriptorCmd;

public class StringDescriptorCmd implements IStringDescriptorCmd {

	public static final IMenuText defaultMenuText = new MenuTextEng();
	public final IMenuText menuText;
	
	public StringDescriptorCmd() {
		LMLogger.getLMLogger();
		LMImages.getLMImages();
		LMIcons.getLMIcons();
		menuText = defaultMenuText;
	}
	
	public StringDescriptorCmd(boolean selectLanguage) {
		menuText = selectLanguage();
	}	

	@Override
	public IMenuText getMenuText() {
		return menuText;
	}
	
	@Override
	public void mainMenu() {
		System.out.println(menuText.getaStringDescriptor());
		IMainMenu mainMenu = new MainMenu();
		mainMenu.exec();
	}
	
	private IMenuText selectLanguage() {
		//not implemented yet
		return defaultMenuText;
	}

}
