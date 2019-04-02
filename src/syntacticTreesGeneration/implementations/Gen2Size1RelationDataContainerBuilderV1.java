package syntacticTreesGeneration.implementations;

import java.util.ArrayList;
import java.util.HashMap;

import copycatModel.grammar.Group;
import copycatModel.interfaces.AbstractDescriptorInterface;
import copycatModel.interfaces.PropertyInterface;
import copycatModel.interfaces.SignalInterface;
import exceptions.DescriptorsBuilderCriticalException;
import settings.DescGenSettings;
import syntacticTreesGeneration.interfaces.EnumerationRelationalDataInterface;
import syntacticTreesGeneration.interfaces.Gen2Size1RelationDataContainerBuilderInterface;
import syntacticTreesGeneration.interfaces.RelationDataContainerInterface;
import syntacticTreesGeneration.interfaces.SequenceRelationalDataInterface;

public class Gen2Size1RelationDataContainerBuilderV1 implements Gen2Size1RelationDataContainerBuilderInterface {

	private Group gen1Descriptor;
	private SignalInterface signal;
	
	public Gen2Size1RelationDataContainerBuilderV1(SignalInterface signal, Group gen1Descriptor) {
		this.gen1Descriptor= gen1Descriptor;
		this.signal = signal;
	}
	
