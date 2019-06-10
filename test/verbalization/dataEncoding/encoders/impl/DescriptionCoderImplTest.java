package verbalization.dataEncoding.encoders.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;
import verbalization.dataEncoding.encoders.IDescriptionCoder;
import verbalization.dataEncoding.encoders.impl.DescriptionCoderImpl;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;

public class DescriptionCoderImplTest {

	@Test
	public void whenParameterIsThisListOfPropertiesThenExpectedNumberOfRecipesIsReturned() 
			throws VerbalizationException, SynTreeGenerationException, CloneNotSupportedException {
		try {
			IListOfDescriptorsBuilder listOfDescriptorsBuilder = new ListOfDescriptorsBuilderImpl("abcd", "fromLeftToRight");
			List<CharString> listOfDescriptors = listOfDescriptorsBuilder.getListOfComprehensiveDescriptors();
			boolean numberOfRecipesIsUnexpected = false;
			for (CharString descriptor : listOfDescriptors) {
				List<String> listOfProperties = descriptor.getListOfPropertiesWithPath();
				int numberOfUnfactorizedFrames = getNbOfUnfactorizedFrames(listOfProperties);
				IDescriptionCoder descriptionCoder = new DescriptionCoderImpl(descriptor);
				IDescriptionCodeGetter descriptionCodeGetter = descriptionCoder.getDescriptionCodeGetter();
				List<IRecipeCodeGetter> listOfRecipeCodeGetters = descriptionCodeGetter.getListOfRecipeCodeGetters();
				if (listOfRecipeCodeGetters.size() != numberOfUnfactorizedFrames)
					numberOfRecipesIsUnexpected = true;
			}
			assertTrue(numberOfRecipesIsUnexpected);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	private int getNbOfUnfactorizedFrames(List<String> listOfProperties) {
		int nbOfUnfactorizedFrames = -1;
		boolean nbFound = false;
		int propertyIndex = 0;
		while(propertyIndex < listOfProperties.size() && nbFound == false) {
			String currentProperty = listOfProperties.get(propertyIndex);
			if (currentProperty.contains("frame/size")){
				int lastSlashIndex = currentProperty.lastIndexOf(Settings.PATH_SEPARATOR);
				nbOfUnfactorizedFrames = Integer.parseInt(currentProperty.substring(lastSlashIndex + 1));
				nbFound = true;
			}
			propertyIndex++;
		}
		return nbOfUnfactorizedFrames;
	}

}
