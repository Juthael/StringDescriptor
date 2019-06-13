package launcher.utils.menu;

import description.impl.DescriptionValuator;

public interface IStringResults extends IMenuStringSubPage {
	
	void seeBestDescription(DescriptionValuator valuator, String string);
	
	void seeAllDescriptions(DescriptionValuator valuator, String string);

}
