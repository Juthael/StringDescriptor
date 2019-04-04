package syntacticTreesGeneration.implementations;

import java.util.ArrayList;

import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.SequenceCheckerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;

public class SequenceCheckerV1 implements SequenceCheckerInterface {

	private final boolean wholeStringIsDescribed;
	private final String dimension;
	private final ArrayList<String> values;
	private final EnumerationRelationalDataInterface enumerationRelationalData;
	private boolean sequenceWasFound;
	private String commonDifferenceValue="";
	
	public SequenceCheckerV1(boolean wholeStringIsDescribed, String dimension, ArrayList<String> values, 
			EnumerationRelationalDataInterface enumerationRelationalData) {
		this.wholeStringIsDescribed = wholeStringIsDescribed;
		this.dimension = dimension;
		this.values = values;
		this.enumerationRelationalData = enumerationRelationalData;
		sequenceWasFound = true;
		int IntegerCommonDifferenceValue = 99;
		if (values.size() > 1) {
			boolean allValuesAreSize1 = testIfAllValuesAreSizeOne();
			if (allValuesAreSize1 == true) {
				ArrayList<Integer> listOfIntegerValues = initializeListOfIntegerValues();
				IntegerCommonDifferenceValue = listOfIntegerValues.get(1) - listOfIntegerValues.get(0);
				int valuesIndex = 1;
				while ((sequenceWasFound == true) && (valuesIndex < listOfIntegerValues.size()-1)) {
					int newDifference = listOfIntegerValues.get(valuesIndex + 1) - listOfIntegerValues.get(valuesIndex);
					if (IntegerCommonDifferenceValue != newDifference)
						sequenceWasFound = false;
					valuesIndex++;
				}
			}
			else if (this.wholeStringIsDescribed == true) {
				boolean allValuesAreTheSameSize = testIfAllValuesAreTheSameSize();
				if (allValuesAreTheSameSize == true) {
					ArrayList<ArrayList<Integer>> secondDegreeListOfIntegerValues = initialize2ndDegreeListOfIntegerValues();
					int valuesIndex = 0;
					IntegerCommonDifferenceValue = 
							secondDegreeListOfIntegerValues.get(1).get(0) - 
								secondDegreeListOfIntegerValues.get(0).get(0);
					while ((sequenceWasFound == true) && (valuesIndex < secondDegreeListOfIntegerValues.size()-1)) {
						ArrayList<Integer> refList = secondDegreeListOfIntegerValues.get(valuesIndex);
						ArrayList<Integer> comparedList = secondDegreeListOfIntegerValues.get(valuesIndex+1);
						boolean gapIsConstantBetweenSubValues = 
								testIfTheGapIsConstantBetween2ndDegreeSubValues(refList, comparedList);
						if (gapIsConstantBetweenSubValues == true) {
							if (valuesIndex > 0) {
								int currentDifference = secondDegreeListOfIntegerValues.get(valuesIndex+1).get(0) - 
										secondDegreeListOfIntegerValues.get(valuesIndex).get(0);
								if (currentDifference != IntegerCommonDifferenceValue)
									sequenceWasFound = false;
							}
						}
						else sequenceWasFound = false;
						valuesIndex++;
					}
				}
				else sequenceWasFound = false;
			}
			else sequenceWasFound = false;
		}
		if ((sequenceWasFound == true) && (Math.abs(IntegerCommonDifferenceValue) <= DescGenSettings.MAX_INCREMENT_ABS_VALUE)) {
			commonDifferenceValue = Integer.toString(IntegerCommonDifferenceValue);
		}
		else sequenceWasFound = false;
	}
	
	@Override
	public boolean getSequenceWasFound() {
		return sequenceWasFound;
	}
	
	@Override
	public SequenceRelationalDataInterface getSequenceRelationalData() throws DescriptorsBuilderCriticalException {
		String enumerationValue = this.enumerationRelationalData.getEnumerationValue();
		SequenceRelationalDataInterface sequenceRelationalData = 
				new SequenceRelationalDataV1(this.dimension, enumerationValue, commonDifferenceValue);
		return sequenceRelationalData;
	}
	
	private boolean testIfAllValuesAreSizeOne() {
		boolean allValuesAreSize1 = true;
		for (String value : values) {
			if (value.contains(","))
				allValuesAreSize1 = false;
		}
		return allValuesAreSize1;
	}
	
	private ArrayList<Integer> initializeListOfIntegerValues() {
		ArrayList<Integer> listOfIntegerValues = new ArrayList<Integer>();
		for (String value : values) {
			listOfIntegerValues.add(Integer.parseInt(value));
		}
		return listOfIntegerValues;
	}
	
	private ArrayList<ArrayList<Integer>> initialize2ndDegreeListOfIntegerValues(){
		ArrayList<ArrayList<Integer>> secondDegreeListOfIntegerValues = new ArrayList<ArrayList<Integer>>();
		for (String value : values) {
			String[] subValues = value.split(",");
			ArrayList<Integer> integerSubValues = new ArrayList<Integer>();
			for (String subValue : subValues) {
				integerSubValues.add(Integer.parseInt(subValue));
			}
			secondDegreeListOfIntegerValues.add(integerSubValues);
		}
		return secondDegreeListOfIntegerValues;
	}
	
	private boolean testIfAllValuesAreTheSameSize() {
		boolean allValuesAreTheSameSize = true;
		int valuesIndex = 1;
		while ((allValuesAreTheSameSize == true) && (valuesIndex < values.size())){
			if (values.get(valuesIndex).length() != values.get(0).length())
				allValuesAreTheSameSize = false;
			valuesIndex++;
		}
		return allValuesAreTheSameSize;
	}
	
	private boolean testIfTheGapIsConstantBetween2ndDegreeSubValues(ArrayList<Integer> refListOfValues, 
			ArrayList<Integer> comparedListOfValues) {
		int subValuesIndex = 1;
		int letterValueGap = comparedListOfValues.get(0) - refListOfValues.get(0);
		boolean gapIsConstant = true;
		while ((gapIsConstant == true) && (subValuesIndex < refListOfValues.size())) {
			if (comparedListOfValues.get(subValuesIndex) - refListOfValues.get(subValuesIndex) != letterValueGap)
				gapIsConstant = false;
			subValuesIndex++;
		}
		return gapIsConstant;
	}

}
