package launcher.utils.menu.impl;

import description.IDescriptionValuator;
import launcher.utils.menu.IMenuPage;
import launcher.utils.menu.IMenuText;
import launcher.utils.menu.IStringDescriptorCmd;

public abstract class MenuPage implements IMenuPage {

	protected IMenuText txt = getMenuText();
	protected static IDescriptionValuator valuator;
	
	public MenuPage() {
	}
	
	@Override
	public void exec() {
		getPagePresentation();
		presentChoice();
		manageSelection();
	}

	@Override
	abstract public void getPagePresentation();

	@Override
	abstract public void presentChoice();

	@Override
	abstract public void manageSelection();

	private IMenuText getMenuText() {
		IStringDescriptorCmd cmd = new StringDescriptorCmd();
		return cmd.getMenuText();
	}

}
