package verbalization.dataEncoding.encodingModel;

import java.util.List;

public interface IDescriptionCodeGetter {
	
	String getReadingDirection();
	
	List<IRecipeCodeGetter> getListOfRecipeCodeGetters();

}
