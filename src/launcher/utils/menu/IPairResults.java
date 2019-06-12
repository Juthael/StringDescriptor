package launcher.utils.menu;

import stringDescription.IPairDescription;

public interface IPairResults extends IMenuPairSubPage {
	
	void seeBestDescription(IPairDescription description, String string1, String string2);
	
	void seeAllDescriptions(IPairDescription description, String string1, String string2);

}
