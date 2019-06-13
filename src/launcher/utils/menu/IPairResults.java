package launcher.utils.menu;

import description.impl.DescriptionValuator;

public interface IPairResults extends IMenuPairSubPage {
	
	void seeBestDescription(DescriptionValuator valuator, String string1, String string2);
	
	void seeAllDescriptions(DescriptionValuator valuator, String string1, String string2);

}
