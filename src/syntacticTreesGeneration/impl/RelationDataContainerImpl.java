package syntacticTreesGeneration.impl;

import java.util.ArrayList;
import java.util.List;

import exceptions.SynTreeGenerationException;
import settings.Settings;
import syntacticTreesGeneration.IEnumerationRelationalData;
import syntacticTreesGeneration.IRelationDataContainer;
import syntacticTreesGeneration.ISequenceRelationalData;
import syntacticTreesGeneration.ISymmetryRelationalData;

public class RelationDataContainerImpl implements IRelationDataContainer {

	private boolean newDescriptorWillCoverTheFullString = false;
	private List<IEnumerationRelationalData> listOfEnumerations = new ArrayList<IEnumerationRelationalData>();
	private List<ISequenceRelationalData> listOfSequences = new ArrayList<ISequenceRelationalData>();
	private List<ISymmetryRelationalData> listOfSymmetries = new ArrayList<ISymmetryRelationalData>();
	
	//Getters
	@Override
	public boolean getNewDescriptorWillCoverTheFullString() {
		return newDescriptorWillCoverTheFullString;
	}	
	
	@Override
	public List<IEnumerationRelationalData> getListOfEnumerations() {
		return listOfEnumerations;
	}

	@Override
	public List<ISequenceRelationalData> getListOfSequences() {
		return listOfSequences;
	}

	@Override
	public List<ISymmetryRelationalData> getListOfSymmetries() {
		return listOfSymmetries;
	}	
	
	//Setters
	@Override
	public void setNewDescriptorWillCoverTheWholeString(boolean newDescriptorWillCoverTheFullString) {
		this.newDescriptorWillCoverTheFullString = newDescriptorWillCoverTheFullString;
	}
	
	@Override
	public void addEnumeration(IEnumerationRelationalData enumerationRelationalData) {
		listOfEnumerations.add(enumerationRelationalData);
	}
	
	@Override
	public void addSequence(ISequenceRelationalData sequenceRelationalData) {
		listOfSequences.add(sequenceRelationalData);
	}
	
	@Override
	public void addSymmetry(ISymmetryRelationalData symmetryRelationalData) {
		listOfSymmetries.add(symmetryRelationalData);
	}
	
	@Override
	public void cleanValuesRedundancies() throws SynTreeGenerationException {
		if (Settings.REDUNDANCIES_IN_RELATIONS_CAN_BE_CLEANED) {
			List<IEnumerationRelationalData> cleanListOfEnumerationRelationalData 
			= new ArrayList<IEnumerationRelationalData>();
			List<ISequenceRelationalData> cleanListOfSequences = 
					new ArrayList<ISequenceRelationalData>();
			List<ISymmetryRelationalData> cleanListOfSymmetries = 
					new ArrayList<ISymmetryRelationalData>();
			cloneEnumerationList();
			boolean enumerationListHasBeenCleaned = false;
			for (int i=0 ; i<listOfEnumerations.size() ; i++) {
				String iEnumerationValue = listOfEnumerations.get(i).getEnumerationValue();
				boolean iValueIsUnique = true;
				for (int j=i+1 ; j<listOfEnumerations.size() ; j++) {
					String jEnumerationValue = listOfEnumerations.get(j).getEnumerationValue();
					if (iEnumerationValue.equals(jEnumerationValue)){
						boolean ifSequencesFoundCommonDiffAreTheSame = 
								checkIfEnumerationsCanBeMerged(listOfEnumerations.get(i), listOfEnumerations.get(j));
						if (ifSequencesFoundCommonDiffAreTheSame == true) {
							List<String> dimensionsToBeAdded = listOfEnumerations.get(i).getDimensions();
							listOfEnumerations.get(j).addDimensions(dimensionsToBeAdded);
							iValueIsUnique = false;
							enumerationListHasBeenCleaned = true;
						}
					}
				}
				if (iValueIsUnique == true)
					cleanListOfEnumerationRelationalData.add(listOfEnumerations.get(i));
			}
			listOfEnumerations = cleanListOfEnumerationRelationalData;
			if (enumerationListHasBeenCleaned == true) {
				cloneOtherLists();
				for (IEnumerationRelationalData enumerationRelationalData : listOfEnumerations) {
					boolean dimensionWasFoundInListOfSequences = false;
					boolean dimensionWasFoundInListOfSymmetries = false;
					List<String> enumerationDimensions = enumerationRelationalData.getDimensions();
					for (String enumerationDimension : enumerationDimensions) {
						for (ISequenceRelationalData sequenceRelationalData : listOfSequences) {
							if (sequenceRelationalData.getDimensions().contains(enumerationDimension)){
								if (dimensionWasFoundInListOfSequences == false) {
									cleanListOfSequences.add(new SequenceRelationalDataImpl(sequenceRelationalData));
									dimensionWasFoundInListOfSequences = true;
								}
								else {
									cleanListOfSequences.get(
										cleanListOfSequences.size()-1).addDimensions(sequenceRelationalData.getDimensions());
								}
							}
						}
						for (ISymmetryRelationalData symmetryRelationalData : listOfSymmetries) {
							if (symmetryRelationalData.getDimensions().contains(enumerationDimension)){
								if (dimensionWasFoundInListOfSymmetries == false) {
									cleanListOfSymmetries.add(symmetryRelationalData);
									dimensionWasFoundInListOfSymmetries = true;
								}
								else {
									cleanListOfSymmetries.get(
											cleanListOfSymmetries.size()-1).addDimensions(symmetryRelationalData.getDimensions());
								}
							}
						}				
					}
				}
				listOfSequences = cleanListOfSequences;
				listOfSymmetries = cleanListOfSymmetries;							
			}
		}
	}
	
