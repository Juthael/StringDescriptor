package model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import copycatModel.grammar.CharString;
import exceptions.DescriptionFormatException;
import exceptions.DescriptorsBuilderCriticalException;
import model.IDescription;
import model.IDescriptionBuilder;
import syntacticTreesGeneration.implementations.ListOfDescriptorsBuilderV1;
import syntacticTreesGeneration.interfaces.ListOfDescriptorsBuilderInterface;

public class DescriptionBuilder implements IDescriptionBuilder {
	
	private boolean stringCanBeReadInBothDirections = false;
	private Predicate<String> validator;

	public DescriptionBuilder() {
		
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
	public List<IDescription> buildList(String stringToBeDescribed) throws DescriptionFormatException {
		List<IDescription> descs = new ArrayList<>();
		try {
			if (validator==null || validator.test(stringToBeDescribed)) {
				List<CharString> listOfWholeStringDescriptors = new ArrayList<CharString>();
				ListOfDescriptorsBuilderInterface descriptorsBuilderLeftToRight = new ListOfDescriptorsBuilderV1(
						stringToBeDescribed, "fromLeftToRight");
				listOfWholeStringDescriptors.addAll(descriptorsBuilderLeftToRight.getListOfStringDescriptors());
				if (stringCanBeReadInBothDirections == true) {
					StringBuilder sB = new StringBuilder(stringToBeDescribed);
					ListOfDescriptorsBuilderInterface descriptorsBuilderRightToLeft = new ListOfDescriptorsBuilderV1(
							sB.reverse().toString(), "fromRightToLeft");
					listOfWholeStringDescriptors.addAll(descriptorsBuilderRightToLeft.getListOfStringDescriptors());
				}
				if (listOfWholeStringDescriptors.isEmpty())
					throw new DescriptionFormatException("Unexpected error. List of Descriptors is empty");
				for (CharString descriptor : listOfWholeStringDescriptors) {
					IDescription description = new DescriptionImpl(descriptor);
					descs.add(description);
				}
			}
		} catch (DescriptorsBuilderCriticalException descException) {
			throw new DescriptionFormatException("Unexpected error. " + descException.getMessage());
		} catch (CloneNotSupportedException cloneException) {
			throw new DescriptionFormatException("Unexpected error. Clone not supported exception");
		}
		return descs;
	}

}
