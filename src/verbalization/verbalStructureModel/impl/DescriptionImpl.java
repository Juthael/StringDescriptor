package verbalization.verbalStructureModel.impl;

import java.util.List;

import exceptions.VerbalizationException;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.verbalStructureModel.IDescription;
import verbalization.verbalStructureModel.IRecipe;

public class DescriptionImpl implements IDescription {

	private final String verbalDescription;
	
	public DescriptionImpl(IDescriptionCodeGetter descriptionCodeGetter) throws VerbalizationException {
		verbalDescription = setVerbalDescription(descriptionCodeGetter);
	}
	
	@Override
	public String getVerbalDescription() {
		return verbalDescription;
	}
	
	private String setVerbalDescription(IDescriptionCodeGetter descriptionCodeGetter) throws VerbalizationException {
		String verbalDescription;
		List<IRecipeCodeGetter> listOfRecipeCodeGetters = descriptionCodeGetter.getListOfRecipeCodeGetters();
		StringBuilder sB = new StringBuilder();
		for (int i=0 ; i<listOfRecipeCodeGetters.size() ; i++) {
			sB.append(getRecipeIntroducer(i+1));
			IRecipe recipe = new RecipeImpl(listOfRecipeCodeGetters.get(i));
			sB.append(recipe.getVerbalRecipe());
		}
		verbalDescription = sB.toString();
		return verbalDescription;
	}
	
	private String getRecipeIntroducer(int index) throws VerbalizationException {
		String recipeIntroducer;
		switch(index) {
			case 1 :
				recipeIntroducer = "First, ";
				break;
			case 2 :
				recipeIntroducer = "Second, ";
				break;
			case 3 :
				recipeIntroducer = "Third, ";
				break;
			case 4 :
				recipeIntroducer = "Fourth, ";
				break;
			case 5 :
				recipeIntroducer = "Fifth, ";
				break;
			case 6 :
				recipeIntroducer = "Sixth, ";
				break;
			case 7 :
				recipeIntroducer = "Seventh, ";
				break;
			case 8:
				recipeIntroducer = "Eigth, ";
				break;
			case 9 :
				recipeIntroducer = "Ninth, ";
				break;
			case 10 :
				recipeIntroducer = "Tenth, ";
				break;
			default :
				throw new VerbalizationException("Decsription.getRecipeIntroducer() : index " + index + " wasn't expected.");
		}
		return recipeIntroducer;
	}

}