	@Override
	public void clear() {
		listOfEnumerations.clear();
		listOfSequences.clear();
		listOfSymmetries.clear();
	}	
	
	private boolean checkIfEnumerationsCanBeMerged(IEnumerationRelationalData enum1, IEnumerationRelationalData enum2) {
		boolean enumerationsCanBeMerged = true;
		String dimension1 = enum1.getDimensions().get(0);
		String dimension2 = enum2.getDimensions().get(0);
		int sequenceListIndexA = 0;
		while (enumerationsCanBeMerged == true && sequenceListIndexA < listOfSequences.size()) {
			ISequenceRelationalData sequenceA = listOfSequences.get(sequenceListIndexA);
			if (sequenceA.getDimensions().contains(dimension1)) {
				String commonDiffA = sequenceA.getCommonDifference();
				boolean sequenceBHasBeenFound = false;
				for (ISequenceRelationalData sequenceB : listOfSequences) {
					if (sequenceB.getDimensions().contains(dimension2)) {
						sequenceBHasBeenFound = true;
						String commonDiffB = sequenceB.getCommonDifference();
						enumerationsCanBeMerged = (commonDiffA.equals(commonDiffB));
					}
				}
				if (sequenceBHasBeenFound == false)
					enumerationsCanBeMerged = false;
			}
			sequenceListIndexA++;
		}
		return enumerationsCanBeMerged;
	}
	
	private void cloneEnumerationList() {
		List<IEnumerationRelationalData> listOfEnumerationsClone = new ArrayList<IEnumerationRelationalData>();
		for (IEnumerationRelationalData enumerationRelationalData : listOfEnumerations) {
			IEnumerationRelationalData enumerationRelationalDataClone = 
					new EnumerationRelationalDataImpl(enumerationRelationalData);
			listOfEnumerationsClone.add(enumerationRelationalDataClone);
		}
		listOfEnumerations = listOfEnumerationsClone;
	}
	
	private void cloneOtherLists() {
		List<ISequenceRelationalData> listOfSequencesClone = new ArrayList<ISequenceRelationalData>();
		for (ISequenceRelationalData sequenceRelationalData : listOfSequences) {
			ISequenceRelationalData sequenceRelationalDataClone = 
					new SequenceRelationalDataImpl(sequenceRelationalData);
			listOfSequencesClone.add(sequenceRelationalDataClone);
		}
		List<ISymmetryRelationalData> listOfSymmetriesClone = new ArrayList<ISymmetryRelationalData>();	
		for (ISymmetryRelationalData symmetryRelationalData : listOfSymmetries) {
			ISymmetryRelationalData symmetryRelationalDataClone = 
					new SymmetryRelationalDataImpl(symmetryRelationalData);
			listOfSymmetriesClone.add(symmetryRelationalDataClone);
		}
		
		listOfSequences = listOfSequencesClone;
		listOfSymmetries = listOfSymmetriesClone;
	}

}
