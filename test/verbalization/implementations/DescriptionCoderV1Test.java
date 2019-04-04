package verbalization.implementations;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import copycatModel.grammar.CharString;
import exceptions.DescriptorsBuilderCriticalException;
import exceptions.VerbalizationException;
import syntacticTreesGeneration.implementations.ListOfDescriptorsBuilderV1;
import syntacticTreesGeneration.interfaces.ListOfDescriptorsBuilderInterface;
import verbalization.interfaces.DescriptionCoderInterface;
import verbalization.interfaces.dataEncodingModel.DescriptionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.RecipeCodeGetterInterface;

public class DescriptionCoderV1Test {

	@Test
	public void whenParameterIsThisListOfPropertiesThenExpectedNumberOfRecipesIsReturned() 
			throws VerbalizationException, DescriptorsBuilderCriticalException, CloneNotSupportedException {
		ListOfDescriptorsBuilderInterface listOfDescriptorsBuilder = new ListOfDescriptorsBuilderV1("abcd", "fromLeftToRight");
		List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfStringDescriptors();
		boolean numberOfRecipesIsUnexpected = false;
		for (CharString descriptor : listOfDescriptors) {
			ArrayList<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
			int numberOfUnfactorizedGroups = getNbOfUnfactorizedGroups(listOfProperties);
			DescriptionCoderInterface descriptionCoder = new DescriptionCoderV1(descriptor);
			DescriptionCodeGetterInterface descriptionCodeGetter = descriptionCoder.getDescriptionCodeGetter();
			List<RecipeCodeGetterInterface> listOfRecipeCodeGetters = descriptionCodeGetter.getListOfRecipeCodeGetters();
			if (listOfRecipeCodeGetters.size() != numberOfUnfactorizedGroups)
				numberOfRecipesIsUnexpected = true;
		}
		assertTrue(numberOfRecipesIsUnexpected);
	}
	
	private int getNbOfUnfactorizedGroups(List<String> listOfProperties) {
		int nbOfUnfactorizedGroups = -1;
		boolean nbFound = false;
		int propertyIndex = 0;
		while(propertyIndex < listOfProperties.size() && nbFound == false) {
			String currentProperty = listOfProperties.get(propertyIndex);
			if (currentProperty.contains("group/size")){
				int lastSlashIndex = currentProperty.lastIndexOf("/");
				nbOfUnfactorizedGroups = Integer.parseInt(currentProperty.substring(lastSlashIndex + 1));
				nbFound = true;
			}
			propertyIndex++;
		}
		return nbOfUnfactorizedGroups;
	}

}
