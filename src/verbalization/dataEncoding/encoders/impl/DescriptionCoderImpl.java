package verbalization.dataEncoding.encoders.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import settings.Settings;
import verbalization.dataEncoding.encoders.IDescriptionCoder;
import verbalization.dataEncoding.encoders.IRecipeCoder;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.DescriptionCodeGetter;

public class DescriptionCoderImpl implements IDescriptionCoder {

	private IDescriptionCodeGetter descriptionCodeGetter;
	
	public DescriptionCoderImpl(CharString descriptorToBeTranslated) throws VerbalizationException {
		descriptionCodeGetter = setDescriptorCodeGetter(descriptorToBeTranslated.getListOfPropertiesWithPath());
	}

	@Override
	public IDescriptionCodeGetter getDescriptionCodeGetter() {
		return descriptionCodeGetter;
	}
	
	private IDescriptionCodeGetter setDescriptorCodeGetter(List<String> listOfDescriptorProperties) 
			throws VerbalizationException {
		IDescriptionCodeGetter descriptionCodeGetter;
		String readingDirection = "";
		List<IRecipeCodeGetter> listOfRecipeCodeGetters = new ArrayList<IRecipeCodeGetter>();
		List<List<String>> listOfUnfactorizedFramesPropertyLists = new ArrayList<List<String>>();
		List<String> currentUnfactorizedFrameListOfProperties = new ArrayList<String>();
		int unfactorizedFrameIndex = 0;
		for (String property : listOfDescriptorProperties) {
			String frameProperty = property.substring(unfactorizedFrameIndex);
			if (unfactorizedFrameIndex == 0) {
				if (frameProperty.contains("direction")) {
					int lastSlashIndex = frameProperty.lastIndexOf(Settings.PATH_SEPARATOR);
					readingDirection = frameProperty.substring(lastSlashIndex + 1);
				}
				else if (frameProperty.contains("frame/size")) {
					unfactorizedFrameIndex = frameProperty.lastIndexOf("frame/size");
					currentUnfactorizedFrameListOfProperties.add(property.substring(unfactorizedFrameIndex));
				}
			}
			else if (frameProperty.startsWith("frame/size")) {
				List<String> currentUnfactorizedFrameListOfPropertiesClone = new ArrayList<String>();
				currentUnfactorizedFrameListOfPropertiesClone.addAll(currentUnfactorizedFrameListOfProperties);
				listOfUnfactorizedFramesPropertyLists.add(currentUnfactorizedFrameListOfPropertiesClone);
				currentUnfactorizedFrameListOfProperties.clear();
				currentUnfactorizedFrameListOfProperties.add(frameProperty);
			}
			else currentUnfactorizedFrameListOfProperties.add(frameProperty);
		}
		listOfUnfactorizedFramesPropertyLists.add(currentUnfactorizedFrameListOfProperties);
		for (List<String> listOfUnfactorizedFrameProperties : listOfUnfactorizedFramesPropertyLists) {
			IRecipeCoder recipeCoder = new RecipeCoderImpl(listOfUnfactorizedFrameProperties);
			IRecipeCodeGetter iRecipeCodeGetter = recipeCoder.getRecipeCodeGetter();
			listOfRecipeCodeGetters.add(iRecipeCodeGetter);
		}
		descriptionCodeGetter = new DescriptionCodeGetter(readingDirection, listOfRecipeCodeGetters);
		return descriptionCodeGetter;
	}

}
