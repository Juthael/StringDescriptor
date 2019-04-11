package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.ISequenceChecker;

public class EnumerationCheckerImpl implements IEnumerationChecker {
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final List<String> values;
	private boolean simpleEnumerationWasFound = false;
	private boolean secondDegreeEnumerationWasFound = false;
	private boolean enumerationWasFound;
	private String enumerationValue="";

	public EnumerationCheckerImpl(boolean wholeStringIsDescribed, String dimension, List<String> values) 
			throws SynTreeGenerationException {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		if (this.values.size() > 1) {
			boolean allValuesAreSize1 = testIfAllValuesAreSizeOne();
			if (allValuesAreSize1 == true) {
				simpleEnumerationWasFound = true;
				StringBuilder sB = new StringBuilder();
				for (int i=0 ; i<this.values.size() ; i++) {
					sB.append(this.values.get(i));
					if (i < this.values.size()-1)
						sB.append(",");
				}
				enumerationValue = sB.toString();
			}
			else {
				if (this.wholeStringIsDescribed == true) {
					if ((this.values.size() > 1) && 
							(this.values.size() <= Settings.MAX_NB_OF_COMPONENTS_FOR_2ND_DEGREE_ENUMERATION)) {
						StringBuilder sB = new StringBuilder();
						for (int i=0 ; i<this.values.size() ; i++) {
							sB.append(this.values.get(i));
							if (i < this.values.size()-1) {
								sB.append(Settings.SECOND_DEG_VALUE_SEPARATOR);
							}
						}
						secondDegreeEnumerationWasFound = true;
						enumerationValue = sB.toString();
					}
				}
			}
		}
		else if (this.values.size() == 1) {
			enumerationValue = this.values.get(0);
			simpleEnumerationWasFound = true;
		}
		else throw new SynTreeGenerationException("EnumerationChecker : less than two values to check");
		enumerationWasFound = (simpleEnumerationWasFound == true || secondDegreeEnumerationWasFound == true);
	}
	
	@Override
	public boolean getSimpleEnumerationWasFound() {
		return simpleEnumerationWasFound; 
	}
	
	@Override
	public boolean getSecondDegreeEnumerationWasFound() {
		return secondDegreeEnumerationWasFound;
	}
	
	@Override
	public boolean getEnumerationWasFound() {
		return enumerationWasFound;
	}
	
	@Override
	public IEnumerationRelationalData getEnumerationRelationalData() throws SynTreeGenerationException {
		if (simpleEnumerationWasFound == true || secondDegreeEnumerationWasFound == true) {
			IEnumerationRelationalData enumerationRelationalData = 
					new EnumerationRelationalDataImpl(dimension, enumerationValue);
			return enumerationRelationalData;
		}
		else throw new SynTreeGenerationException("EnumerationChecker : can't get an Enumeration that wasn't found");
	}
	
	@Override 
	public boolean getAllSecondDegreeValuesAreSequences() throws SynTreeGenerationException {
		boolean allValuesAreSequences = true;
		boolean oneValueAtLeastIsNotOfSize1 = false;
		if (values.size() > 1) {
			int valueIndex = 0;
			while (valueIndex < values.size() && allValuesAreSequences == true) {
				String value = values.get(valueIndex);
				List<String> listOfSubValues = new ArrayList<String>(Arrays.asList(value.split(",")));
				if (listOfSubValues.size() != 1) {
					if (oneValueAtLeastIsNotOfSize1 == false) {
						oneValueAtLeastIsNotOfSize1 = true;
					}
					IEnumerationChecker enumerationChecker = new EnumerationCheckerImpl(false, "temporaryDimension", listOfSubValues);
					ISequenceChecker sequenceChecker = 
							new SequenceCheckerImpl(
									false, "temporaryDimension", listOfSubValues, enumerationChecker.getEnumerationRelationalData());
					if (sequenceChecker.getSequenceWasFound() == false) {
						allValuesAreSequences = false;
					}
				}
				valueIndex++;
			}
			if (oneValueAtLeastIsNotOfSize1 == false)
				throw new SynTreeGenerationException("SymmetryCheckerImpl.getValuesAreIdentical : this method should only"
						+ " be used on second degree enumerations.");
			else return allValuesAreSequences;
		}
		else throw new SynTreeGenerationException("SymmetryCheckerImpl.getValuesAreIdentical() : this method shouldn't "
				+ "be invoked with a list of values of size " + values.size() + ".");
	}	
	
	private boolean testIfAllValuesAreSizeOne() {
		boolean allValuesAreSize1 = true;
		for (String value : values) {
			if (value.contains(","))
				allValuesAreSize1 = false;
		}
		return allValuesAreSize1;
	}

}
