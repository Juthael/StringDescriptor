package verbalization.dataEncoding.encodingModel.impl;

import java.util.List;

import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;

public class DescriptionCodeGetter implements IDescriptionCodeGetter {

	private final String readingDirection;
	private final List<IRecipeCodeGetter> listOfRecipeCodeGetters;
	
	public DescriptionCodeGetter(String readingDirection, List<IRecipeCodeGetter> listOfRecipeCodeGetters) {
		this.readingDirection = readingDirection;
		this.listOfRecipeCodeGetters = listOfRecipeCodeGetters;
	}
	
	@Override
	public String getReadingDirection() {
		return readingDirection;
	}


	@Override
	public List<IRecipeCodeGetter> getListOfRecipeCodeGetters() {
		return listOfRecipeCodeGetters;
	}
	
}
