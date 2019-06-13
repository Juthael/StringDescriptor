package launcher.utils.menu;

import description.impl.DescriptionValuator;

public interface IStringAllResults extends IMenuStringSubPage, IMenuResultSubPage {
	
	void seeAllVerbalDescriptions(DescriptionValuator valuator, String string);
	
	void seeAllDescriptions1by1(DescriptionValuator valuato, String string);

}
