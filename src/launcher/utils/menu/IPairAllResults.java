package launcher.utils.menu;

public interface IPairAllResults extends IMenuPairSubPage, IMenuResultSubPage {
	
	void seeAllVerbalDescriptions(IPairDescription description, String string1, String string2);
	
	void seeAllDescriptions1by1(IPairDescription description, String string1, String string2);

}
