package verbalization.dataEncoding.encoders.impl;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.CharString;
import exceptions.VerbalizationException;
import verbalization.dataEncoding.encoders.IDescriptionCoder;
import verbalization.dataEncoding.encoders.IRecipeCoder;
import verbalization.dataEncoding.encodingModel.IDescriptionCodeGetter;
import verbalization.dataEncoding.encodingModel.IRecipeCodeGetter;
import verbalization.dataEncoding.encodingModel.impl.DescriptionCodeGetterImpl;

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
		List<List<String>> listOfUnfactorizedGroupsPropertyLists = new ArrayList<List<String>>();
		List<String> currentUnfactorizedGroupListOfProperties = new ArrayList<String>();
		int unfactorizedGroupIndex = 0;
		for (String property : listOfDescriptorProperties) {
			String groupProperty = property.substring(unfactorizedGroupIndex);
			if (unfactorizedGroupIndex == 0) {
				if (groupProperty.contains("direction")) {
					int lastSlashIndex = groupProperty.lastIndexOf("/");
					readingDirection = groupProperty.substring(lastSlashIndex + 1);
				}
				else if (groupProperty.contains("group/size")) {
					unfactorizedGroupIndex = groupProperty.lastIndexOf("group/size");
					currentUnfactorizedGroupListOfProperties.add(property.substring(unfactorizedGroupIndex));
				}
			}
			else if (groupProperty.startsWith("group/size")) {
				List<String> currentUnfactorizedGroupListOfPropertiesClone = new ArrayList<String>();
				currentUnfactorizedGroupListOfPropertiesClone.addAll(currentUnfactorizedGroupListOfProperties);
				listOfUnfactorizedGroupsPropertyLists.add(currentUnfactorizedGroupListOfPropertiesClone);
				currentUnfactorizedGroupListOfProperties.clear();
				currentUnfactorizedGroupListOfProperties.add(groupProperty);
			}
			else currentUnfactorizedGroupListOfProperties.add(groupProperty);
		}
		listOfUnfactorizedGroupsPropertyLists.add(currentUnfactorizedGroupListOfProperties);
		for (List<String> listOfUnfactorizedGroupProperties : listOfUnfactorizedGroupsPropertyLists) {
			IRecipeCoder recipeCoder = new RecipeCoderImpl(listOfUnfactorizedGroupProperties);
			IRecipeCodeGetter iRecipeCodeGetter = recipeCoder.getRecipeCodeGetter();
			listOfRecipeCodeGetters.add(iRecipeCodeGetter);
		}
		descriptionCodeGetter = new DescriptionCodeGetterImpl(readingDirection, listOfRecipeCodeGetters);
		return descriptionCodeGetter;
	}

}
