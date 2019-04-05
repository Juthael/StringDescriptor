package syntacticTreesGeneration.impl;

import java.util.List;

import exceptions.DescriptorsBuilderException;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationChecker;
import syntacticTreesGeneration.IEnumerationRelationalData;

public class EnumerationCheckerImpl implements IEnumerationChecker {
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final List<String> values;
	private boolean simpleEnumerationWasFound = false;
	private boolean secondDegreeEnumerationWasFound = false;
	private boolean enumerationWasFound;
	private String enumerationValue="";

	public EnumerationCheckerImpl(boolean wholeStringIsDescribed, String dimension, List<String> values) 
			throws DescriptorsBuilderException {
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
								sB.append(Settings.SECOND_DEGREE_ENUMERATION_SEPARATOR);
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
		else throw new DescriptorsBuilderException("EnumerationChecker : less than two values to check");
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
	public IEnumerationRelationalData getEnumerationRelationalData() throws DescriptorsBuilderException {
		if (simpleEnumerationWasFound == true || secondDegreeEnumerationWasFound == true) {
			IEnumerationRelationalData enumerationRelationalData = 
					new EnumerationRelationalDataImpl(dimension, enumerationValue);
			return enumerationRelationalData;
		}
		else throw new DescriptorsBuilderException("EnumerationChecker : can't get an Enumeration that wasn't found");
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
