package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.EnumerationCheckerInterface;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;

public class EnumerationCheckerV1 implements EnumerationCheckerInterface {
	
	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final ArrayList<String> values;
	private boolean simpleEnumerationWasFound = false;
	private boolean secondDegreeEnumerationWasFound = false;
	private boolean enumerationWasFound;
	private String enumerationValue="";

	public EnumerationCheckerV1(boolean wholeStringIsDescribed, String dimension, ArrayList<String> values) 
			throws DescriptorsBuilderCriticalException {
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
							(this.values.size() <= DescGenSettings.MAX_NB_OF_COMPONENTS_FOR_2ND_DEGREE_ENUMERATION)) {
						StringBuilder sB = new StringBuilder();
						for (int i=0 ; i<this.values.size() ; i++) {
							sB.append(this.values.get(i));
							if (i < this.values.size()-1) {
								sB.append(DescGenSettings.SECOND_DEGREE_ENUMERATION_SEPARATOR);
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
		else throw new DescriptorsBuilderCriticalException("EnumerationChecker : less than two values to check");
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
	public EnumerationRelationalDataInterface getEnumerationRelationalData() throws DescriptorsBuilderCriticalException {
		if (simpleEnumerationWasFound == true || secondDegreeEnumerationWasFound == true) {
			EnumerationRelationalDataInterface enumerationRelationalData = 
					new EnumerationRelationalDataV1(dimension, enumerationValue);
			return enumerationRelationalData;
		}
		else throw new DescriptorsBuilderCriticalException("EnumerationChecker : can't get an Enumeration that wasn't found");
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
