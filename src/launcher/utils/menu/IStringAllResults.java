package launcher.utils.menu;

import stringDescription.IDescription;

public interface IStringAllResults extends IMenuStringSubPage, IMenuResultSubPage {
	
	void seeAllVerbalDescriptions(IDescription description, String string);
	
	void seeAllDescriptions1by1(IDescription description, String string);

}
