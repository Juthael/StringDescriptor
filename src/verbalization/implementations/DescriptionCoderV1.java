package verbalization.implementations;

import java.util.ArrayList;
import java.util.List;

import copycatModel.grammar.CharString;
import exceptions.VerbalizationException;
import verbalization.implementations.dataEncodingModel.DescriptionCodeGetterV1;
import verbalization.interfaces.DescriptionCoderInterface;
import verbalization.interfaces.RecipeCoderInterface;
import verbalization.interfaces.dataEncodingModel.DescriptionCodeGetterInterface;
import verbalization.interfaces.dataEncodingModel.RecipeCodeGetterInterface;

public class DescriptionCoderV1 implements DescriptionCoderInterface {

	private DescriptionCodeGetterInterface descriptionCodeGetter;
	
	public DescriptionCoderV1(CharString descriptorToBeTranslated) throws VerbalizationException {
		descriptionCodeGetter = setDescriptorCodeGetter(descriptorToBeTranslated.getListOfPropertiesWithPath());
	}

	@Override
	public DescriptionCodeGetterInterface getDescriptionCodeGetter() {
		return descriptionCodeGetter;
	}
	
	private DescriptionCodeGetterInterface setDescriptorCodeGetter(List<String> listOfDescriptorProperties) 
			throws VerbalizationException {
		DescriptionCodeGetterInterface descriptionCodeGetter;
		String readingDirection = "";
		List<RecipeCodeGetterInterface> listOfRecipeCodeGetters = new ArrayList<RecipeCodeGetterInterface>();
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
			RecipeCoderInterface recipeCoder = new RecipeCoderV1(listOfUnfactorizedGroupProperties);
			RecipeCodeGetterInterface recipeCodeGetterInterface = recipeCoder.getRecipeCodeGetter();
			listOfRecipeCodeGetters.add(recipeCodeGetterInterface);
		}
		descriptionCodeGetter = new DescriptionCodeGetterV1(readingDirection, listOfRecipeCodeGetters);
		return descriptionCodeGetter;
	}

}
