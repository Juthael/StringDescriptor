package model.outputModel.charStringDescription.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import exceptions.OrderedSetsGenerationException;
import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;
import exceptions.VerbalizationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.outputModel.charStringDescription.IDescription;
import model.outputModel.charStringDescription.IDescriptionBuilder;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilder;

public class DescriptionBuilderImpl implements IDescriptionBuilder {
	
	private boolean stringCanBeReadInBothDirections = false;
	private Predicate<String> validator;

	public DescriptionBuilderImpl() {
	}
	
	@Override
	public IDescriptionBuilder validatedBy(Predicate<String> predicate) {
		this.validator = predicate;
		return this;
	}

	@Override
	public IDescriptionBuilder stringCanBeReadInBothDirections(boolean stringCanBeReadInBothDirections) {
		this.stringCanBeReadInBothDirections = stringCanBeReadInBothDirections;
		return this;
	}

	@Override
	public List<IDescription> buildList(String stringToBeDescribed) 
			throws SynTreeGenerationException, StringFormatException, VerbalizationException, OrderedSetsGenerationException {
		List<IDescription> listOfDescriptions = new ArrayList<>();
		try {
			if (validator==null || validator.test(stringToBeDescribed)) {
				List<CharString> listOfWholeStringDescriptors = new ArrayList<CharString>();
				IListOfDescriptorsBuilder descriptorsBuilderLeftToRight = new ListOfDescriptorsBuilder(
						stringToBeDescribed, Settings.LEFT_TO_RIGHT);
				listOfWholeStringDescriptors.addAll(descriptorsBuilderLeftToRight.getListOfDescriptorsWithAbstractComponents());
				if (stringCanBeReadInBothDirections == true) {
					StringBuilder sB = new StringBuilder(stringToBeDescribed);
					IListOfDescriptorsBuilder descriptorsBuilderRightToLeft = new ListOfDescriptorsBuilder(
							sB.reverse().toString(), Settings.RIGHT_TO_LEFT);
					listOfWholeStringDescriptors.addAll(
							descriptorsBuilderRightToLeft.getListOfDescriptorsWithAbstractComponents());
				}
				if (listOfWholeStringDescriptors.isEmpty())
					throw new SynTreeGenerationException("Unexpected error. List of Descriptors is empty");
				for (CharString descriptor : listOfWholeStringDescriptors) {
					System.out.println("");
					for (String property : descriptor.getListOfPropertiesWithPath()) {
						System.out.println(property);
					}
					IDescription description = new DescriptionImpl(descriptor);
					listOfDescriptions.add(description);
				}
			}
			else throw new StringFormatException("String entered is invalid.");
		}
		catch (CloneNotSupportedException cloneException) {
			cloneException.printStackTrace();
			throw new SynTreeGenerationException("DescriptionBuilderImpl.buildList() : "
					+ "CloneNotSupporterException catched.");
			
		}
		return listOfDescriptions;
	}

}
