package model.outputModel.charStringDescription.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import exceptions.StringFormatException;
import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.CharString;
import model.outputModel.charStringDescription.IDescription;
import model.outputModel.charStringDescription.IDescriptionBuilder;
import settings.Settings;
import syntacticTreesGeneration.IListOfDescriptorsBuilder;
import syntacticTreesGeneration.impl.ListOfDescriptorsBuilderImpl;

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
			throws SynTreeGenerationException, StringFormatException {
		List<IDescription> listOfDescriptions = new ArrayList<>();
		try {
			if (validator==null || validator.test(stringToBeDescribed)) {
				List<CharString> listOfWholeStringDescriptors = new ArrayList<CharString>();
				IListOfDescriptorsBuilder descriptorsBuilderLeftToRight = new ListOfDescriptorsBuilderImpl(
						stringToBeDescribed, Settings.LEFT_TO_RIGHT);
				listOfWholeStringDescriptors.addAll(descriptorsBuilderLeftToRight.getListOfStringDescriptors());
				if (stringCanBeReadInBothDirections == true) {
					StringBuilder sB = new StringBuilder(stringToBeDescribed);
					IListOfDescriptorsBuilder descriptorsBuilderRightToLeft = new ListOfDescriptorsBuilderImpl(
							sB.reverse().toString(), Settings.RIGHT_TO_LEFT);
					listOfWholeStringDescriptors.addAll(descriptorsBuilderRightToLeft.getListOfStringDescriptors());
				}
				if (listOfWholeStringDescriptors.isEmpty())
					throw new SynTreeGenerationException("Unexpected error. List of Descriptors is empty");
				for (CharString descriptor : listOfWholeStringDescriptors) {
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