	@Override
	public ArrayList<RelationDataContainerInterface> getListOfRelationDataContainers() 
			throws DescriptorsBuilderCriticalException {
		ArrayList<RelationDataContainerInterface> listOfRelationDataContainers = 
				new ArrayList<RelationDataContainerInterface>();
		ArrayList<AbstractDescriptorInterface> listOf1Descriptor = new ArrayList<AbstractDescriptorInterface>();
		listOf1Descriptor.add(gen1Descriptor);
		boolean newDescriptorWillCoverTheWholeString = 
				DescriptorSpanGetterV1.testIfWholeStringIsDescribed(signal, listOf1Descriptor);
		EnumerationRelationalDataInterface letterEnumerationRelationalData = buildLetterEnumerationRelationalData();
		EnumerationRelationalDataInterface sizeEnumerationRelationalData = buildSizeEnumerationRelationalData();
		boolean enumerationAllowed = false;
		boolean gen1DescriptorContainsFirstLetter = false;
		boolean gen1DescriptorContainsFirstLetterWasTested = false;
		boolean gen1DescriptorContainsLastLetter = false;
		boolean gen1DescriptorContainsLastLetterWasTested = false;
		boolean gen1DescriptorContainsCentralLetter = false;
		if (!DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_ALL_LETTERS) {
			if (DescGenSettings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS) {
				gen1DescriptorContainsFirstLetter = testIfGen1DescriptorContainsFirstLetter();
				gen1DescriptorContainsFirstLetterWasTested = true;
				if (gen1DescriptorContainsFirstLetter == false) {
					gen1DescriptorContainsLastLetter = testIfGen1DescriptorContainsLastLetter();
					gen1DescriptorContainsLastLetterWasTested = true;
					if (gen1DescriptorContainsLastLetter == true)
						enumerationAllowed = true;
				}
				else enumerationAllowed = true;
			}
		}
		else enumerationAllowed = true;
		if (enumerationAllowed == true) {
			RelationDataContainerInterface enumerationDataContainer = new RelationDataContainerV1();
			enumerationDataContainer.addEnumeration(sizeEnumerationRelationalData);
			enumerationDataContainer.addEnumeration(letterEnumerationRelationalData);
			enumerationDataContainer.setNewDescriptorWillCoverTheWholeString(newDescriptorWillCoverTheWholeString);
			listOfRelationDataContainers.add(enumerationDataContainer);			
		}
		boolean sequenceAllowed = false;
		boolean restrictedSequenceAllowed = false;
		if (!DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_ALL_LETTERS) {
			if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
				if (gen1DescriptorContainsFirstLetterWasTested == false)
					gen1DescriptorContainsFirstLetter = testIfGen1DescriptorContainsFirstLetter();
				if (gen1DescriptorContainsFirstLetter == true)
					sequenceAllowed = true;
				else if (DescGenSettings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_LAST_LETTER) {
					if (gen1DescriptorContainsLastLetterWasTested == false)
						gen1DescriptorContainsLastLetter = testIfGen1DescriptorContainsLastLetter();
					if (gen1DescriptorContainsLastLetter == true)
						sequenceAllowed = true;
					else if (DescGenSettings.GEN2SIZE1_RESTRICTED_SEQUENCE_ALLOWED_FOR_CENTRAL_LETTER) {
						gen1DescriptorContainsCentralLetter = testIfGen1DescriptorContainsCentralLetter();
						if (gen1DescriptorContainsCentralLetter == true)
							restrictedSequenceAllowed = true;
					}
				}

			}
		}
		else sequenceAllowed = true;
		if ((sequenceAllowed == true || restrictedSequenceAllowed == true) && enumerationAllowed == true) {
			int minIncrement;
			int maxIncrement;
			if (sequenceAllowed == true) {
				minIncrement = DescGenSettings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT;
				maxIncrement = DescGenSettings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT;
			}
			else {
				minIncrement = DescGenSettings.GEN2SIZE1_RESTRICTED_SEQUENCE_MIN_INCREMENT;
				maxIncrement = DescGenSettings.GEN2SIZE1_RESTRICTED_SEQUENCE_MAX_INCREMENT;
			}
			for (int i=minIncrement ; i<=maxIncrement ; i++) {
				RelationDataContainerInterface iSequenceDataContainer = new RelationDataContainerV1();
				SequenceRelationalDataInterface iSequenceRelationalData = 
						buildLetterSequenceRelationalData(letterEnumerationRelationalData, i);
				SequenceRelationalDataInterface sizeSequenceRelationalData = 
						buildSizeSequenceRelationalData(sizeEnumerationRelationalData);
				iSequenceDataContainer.addEnumeration(sizeEnumerationRelationalData);
				iSequenceDataContainer.addEnumeration(letterEnumerationRelationalData);
				iSequenceDataContainer.addSequence(sizeSequenceRelationalData);
				iSequenceDataContainer.addSequence(iSequenceRelationalData);
				iSequenceDataContainer.setNewDescriptorWillCoverTheWholeString(newDescriptorWillCoverTheWholeString);
				listOfRelationDataContainers.add(iSequenceDataContainer);
			}			
		}
		for (RelationDataContainerInterface rdContainer : listOfRelationDataContainers) {
			rdContainer.cleanValuesRedundancies();
		}
		return listOfRelationDataContainers;
	}
	
	private boolean testIfGen1DescriptorContainsFirstLetter() throws DescriptorsBuilderCriticalException {
		boolean gen1DescriptorContainsFirstLetter;
		String gen1LetterPositionValue = "";
		ArrayList<String> listOfPropertiesWithPath = gen1Descriptor.getListOfPropertiesWithPath();
		boolean letterPositionWasFound = false;
		int propertyIndex = 0;
		while (letterPositionWasFound == false && propertyIndex < listOfPropertiesWithPath.size()) {
			String currentPropertyWithPath = listOfPropertiesWithPath.get(propertyIndex);
			if (currentPropertyWithPath.contains("letter/position")) {
				letterPositionWasFound = true;
				int lastSlashIndex = currentPropertyWithPath.lastIndexOf("/");
				gen1LetterPositionValue = currentPropertyWithPath.substring(lastSlashIndex + 1);
			}
			else propertyIndex++;
		}
		if (!gen1LetterPositionValue.isEmpty()) {
			if (gen1LetterPositionValue.equals("1"))
				gen1DescriptorContainsFirstLetter = true;
			else gen1DescriptorContainsFirstLetter = false;
		}
		else throw new DescriptorsBuilderCriticalException("Gen2Size1DescriptorBuilder : "
				+ "error in testIfGen1DescriptorContainsFirstLetter() method");
		return gen1DescriptorContainsFirstLetter;
	}
	
	private boolean testIfGen1DescriptorContainsLastLetter() throws DescriptorsBuilderCriticalException {
		int signalSize = signal.getSignalSize();
		boolean gen1DescriptorContainsLastLetter;
		String gen1LetterPositionValue = "";
		ArrayList<String> listOfPropertiesWithPath = gen1Descriptor.getListOfPropertiesWithPath();
		boolean letterPositionWasFound = false;
		int propertyIndex = 0;
		while (letterPositionWasFound == false && propertyIndex < listOfPropertiesWithPath.size()) {
			String currentPropertyWithPath = listOfPropertiesWithPath.get(propertyIndex);
			if (currentPropertyWithPath.contains("letter/position")) {
				letterPositionWasFound = true;
				int lastSlashIndex = currentPropertyWithPath.lastIndexOf("/");
				gen1LetterPositionValue = currentPropertyWithPath.substring(lastSlashIndex + 1);
			}
			else propertyIndex++;
		}
		if (!gen1LetterPositionValue.isEmpty()) {
			if (gen1LetterPositionValue.equals(Integer.toString(signalSize)))
				gen1DescriptorContainsLastLetter = true;
			else gen1DescriptorContainsLastLetter = false;
		}
		else throw new DescriptorsBuilderCriticalException("Gen2Size1DescriptorBuilder : Group position was not found");
		return gen1DescriptorContainsLastLetter;
	}
	
	private boolean testIfGen1DescriptorContainsCentralLetter() throws DescriptorsBuilderCriticalException {
		int signalSize = signal.getSignalSize();
		boolean gen1DescriptorContainsCentralLetter;
		String gen1LetterPositionValue = "";
		ArrayList<String> listOfPropertiesWithPath = gen1Descriptor.getListOfPropertiesWithPath();
		boolean letterPositionWasFound = false;
		int propertyIndex = 0;
		while (letterPositionWasFound == false && propertyIndex < listOfPropertiesWithPath.size()) {
			String currentPropertyWithPath = listOfPropertiesWithPath.get(propertyIndex);
			if (currentPropertyWithPath.contains("letter/position")) {
				letterPositionWasFound = true;
				int lastSlashIndex = currentPropertyWithPath.lastIndexOf("/");
				gen1LetterPositionValue = currentPropertyWithPath.substring(lastSlashIndex + 1);
			}
			else propertyIndex++;
		}
		if (!gen1LetterPositionValue.isEmpty()) {
			int centralPosition;
			if ((signalSize % 2) == 1) {
				centralPosition = ((int) signalSize/2) + 1;
				if (gen1LetterPositionValue.equals(Integer.toString(centralPosition)))
					gen1DescriptorContainsCentralLetter = true;
				else gen1DescriptorContainsCentralLetter = false;
			}
			else gen1DescriptorContainsCentralLetter = false;
		}
		else throw new DescriptorsBuilderCriticalException("Gen2Size1DescriptorBuilder : Group position was not found");
		return gen1DescriptorContainsCentralLetter;
	}
	
	private EnumerationRelationalDataV1 buildLetterEnumerationRelationalData() throws DescriptorsBuilderCriticalException {
		EnumerationRelationalDataV1 letterEnumerationRelationalData;
		String dimension = "";
		String enumerationValue="";
		HashMap<String, PropertyInterface> dimensionToProperty = gen1Descriptor.getpropertyContainer().getDimensionToProperty();
		ArrayList<String> listOfDimensions = new ArrayList<String>(dimensionToProperty.keySet());
		boolean dimensionWasFound = false;
		int keyIndex = 0;
		while (keyIndex < listOfDimensions.size() && dimensionWasFound == false) {
			String currentDimension = listOfDimensions.get(keyIndex);
			if (currentDimension.contains("platonicLetter")){
				dimension = currentDimension;
				enumerationValue = dimensionToProperty.get(currentDimension).getValue();
				dimensionWasFound = true;
			}
			else keyIndex++;
		}
		if (!enumerationValue.isEmpty()) {
			letterEnumerationRelationalData = new EnumerationRelationalDataV1(dimension, enumerationValue);
			return letterEnumerationRelationalData;
		}
		else throw new DescriptorsBuilderCriticalException("Gen2Size1DescriptorBuilder : platonicLetter value was not found.");				
	}
	
	private EnumerationRelationalDataV1 buildSizeEnumerationRelationalData() throws DescriptorsBuilderCriticalException {
		EnumerationRelationalDataV1 sizeEnumerationRelationalData;
		String dimension = "";
		String enumerationValue = "1";
		ArrayList<String> listOfDimensions = gen1Descriptor.getpropertyContainer().getListOfDimensions();
		boolean dimensionWasFound = false;
		int keyIndex = 0;
		while (keyIndex < listOfDimensions.size() && dimensionWasFound == false) {
			String currentDimension = listOfDimensions.get(keyIndex);
			if (currentDimension.contains("size")){
				dimension = currentDimension;
				dimensionWasFound = true;
			}
			else keyIndex++;
		}
		if (dimensionWasFound == true) {
			sizeEnumerationRelationalData = new EnumerationRelationalDataV1(dimension, enumerationValue);
			return sizeEnumerationRelationalData;
		}
		else throw new DescriptorsBuilderCriticalException("Gen2Size1DescriptorBuilder : Size value was not found.");			
	}
	
	private SequenceRelationalDataInterface buildLetterSequenceRelationalData(
			EnumerationRelationalDataInterface letterEnumerationRelationalData, 
			int incrementValue) throws DescriptorsBuilderCriticalException {
		SequenceRelationalDataInterface letterSequenceRelationalData;
		String dimensionValue = letterEnumerationRelationalData.getDimensions().get(0);
		String enumerationValue = letterEnumerationRelationalData.getEnumerationValue();
		String commonDifference = Integer.toString(incrementValue);
		letterSequenceRelationalData = new SequenceRelationalDataV1(dimensionValue, enumerationValue, commonDifference);
		return letterSequenceRelationalData;		
	}
	
	private SequenceRelationalDataInterface buildSizeSequenceRelationalData(
			EnumerationRelationalDataInterface sizeEnumerationRelationalData) throws DescriptorsBuilderCriticalException {
		int incrementValue = 0;
		SequenceRelationalDataInterface sizeSequenceRelationalData;
		String dimensionValue = sizeEnumerationRelationalData.getDimensions().get(0);
		String enumerationValue = sizeEnumerationRelationalData.getEnumerationValue();
		String commonDifference = Integer.toString(incrementValue);
		sizeSequenceRelationalData = new SequenceRelationalDataV1(dimensionValue, enumerationValue, commonDifference);
		return sizeSequenceRelationalData;		
	}
	
	

}
