package launcher.utils.menu;

import stringDescription.IDescription;

public interface IStringResults extends IMenuStringSubPage {
	
	void seeBestDescription(IDescription description, String string);
	
	void seeAllDescriptions(IDescription description, String string);

}
