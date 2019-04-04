package verbalization.implementations.dataEncodingModel;

import java.util.List;

import verbalization.interfaces.dataEncodingModel.DescriptionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.RecipeCodeGetterInterface;

public class DescriptionCodeGetterV1 implements DescriptionCodeGetterInterface {

	private final String readingDirection;
	private final List<RecipeCodeGetterInterface> listOfRecipeCodeGetters;
	
	public DescriptionCodeGetterV1(String readingDirection, List<RecipeCodeGetterInterface> listOfRecipeCodeGetters) {
		this.readingDirection = readingDirection;
		this.listOfRecipeCodeGetters = listOfRecipeCodeGetters;
	}
	
	@Override
	public String getReadingDirection() {
		return readingDirection;
	}


	@Override
	public List<RecipeCodeGetterInterface> getListOfRecipeCodeGetters() {
		return listOfRecipeCodeGetters;
	}
	
}
