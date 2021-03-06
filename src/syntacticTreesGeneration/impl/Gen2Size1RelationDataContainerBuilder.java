package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exceptions.SynTreeGenerationException;
import model.copycatModel.synTreeGrammar.Frame;
import model.synTreeModel.ISignal;
import model.synTreeModel.IGrammaticalST;
import model.synTreeModel.impl.utils.IProperty;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IGen2Size1RelationDataContainerBuilder;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISequenceRelationalData;

public class Gen2Size1RelationDataContainerBuilder implements IGen2Size1RelationDataContainerBuilder {

	private Frame gen1Descriptor;
	private ISignal signal;
	
	public Gen2Size1RelationDataContainerBuilder(ISignal signal, Frame gen1Descriptor) {
		this.gen1Descriptor= gen1Descriptor;
		this.signal = signal;
	}
	
	@Override
	public List<IRelationDataContainer> getListOfRelationDataContainers() 
			throws SynTreeGenerationException {
		List<IRelationDataContainer> listOfRelationDataContainers = 
				new ArrayList<IRelationDataContainer>();
		List<IGrammaticalST> listOf1Descriptor = new ArrayList<IGrammaticalST>();
		listOf1Descriptor.add(gen1Descriptor);
		boolean newDescriptorWillCoverTheWholeString = 
				DescriptorSpanGetter.testIfWholeStringIsDescribed(signal, listOf1Descriptor);
		IEnumerationRelationalData letterEnumerationRelationalData = buildLetterEnumerationRelationalData();
		IEnumerationRelationalData sizeEnumerationRelationalData = buildSizeEnumerationRelationalData();
		boolean enumerationAllowed = false;
		boolean gen1DescriptorContainsFirstLetter = false;
		boolean gen1DescriptorContainsFirstLetterWasTested = false;
		boolean gen1DescriptorContainsLastLetter = false;
		boolean gen1DescriptorContainsLastLetterWasTested = false;
		boolean gen1DescriptorContainsCentralLetter = false;
		if (!Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_ALL_LETTERS) {
			if (Settings.GEN2SIZE1_ENUMERATION_ALLOWED_FOR_THE_WORD_ENDS) {
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
			IRelationDataContainer enumerationDataContainer = new RelationDataContainer();
			enumerationDataContainer.addEnumeration(letterEnumerationRelationalData);
			enumerationDataContainer.addEnumeration(sizeEnumerationRelationalData);
			enumerationDataContainer.setNewDescriptorWillCoverTheWholeString(newDescriptorWillCoverTheWholeString);
			listOfRelationDataContainers.add(enumerationDataContainer);			
		}
		boolean sequenceAllowed = false;
		boolean restrictedSequenceAllowed = false;
		if (!Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_ALL_LETTERS) {
			if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_FIRST_LETTER) {
				if (gen1DescriptorContainsFirstLetterWasTested == false)
					gen1DescriptorContainsFirstLetter = testIfGen1DescriptorContainsFirstLetter();
				if (gen1DescriptorContainsFirstLetter == true)
					sequenceAllowed = true;
				else if (Settings.GEN2SIZE1_SEQUENCE_ALLOWED_FOR_LAST_LETTER) {
					if (gen1DescriptorContainsLastLetterWasTested == false)
						gen1DescriptorContainsLastLetter = testIfGen1DescriptorContainsLastLetter();
					if (gen1DescriptorContainsLastLetter == true)
						sequenceAllowed = true;
					else if (Settings.GEN2SIZE1_RESTRICTED_SEQUENCE_ALLOWED_FOR_CENTRAL_LETTER) {
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
				minIncrement = Settings.GEN2SIZE1_SEQUENCE_MIN_INCREMENT;
				maxIncrement = Settings.GEN2SIZE1_SEQUENCE_MAX_INCREMENT;
			}
			else {
				minIncrement = Settings.GEN2SIZE1_RESTRICTED_SEQUENCE_MIN_INCREMENT;
				maxIncrement = Settings.GEN2SIZE1_RESTRICTED_SEQUENCE_MAX_INCREMENT;
			}
			for (int i=minIncrement ; i<=maxIncrement ; i++) {
				IRelationDataContainer iSequenceDataContainer = new RelationDataContainer();
				ISequenceRelationalData iSequenceRelationalData = 
						buildLetterSequenceRelationalData(letterEnumerationRelationalData, i);
				ISequenceRelationalData sizeSequenceRelationalData = 
						buildSizeSequenceRelationalData(sizeEnumerationRelationalData);
				iSequenceDataContainer.addEnumeration(letterEnumerationRelationalData);
				iSequenceDataContainer.addEnumeration(sizeEnumerationRelationalData);
				iSequenceDataContainer.addSequence(iSequenceRelationalData);
				iSequenceDataContainer.addSequence(sizeSequenceRelationalData);
				iSequenceDataContainer.setNewDescriptorWillCoverTheWholeString(newDescriptorWillCoverTheWholeString);
				listOfRelationDataContainers.add(iSequenceDataContainer);
			}			
		}
		return listOfRelationDataContainers;
	}
	
	private boolean testIfGen1DescriptorContainsFirstLetter() throws SynTreeGenerationException {
		boolean gen1DescriptorContainsFirstLetter;
		String gen1LetterPositionValue = "";
		List<String> listOfPropertiesWithPath = gen1Descriptor.getListOfPropertiesWithPath();
		boolean letterPositionWasFound = false;
		int propertyIndex = 0;
		while (letterPositionWasFound == false && propertyIndex < listOfPropertiesWithPath.size()) {
			String currentPropertyWithPath = listOfPropertiesWithPath.get(propertyIndex);
			if (currentPropertyWithPath.contains("letter/position")) {
				letterPositionWasFound = true;
				int lastSlashIndex = currentPropertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
				gen1LetterPositionValue = currentPropertyWithPath.substring(lastSlashIndex + 1);
			}
			else propertyIndex++;
		}
		if (!gen1LetterPositionValue.isEmpty()) {
			if (gen1LetterPositionValue.equals("1"))
				gen1DescriptorContainsFirstLetter = true;
			else gen1DescriptorContainsFirstLetter = false;
		}
		else throw new SynTreeGenerationException("Gen2Size1DescriptorBuilder : "
				+ "error in testIfGen1DescriptorContainsFirstLetter() method");
		return gen1DescriptorContainsFirstLetter;
	}
	
	private boolean testIfGen1DescriptorContainsLastLetter() throws SynTreeGenerationException {
		int signalSize = signal.getSignalSize();
		boolean gen1DescriptorContainsLastLetter;
		String gen1LetterPositionValue = "";
		List<String> listOfPropertiesWithPath = gen1Descriptor.getListOfPropertiesWithPath();
		boolean letterPositionWasFound = false;
		int propertyIndex = 0;
		while (letterPositionWasFound == false && propertyIndex < listOfPropertiesWithPath.size()) {
			String currentPropertyWithPath = listOfPropertiesWithPath.get(propertyIndex);
			if (currentPropertyWithPath.contains("letter/position")) {
				letterPositionWasFound = true;
				int lastSlashIndex = currentPropertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
				gen1LetterPositionValue = currentPropertyWithPath.substring(lastSlashIndex + 1);
			}
			else propertyIndex++;
		}
		if (!gen1LetterPositionValue.isEmpty()) {
			if (gen1LetterPositionValue.equals(Integer.toString(signalSize)))
				gen1DescriptorContainsLastLetter = true;
			else gen1DescriptorContainsLastLetter = false;
		}
		else throw new SynTreeGenerationException("Gen2Size1DescriptorBuilder : Frame position was not found");
		return gen1DescriptorContainsLastLetter;
	}
	
	private boolean testIfGen1DescriptorContainsCentralLetter() throws SynTreeGenerationException {
		int signalSize = signal.getSignalSize();
		boolean gen1DescriptorContainsCentralLetter;
		String gen1LetterPositionValue = "";
		List<String> listOfPropertiesWithPath = gen1Descriptor.getListOfPropertiesWithPath();
		boolean letterPositionWasFound = false;
		int propertyIndex = 0;
		while (letterPositionWasFound == false && propertyIndex < listOfPropertiesWithPath.size()) {
			String currentPropertyWithPath = listOfPropertiesWithPath.get(propertyIndex);
			if (currentPropertyWithPath.contains("letter/position")) {
				letterPositionWasFound = true;
				int lastSlashIndex = currentPropertyWithPath.lastIndexOf(Settings.PATH_SEPARATOR);
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
		else throw new SynTreeGenerationException("Gen2Size1DescriptorBuilder : Frame position was not found");
		return gen1DescriptorContainsCentralLetter;
	}
	
	private EnumerationRelationalData buildLetterEnumerationRelationalData() throws SynTreeGenerationException {
		EnumerationRelationalData letterEnumerationRelationalData;
		String indexedPath = "";
		String enumerationValue= "";
		Map<String, IProperty> indexedPathToProperty = gen1Descriptor.getpropertyContainer().getIndexedPathToProperty();
		List<String> listOfIndexedPaths = new ArrayList<String>(indexedPathToProperty.keySet());
		boolean indexedPathWasFound = false;
		int keyIndex = 0;
		while (keyIndex < listOfIndexedPaths.size() && indexedPathWasFound == false) {
			String currentIndexedPath = listOfIndexedPaths.get(keyIndex);
			if (currentIndexedPath.contains("platonicLetter")){
				indexedPath = currentIndexedPath;
				enumerationValue = indexedPathToProperty.get(currentIndexedPath).getValue();
				indexedPathWasFound = true;
			}
			else keyIndex++;
		}
		if (!enumerationValue.isEmpty()) {
			String dimensionCode = DimensionEncodingManager.getDimensionCodeFromIndexedPath(indexedPath);
			letterEnumerationRelationalData = new EnumerationRelationalData(dimensionCode, enumerationValue);
			return letterEnumerationRelationalData;
		}
		else throw new SynTreeGenerationException("Gen2Size1DescriptorBuilder : platonicLetter value was not found.");				
	}
	
	private EnumerationRelationalData buildSizeEnumerationRelationalData() throws SynTreeGenerationException {
		EnumerationRelationalData sizeEnumerationRelationalData;
		String indexedPath = "";
		String enumerationValue = "1";
		List<String> listOfIndexedPaths = gen1Descriptor.getpropertyContainer().getListOfIndexedPaths();
		boolean indexedPathWasFound = false;
		int keyIndex = 0;
		while (keyIndex < listOfIndexedPaths.size() && indexedPathWasFound == false) {
			String currentIndexedPath = listOfIndexedPaths.get(keyIndex);
			if (currentIndexedPath.contains("size")){
				indexedPath = currentIndexedPath;
				indexedPathWasFound = true;
			}
			else keyIndex++;
		}
		if (indexedPathWasFound == true) {
			String dimensionCode = DimensionEncodingManager.getDimensionCodeFromIndexedPath(indexedPath);
			sizeEnumerationRelationalData = new EnumerationRelationalData(dimensionCode, enumerationValue);
			return sizeEnumerationRelationalData;
		}
		else throw new SynTreeGenerationException("Gen2Size1DescriptorBuilder : Size value was not found.");			
	}
	
	private ISequenceRelationalData buildLetterSequenceRelationalData(
			IEnumerationRelationalData letterEnumerationRelationalData,	int incrementValue) throws SynTreeGenerationException {
		ISequenceRelationalData letterSequenceRelationalData;
		String indexedPath = letterEnumerationRelationalData.getIndexedPath();
		String enumerationValue = letterEnumerationRelationalData.getEnumerationValue();
		String commonDifference = Integer.toString(incrementValue);
		String dimensionCode = DimensionEncodingManager.getDimensionCodeFromIndexedPath(indexedPath);
		letterSequenceRelationalData = new SequenceRelationalData(dimensionCode, enumerationValue, commonDifference);
		return letterSequenceRelationalData;		
	}
	
	private ISequenceRelationalData buildSizeSequenceRelationalData(
			IEnumerationRelationalData sizeEnumerationRelationalData) throws SynTreeGenerationException {
		int incrementValue = 0;
		ISequenceRelationalData sizeSequenceRelationalData;
		String indexedPath = sizeEnumerationRelationalData.getIndexedPath();
		String enumerationValue = sizeEnumerationRelationalData.getEnumerationValue();
		String commonDifference = Integer.toString(incrementValue);
		String dimensionCode = DimensionEncodingManager.getDimensionCodeFromIndexedPath(indexedPath);		
		sizeSequenceRelationalData = new SequenceRelationalData(dimensionCode, enumerationValue, commonDifference);
		return sizeSequenceRelationalData;		
	}
	
	

}
